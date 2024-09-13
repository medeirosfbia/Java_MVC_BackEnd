package br.edu.fateccotia.bia.tasklist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_tokens")
public class Token {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JsonIgnore
	private User user;
	private String token;
	private Long expirationTime;
	
	
//	CONSTRUCTOR VAZIO
	public Token() {
		// TODO Auto-generated constructor stub
	}

	
//	CONSTRUCTOR WITH FIELDS
	public Token(Integer id, User user, String token, Long expirationTime) {
		super();
		this.id = id;
		this.user = user;
		this.token = token;
		this.expirationTime = expirationTime;
	}


// 	GETTERS AND SETTERS
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Long expirationTime) {
		this.expirationTime = expirationTime;
	}
	
	
	
}
