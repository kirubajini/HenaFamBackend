package com.Henafam.model;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
	@Id
	private String id;
	@NotBlank
	@Size(max = 20)
	private String username;
	@NotBlank
	@Size(max = 50)
	@Email
	private String email;
	@NotBlank
	@Size(max = 120)
	private String password;
	
	private Set<String> updateroles;

	private String fullname;
	private String district;
	private String phonenumber;
	private String city;
	private String address;

	@DBRef
	private Set<Role> roles=new HashSet<>();
	public User() {

	}

	public User(String id,  String username,  String email,String password,  String fullname,String district, String phonenumber, String city, String address) {
	this.username=username;
	this.email=email;
	this.password=password;
	this.fullname = fullname;
	this.district = district;
	this.phonenumber = phonenumber;
	this.city = city;
	this.address = address;
	}

	public User(@NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
	@NotBlank @Size(max = 120) String password) {
	super();
	this.username = username;
	this.email = email;
	this.password = password;
	}


	public String getId() {
	return id;
	}
	public void setId(String id) {
	this.id = id;
	}
	public String getUsername() {
	return username;
	}
	public void setUsername(String username) {
	this.username = username;
	}
	public String getEmail() {
	return email;
	}
	public void setEmail(String email) {
	this.email = email;
	}
	public String getPassword() {
	return password;
	}
	public void setPassword(String password) {
	this.password = password;
	}
	public Set<String> getUpdateroles() {
	return updateroles;
	}
	public void setUpdateroles(Set<String> updateroles) {
	this.updateroles = updateroles;
	}
	public Set<Role> getRoles() {
	return roles;
	}

	public void setRoles(Set<Role> roles) {
	this.roles = roles;
	}


	public String getFullname() {
	return fullname;
	}


	public void setFullname(String fullname) {
	this.fullname = fullname;
	}


	public String getDistrict() {
	return district;
	}


	public void setDistrict(String district) {
	this.district = district;
	}


	public String getPhonenumber() {
	return phonenumber;
	}


	public void setPhonenumber(String phonenumber) {
	this.phonenumber = phonenumber;
	}


	public String getCity() {
	return city;
	}


	public void setCity(String city) {
	this.city = city;
	}


	public String getAddress() {
	return address;
	}


	public void setAddress(String address) {
	this.address = address;
	}

}

		
