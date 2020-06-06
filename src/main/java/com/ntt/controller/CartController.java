package com.ntt.controller;

import com.ntt.config.PaypalPaymentIntent;
import com.ntt.config.PaypalPaymentMethod;
import com.ntt.entity.*;
import com.ntt.repository.CustomerRepository;
import com.ntt.repository.InvoiceRepository;
import com.ntt.service.InvoiceService;
import com.ntt.service.PaypalService;
import com.ntt.util.Util;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;

@Controller
public class CartController {
    @Autowired
    private PaypalService paypalService;

    @Autowired
    InvoiceService invoiceSer;

    @Autowired
    CustomerRepository customerRepo;

    @PostMapping(value = "/booking-paypal")
    public String pay(HttpServletRequest request, @RequestParam float subtotal,
                      Principal principal,
                      @RequestParam(name = "receiver")String receiver,
                      @RequestParam(name = "receiveAddress")String receiveAddress,
                      @RequestParam(name = "receivePhone")String receivePhone){
        String cancelUrl = Util.getBaseURL(request) + "/cancel-pay";
        String successUrl = Util.getBaseURL(request) + "/save-invoice-after-pay";
        try {
            HttpSession session = request.getSession();
            if(session.getAttribute("invoice")!=null){
                session.removeAttribute("invoice");
            }

            Invoice invoice = new Invoice();

            long millis=System.currentTimeMillis();
            java.sql.Date date=new java.sql.Date(millis);
            invoice.setDate(date);

            invoice.setPaymentMethod(PaymentMethod.Paypal);

            Customer customer = customerRepo.findByAccount_UserName(principal.getName());
            invoice.setCustomer(customer);

            invoice.setReceiveAddress(receiveAddress);
            invoice.setReceivePhone(receivePhone);
            invoice.setReceiver(receiver);

            invoice.setStatus(InvoiceStatus.Da_xac_nhan);

            ArrayList<Item> items = (ArrayList<Item>) session.getAttribute("items");
            float total = (float) 0;
            for(Item item : items){
                total += item.getTotal();
            }
            invoice.setTotalPayment(total);

            session.setAttribute("invoice", invoice);

            Payment payment = paypalService.createPayment(
                    subtotal,
                    "USD",
                    PaypalPaymentMethod.paypal,
                    PaypalPaymentIntent.sale,
                    "payment description",
                    cancelUrl,
                    successUrl);
            for(Links links : payment.getLinks()){
                System.out.println(links.getHref());
                if(links.getRel().equals("approval_url")){
                    return "redirect:" + links.getHref();
                }
            }
        } catch (PayPalRESTException e) {
        }
        return "redirect:/cart";
    }

    @RequestMapping("/cancel-pay")
    public String cancelPay(HttpServletRequest req){
        req.getSession().removeAttribute("invoice");
        return "redirect:/cart";
    }

    @RequestMapping("/save-invoice-after-pay")
    public String saveInvoiceAfterPay(HttpServletRequest req,
                                      @RequestParam(name = "paymentId") String paymentId,
                                      @RequestParam(name = "token") String token,
                                      @RequestParam(name = "PayerID") String payerID){
        HttpSession session = req.getSession();
        Invoice invoice = (Invoice) session.getAttribute("invoice");
        ArrayList<Item> items = (ArrayList<Item>) session.getAttribute("items");
        invoice.setItems(items);
        invoiceSer.create(invoice);
        session.removeAttribute("invoice");
        items.clear();
        return "redirect:/cart";
    }

    @PostMapping("/booking-cash")
    public String bookingWithCash(HttpServletRequest req, Model model, Principal principal,
                                  @RequestParam(name = "receiver")String receiver,
                                  @RequestParam(name = "receiveAddress")String receiveAddress,
                                  @RequestParam(name = "receivePhone")String receivePhone){

        Invoice invoice = new Invoice();

        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        invoice.setDate(date);

        invoice.setPaymentMethod(PaymentMethod.Cash);

        Customer customer = customerRepo.findByAccount_UserName(principal.getName());
        invoice.setCustomer(customer);

        invoice.setReceiveAddress(receiveAddress);
        invoice.setReceivePhone(receivePhone);
        invoice.setReceiver(receiver);

        invoice.setStatus(InvoiceStatus.Da_xac_nhan);

        ArrayList<Item> items = (ArrayList<Item>) req.getSession().getAttribute("items");
        invoice.setItems(items);

        invoiceSer.create(invoice);
        items.clear();
        model.addAttribute("items",items);
        return "redirect:/cart";
    }

    @GetMapping("/removeItem/{index}")
    public String removeItem(HttpServletRequest req, @PathVariable(name = "index") int index){
        HttpSession session = req.getSession();
        ArrayList<Item> items = (ArrayList<Item>) session.getAttribute("items");
        items.remove(index);
        return "redirect:/cart";
    }

    @GetMapping("/oder/cancel/{id}")
    public String cancelOrder(Principal principal,@PathVariable(name = "id")int id){
        Customer customer = customerRepo.findByAccount_UserName(principal.getName());
        Invoice invoice = invoiceSer.getInvoiceById(id);
        if(customer.getId() == invoice.getCustomer().getId()){
            invoice.setStatus(InvoiceStatus.Da_huy);
            invoiceSer.update(invoice);
        }
        return "redirect:/cart";
    }
}
