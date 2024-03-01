package com.tianyianquan.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDomain {
    private Integer id;

    private String username;

    private String password;

    public UserDomain(String username, String password) {
        this.username = username;
        this.password = password;
    }


    @Override
    public String toString() {
        return "UserDomain{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
