package com.restful.webservice.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.restful.webservice.post.Post;


@Entity
public class User {
	
	@Id
	@GeneratedValue
	//@JsonProperty("Id")
	private Integer id;
	//@JsonProperty("Name")
	private String name;
	//@JsonProperty("Date Of Birth")
	private Date dob;
	
	@OneToMany(mappedBy="user")
	private List<Post> post;
	
	public User() {
		super();
	}
	public User(Integer id, String name, Date dob, List<Post> post) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.post = post;
	}
	public List<Post> getPost() {
		return post;
	}
	public void setPost(List<Post> post) {
		this.post = post;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", dob=" + dob + ", post=" + post + "]";
	}
	
	
	
	

}
