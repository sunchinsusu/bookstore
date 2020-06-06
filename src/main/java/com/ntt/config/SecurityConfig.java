package com.ntt.config;
import com.ntt.repository.CustomerRepository;
import com.ntt.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    MyUserDetailsService myUserDetailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/cart").hasRole( "CUSTOMER")
                .antMatchers("/api").hasRole( "ADMIN")
                .antMatchers("/account").hasRole("CUSTOMER")
                .antMatchers("/add-to-cart").hasRole("CUSTOMER")
                .antMatchers("/payWithPaypal").hasRole("CUSTOMER")
                .antMatchers("/pay/success").hasRole("CUSTOMER")
                .antMatchers("/removeItem/**").hasRole("CUSTOMER")
                .antMatchers("/").permitAll()
                .antMatchers("/home").permitAll()
                .antMatchers("/search/**").permitAll()
                .antMatchers("/detail/**").permitAll();
        http.authorizeRequests().and().formLogin()//
                .loginProcessingUrl("/j_spring_security_login")//
                .loginPage("/login")//
                .defaultSuccessUrl("/")//
                .failureUrl("/login?message=error")//
                .usernameParameter("username")//
                .passwordParameter("password")
//                .successHandler(authenticationSuccessHandler)
//                .successForwardUrl()

                .and().logout().logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/login?message=logout");
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");


        //config security api
        http.authorizeRequests()
                .antMatchers("/api/book/create/list").hasRole("ADMIN")
                .antMatchers("/api/book/get").hasRole("CUSTOMER")
                .antMatchers("/api/book/get/**").hasRole("ADMIN")
                .antMatchers("/api/signin").permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder() ;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}

