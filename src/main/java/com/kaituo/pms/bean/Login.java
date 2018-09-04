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
    private Object details;
    private boolean authenticated;
    private Object principal;
    private Object credentials;
    private String name;

    public Object[] getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Object[] authorities) {
        this.authorities = authorities;
    }

    public Object getDetails() {
        return details;
    }

    public void setDetails(Object details) {
        this.details = details;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public Object getPrincipal() {
        return principal;
    }

    public void setPrincipal(Object principal) {
        this.principal = principal;
    }

    public Object getCredentials() {
        return credentials;
    }

    public void setCredentials(Object credentials) {
        this.credentials = credentials;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
