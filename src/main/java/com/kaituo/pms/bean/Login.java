package com.kaituo.pms.bean;

/**
 * @author 张金行
 * @version 1.0
 * @Title: Login
 * @ProjectName pms
 * @Description:
 * @date 2018/9/3 00030:34
 */
public class Login {
/*    Collection<? extends GrantedAuthority> getAuthorities();

    Object getCredentials();

    Object getDetails();

    Object getPrincipal();

    boolean isAuthenticated();

    void setAuthenticated(boolean var1) throws IllegalArgumentException;*/
    private Object[] authorities;

    private String token;

    public Object[] getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Object[] authorities) {
        this.authorities = authorities;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
