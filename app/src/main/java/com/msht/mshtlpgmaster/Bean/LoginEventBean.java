package com.msht.mshtlpgmaster.Bean;

public class LoginEventBean {

    private  UserLoginBean bean;

    public LoginEventBean(UserLoginBean s) {
        bean = s;
    }

    public UserLoginBean getUserLoginBean() {
        return bean;
    }
}
