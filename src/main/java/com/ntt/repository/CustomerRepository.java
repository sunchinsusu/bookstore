package com.ntt.repository;

import com.ntt.entity.Account;
import com.ntt.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findByAccount(Account account);
    Customer findByAccount_UserName(String username);
}
