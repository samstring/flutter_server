package com.server.sf.server_user.user.model;

//import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "bb_role")
@Data
public class BBRole extends BaseBean{

    @Column(nullable = false)
    private String name;



    public void setName(String name) {
        this.name = name;
    }

//    @Override
//    public String getAuthority() {
//        return name;
//    }
//
//    @Override
//    public String toString() {
//        return "Role{" +
//                "id=" + getB_Id() +
//                ", name='" + name + '\'' +
//                '}';
//    }
}