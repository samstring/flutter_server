package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "server_video_user")
@Data
//@PrimaryKeyJoinColumn(name= "video_user_id")
public class BBVideoUser extends BBUser{
    private int likeCount;
    private int fansCount;
    private int followCount;
    private int videoCount;


//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "b_Id")
//    private String b_Id;

    @Column(unique = true)
    private String inviteString;

    @Column(unique = true,nullable = true)
    private String BangId;

}
