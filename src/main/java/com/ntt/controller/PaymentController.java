//package com.ntt.controller;
//import javax.servlet.http.HttpServletRequest;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.paypal.api.payments.Links;
//import com.paypal.api.payments.Payment;
//import com.paypal.base.rest.PayPalRESTException;
//import com.ntt.config.PaypalPaymentIntent;
//import com.ntt.config.PaypalPaymentMethod;
//import com.ntt.service.PaypalService;
//import com.ntt.util.Util;
//
//@Controller
//public class PaymentController {
//
//    @Autowired
//    private PaypalService paypalService;
//
//    @PostMapping("/pay")
//    public String pay(HttpServletRequest request,@RequestParam("price") float price ){
//        String cancelUrl = Util.getBaseURL(request) + "/pay/cancel";
//        String successUrl = Util.getBaseURL(request) + "/pay/success";
//        try {
//            Payment payment = paypalService.createPayment(
//                    price,
//                    "USD",
//                    PaypalPaymentMethod.paypal,
//                    PaypalPaymentIntent.sale,
//                    "payment description",
//                    cancelUrl,
//                    successUrl);
//            for(Links links : payment.getLinks()){
//                if(links.getRel().equals("approval_url")){
//                    return "redirect:" + links.getHref();
//                }
//            }
//        } catch (PayPalRESTException e) {
//        }
//        return "redirect:/";
//    }
//
//    @GetMapping("/pay/cancel")
//    public String cancelPay(){
//        return "cancel";
//    }
//
//    @GetMapping("/pay/success")
//    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId){
//        try {
//            Payment payment = paypalService.executePayment(paymentId, payerId);
//            if(payment.getState().equals("approved")){
//                return "success";
//            }
//        } catch (PayPalRESTException e) {
//        }
//        return "redirect:/";
//    }
//}
