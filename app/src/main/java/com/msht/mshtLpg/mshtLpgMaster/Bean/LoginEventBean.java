package com.msht.mshtLpg.mshtLpgMaster.Bean;

public class LoginEventBean {

    private  UserLoginBean bean;

    public LoginEventBean(UserLoginBean s) {
        bean = s;
    }

    public UserLoginBean getUserLoginBean() {
        return bean;
    }
}
