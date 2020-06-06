package com.ntt.controller;

import com.ntt.entity.Customer;
import com.ntt.entity.MyUserDetails;
import com.ntt.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Autowired
    CustomerRepository customerRepo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        MyUserDetails mud = (MyUserDetails) authentication.getPrincipal();
        Customer customer = customerRepo.findByAccount_UserName(mud.getUsername());
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("customer", customer);
        System.out.println(httpServletRequest.getPathTranslated());
        httpServletResponse.sendRedirect(httpServletRequest.getPathTranslated());

    }
}
