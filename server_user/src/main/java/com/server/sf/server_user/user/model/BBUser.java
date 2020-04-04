package com.server.sf.server_user.user.model;


import com.server.sf.server_user.common.model.BaseBean;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "server_user")
@Inheritance(strategy=InheritanceType.JOINED)
public class BBUser  extends BaseBean {

	@Getter
	@Setter
	private String name;
	@Column(unique = true,nullable = true)
	@Getter
	@Setter
	private String phoneNumber;
	@Getter
	@Setter
	private String address;
	@Getter
	@Setter
	private String email;
	@Getter
	@Setter
	private String CountryCode;
	@Getter
	@Setter
	private String avatarImage;
	@Getter
	@Setter
	private String userBackgroundImage;
	@Getter
	@Setter
	private String userDesc;
	@Getter
	@Setter
	private String city;
	@Getter
	@Setter
	@Column(length = 3000)
	private String userDetailDesc;
	@Getter
	@Setter
	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL,targetEntity = BBToken.class)
	private BBToken bbToken;
	@Getter
	@Setter
	@ManyToMany(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER,targetEntity = BBRole.class)
	@JoinTable(name = "server_user_role", joinColumns = @JoinColumn(name = "user_id"))
	private Set<BBRole> roles;


	@Getter
	@Setter
	private boolean isLogin;

	@Getter
	@Setter
	private String password;


}
