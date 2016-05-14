package com.backend.opiniothon;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserEntity {
	
	public static final String USER_TYPE_DELIVERY_AGENT = "DeliveryGuy";
	public static final String USER_TYPE_CONSUMER = "Consumer";
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String userName;
	private String password;
	private String type;

	public UserEntity(String userName, String password, String type) {
		super();
		this.userName = userName;
		this.password = password;
		this.type = type;
	}
	public UserEntity() {
	
	
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(unique=true)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
