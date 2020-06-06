package com.ntt.controller;

import com.ntt.entity.Account;
import com.ntt.entity.Customer;
import com.ntt.entity.Role;
import com.ntt.repository.AccountRepository;
import com.ntt.repository.CustomerRepository;
import com.ntt.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AccountRepository accountRepo;

    @Autowired
    RoleRepository roleRepo;

    @Autowired
    CustomerRepository customerRepo;
    @RequestMapping("/registry")
    public RedirectView registry(@RequestParam(name = "registry_name") String name,
                                 @RequestParam(name = "registry_address") String address,
                                 @RequestParam(name = "registry_tel")String tel,
                                 @RequestParam(name = "registry_email")String email,
                                 @RequestParam(name = "registry_password")String password,
                                RedirectAttributes redirectAttributes){
        Account a = new Account();
        a.setUserName(email);
        a.setPassWord(passwordEncoder.encode(password));
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(roleRepo.findByName("ROLE_CUSTOMER"));
        a.setRoles(roles);
        accountRepo.save(a);
        Customer c = new Customer();
        c.setName(name);
        c.setAccount(a);
        c.setAddress(address);
        c.setTel(tel);
        c.setEmail(email);
        customerRepo.save(c);
        RedirectView rv= new RedirectView("/login");
        return rv;
    }
}
