//package com.ntt.config;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.ntt.entity.*;
//import com.ntt.repository.BookRepository;
//import com.ntt.repository.CustomerRepository;
//import com.ntt.service.BookService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import com.ntt.entity.Account;
//import com.ntt.repository.RoleRepository;
//import com.ntt.repository.AccountRepository;
//
//@Component
//public class Init implements ApplicationListener<ContextRefreshedEvent>{
//    @Autowired
//    CustomerRepository customerRepo;
//
//    @Autowired
//    BookRepository bookRepo;
//
//	@Autowired
//    private AccountRepository accountRepo;
//
//    @Autowired
//    private RoleRepository roleRepo;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent arg0) {
//
//        if (roleRepo.findByName("ROLE_ADMIN") == null) {
//            roleRepo.save(new Role("ROLE_ADMIN"));
//        }
//
//        if (roleRepo.findByName("ROLE_CUSTOMER") == null) {
//            roleRepo.save(new Role("ROLE_CUSTOMER"));
//        }
//
//        if (accountRepo.findByUserName("admin@gmail.com") == null) {
//            Account admin = new Account();
//            admin.setUserName("admin@gmail.com");
//            admin.setPassWord(passwordEncoder.encode("123456"));
//            List<Role> roles = new ArrayList<>();
//            roles.add(roleRepo.findByName("ROLE_ADMIN"));
//            roles.add(roleRepo.findByName("ROLE_CUSTOMER"));
//            admin.setRoles(roles);
//            accountRepo.save(admin);
//        }
//
//        if (accountRepo.findByUserName("customer@gmail.com") == null) {
//
//            Customer c = new Customer();
//            c.setName("Nguyen Tien Thuat");
//            c.setAddress("Nam Giang, Nam Truc, Nam Dinh");
//            c.setTel("0969344172");
//            c.setEmail("tienthuat280798@gmail.com");
//
//            Account user = new Account();
//            user.setUserName("customer@gmail.com");
//            user.setPassWord(passwordEncoder.encode("123456"));
//            List<Role> roles = new ArrayList<>();
//            roles.add(roleRepo.findByName("ROLE_CUSTOMER"));
//            user.setRoles(roles);
//
//            c.setAccount(accountRepo.save(user));
//            customerRepo.save(c);
//        }
//
//        if(bookRepo.findByName("Cô gái đến từ hôm qua") == null){
//            Book b = new Book();
//            b.setDes("Sách hay");
//            b.setName("Cô gái đến từ hôm qua");
//            b.setAuthor("Nguyễn Nhật Ánh");
//            b.setCategory(Category.KiNangSong);
//            b.setPurchasePrice((float) 2.7);
//            b.setSalePrice((float) 3.2);
//            b.setSaleOff((float) 0.15);
//            b.setUrl("/images/book_2.jpg");
//            bookRepo.save(b);
//        }
//        if(bookRepo.findByName("Tôi thấy hoa vàng trên cỏ xanh") == null){
//            Book b = new Book();
//            b.setDes("Sách cực hay");
//            b.setName("Tôi thấy hoa vàng trên cỏ xanh");
//            b.setAuthor("Nguyễn Nhật Ánh");
//            b.setCategory(Category.TieuThuyet);
//            b.setPurchasePrice((float) 2.7);
//            b.setSalePrice((float) 3.2);
//            b.setSaleOff((float) 0.15);
//            b.setUrl("/images/book_1.jpg");
//            bookRepo.save(b);
//        }
//    }
//}
