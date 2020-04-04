package com.server.sf.server_user.user.model;

import com.server.sf.server_user.common.model.BaseBean;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "server_authority")
@Data
public class BBAuthority extends BaseBean implements GrantedAuthority {

    private String name;

    @Override
    public String getAuthority() {
        return name;
    }

    @Override
    public String toString() {
        return "Authory{" +
                "id=" + getB_Id() +
                ", name='" + name + '\'' +
                '}';
    }
}
