package com.server.sf.server_user.user.model;




import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
public class BaseBean implements Comparable<BaseBean>, Serializable {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id")
//	private String id;

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "b_Id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "custom-id")
	@GenericGenerator(name = "custom-id", strategy = "com.server.sf.server_user.user.model.CustomIDGenerator")
	private String b_Id;
	
	@Version
	private Integer version;

	private boolean deleted;

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(updatable = false)
//	@org.hibernate.annotations.CreationTimestamp
	private Date dateCreated;



	@Override
	public int compareTo(BaseBean o) {
		return dateCreated.compareTo(o.dateCreated);
	}
}
