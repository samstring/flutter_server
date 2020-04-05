package com.server.sf.server_user.user.model;

//import org.springframework.security.core.GrantedAuthority;

import com.server.sf.server_user.common.model.BaseBean;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "server_role")
@Data
public class BBRole extends BaseBean {

    @Column(nullable = false)
    private String name;

    @ManyToMany(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER,targetEntity = BBAuthority.class)
    private Set<BBAuthority> authorises;

    public void setName(String name) {
        this.name = name;
    }

}