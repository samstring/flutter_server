package com.example.demo.model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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

	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER,targetEntity = BBRole.class)
	private Set<BBRole> roles;



	private boolean isLogin;

	
	private String password;


}
