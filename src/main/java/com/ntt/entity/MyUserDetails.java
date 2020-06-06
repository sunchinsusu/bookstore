package com.ntt.entity;

import java.util.ArrayList;
import java.util.List;

import com.ntt.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class MyUserDetails implements UserDetails{

	private static final long serialVersionUID = 1L;

	private Account account;
	private List<GrantedAuthority> authorities;

	public MyUserDetails(Account account) {
		this.account = account;
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		List<Role> roles = account.getRoles();
		for(Role role : roles) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		this.authorities = grantedAuthorities;
	}

	public Account getAccount() {
		return account;
	}

	@Override
	public List<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return account.getPassWord();
	}

	@Override
	public String getUsername() {
		return account.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
