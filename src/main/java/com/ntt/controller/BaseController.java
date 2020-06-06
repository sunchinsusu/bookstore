package com.ntt.controller;

import com.ntt.entity.*;
import com.ntt.repository.CustomerRepository;
import com.ntt.service.BookService;
import com.ntt.service.InvoiceService;
import com.ntt.service.MyUserDetailsService;
import com.ntt.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;

@Controller
public class BaseController {

    @Autowired
    CustomerRepository customerRepo;

    @Autowired
    BookService bookSer;

    @Autowired
    InvoiceService invoiceSer;

    @RequestMapping("/test")
    public String test(){
        return "test";
    }

    @RequestMapping("/account")
    public String viewAccount(HttpServletRequest req, Model model, Principal principal){
        Customer c = customerRepo.findByAccount_UserName(principal.getName());
        model.addAttribute("customer", c);
        return "account";
    }
    @RequestMapping("/cart")
    public String viewCart(HttpServletRequest req,Model model, Principal principal){

        Customer customer = customerRepo.findByAccount_UserName(principal.getName());
        model.addAttribute("user", customer);

        HttpSession session = req.getSession();
        ArrayList<Item> items = (ArrayList<Item>) session.getAttribute("items");

        ArrayList<Invoice> listOrder = new ArrayList<Invoice>(invoiceSer.getInvoiceByIdCustomer(customer.getId()));
        ArrayList<Invoice> listOrderSort = new ArrayList<>();
        for(int i = listOrder.size()-1; i>=0; i--){
            listOrderSort.add(listOrder.get(i));
        }

        model.addAttribute("listOrder", listOrderSort);

        if(items == null){
            items = new ArrayList<>();
            model.addAttribute("items", items);
            return "cart";
        }
        else{
            float subtotal = (float)0;
            for(Item item : items){
                subtotal += item.getTotal();
            }
            model.addAttribute("items", items);
            model.addAttribute("subtotal", subtotal);
            return "cart";
        }

    }

    @RequestMapping(value = "/login")
    public String viewLogin(){
        return "login";
    }

    @GetMapping({"/","/home"})
    public String home(Model model, HttpServletRequest req) {
        ArrayList<Book> books = new ArrayList<Book>(bookSer.getAll());
        model.addAttribute("books", books);
        return "home";
    }

    @GetMapping("/403")
    public String error() {
        return "Lỗi rồi :(";
    }
}
