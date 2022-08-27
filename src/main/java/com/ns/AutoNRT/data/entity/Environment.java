package com.ns.autoNRT.data.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.ns.autoNRT.data.AbstractEntity;

import java.util.List;

@Entity
public class Environment extends AbstractEntity {

    @NotEmpty
    private String name = "";

    @NotEmpty
    private String domain = "";

    private Boolean authentication;

    private String username;

    private String password;

    private String token;




    public Boolean getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Boolean authentication) {
        this.authentication = authentication;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return name + " " + domain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }


}
