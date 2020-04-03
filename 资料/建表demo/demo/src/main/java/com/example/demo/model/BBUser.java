package com.example.demo.model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "server_user")
@Inheritance(strategy=InheritanceType.JOINED)
@Data
public class BBUser  extends BaseBean{



	private String name;
	@Column(unique = true,nullable = true)
	private String phoneNumber;
	private String address;
	private String email;
	private String CountryCode;
	private String avatarImage;
	private String userBackgroundImage;
	private String userDesc;
	private String city;
	@Column(length = 3000)
	private String userDetailDesc;
	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL,targetEntity = BBToken.class)
	private BBToken bbToken;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,targetEntity = BBRole.class)
	@JoinTable(name = "server_user_role", joinColumns = @JoinColumn(name = "user_id"))
	private List<BBRole> authorities;



	private boolean isLogin;

	
	private String password;


}
