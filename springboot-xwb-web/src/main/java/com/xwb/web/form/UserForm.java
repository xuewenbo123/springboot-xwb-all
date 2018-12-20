package com.xwb.web.form;

import java.io.Serializable;

public class UserForm implements Serializable{

    Long id;

    String userName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
