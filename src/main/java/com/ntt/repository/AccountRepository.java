package com.ntt.repository;

import com.ntt.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ntt.entity.Account;

import java.nio.file.attribute.UserPrincipal;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{
	Account findByUserName(String userName);
}
