package com.example.demo.model;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;


@Entity
@Table(name = "server_authority")
@Data
public class BBAuthority extends BaseBean {


    private String name;
//    @ManyToMany(mappedBy = "authoritys")
//    private Set<BBAuthority> role;

//    public void setName(String name) {
//        this.name = name;
//    }


}
