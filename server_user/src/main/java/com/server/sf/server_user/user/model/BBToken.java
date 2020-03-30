package com.server.sf.server_user.user.model;





import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "b_token")
@Data
public class BBToken extends BaseBean {



	private String b_tokenString;//服务器自身的token，
	private String chat_tokenString;
	private String b_uploadString;//上传时的签名
	

}
