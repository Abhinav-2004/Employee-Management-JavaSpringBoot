package com.demo.employeeManagement.entities;

import java.security.PrivateKey;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Role implements GrantedAuthority{
	@Id
	private int id;
	private String name;
	
	@ManyToMany(mappedBy = "roles")
	@JsonIgnore  // Prevent infinite recursion inside users because each user contains roles and each role again contains users
	private Set<Users> users;
	public Role(int id, String name, Set<Users> users) {
		super();
		this.id = id;
		this.name = name;
		this.users = users;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Users> getUsers() {
		return users;
	}
	public void setUsers(Set<Users> users) {
		this.users = users;
	}
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return name;
	}
	
	
}
