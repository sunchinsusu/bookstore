package com.ntt.service;


import com.ntt.entity.Customer;
import com.ntt.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ntt.entity.MyUserDetails;
import com.ntt.entity.Account;
import com.ntt.repository.AccountRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired AccountRepository accountRepo;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepo.findByUserName(username);
		if(account != null){
			return new MyUserDetails(account);
		}
		return null;
	}

}
