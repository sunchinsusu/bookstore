package com.ntt.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "ntt_account")
public class Account implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "username")
	private String userName;
	
	@Column(name = "password")
	private String passWord;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ntt_account_role",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
	private List<Role> roles;

	public Account() {
		
	}
	
	public Account(String userName, String passWord, List<Role> roles) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.roles = roles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	
}
