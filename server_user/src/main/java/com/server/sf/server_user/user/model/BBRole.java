package com.server.sf.server_user.user.model;

//import org.springframework.security.core.GrantedAuthority;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "server_role")
@Data
public class BBRole extends BaseBean  implements GrantedAuthority {

    @Column(nullable = false)
    private String name;



    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + getB_Id() +
                ", name='" + name + '\'' +
                '}';
    }
}