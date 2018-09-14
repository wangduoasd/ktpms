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
    private String userName;
    private Integer integral;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Login(Object[] authorities, String token, String userName, Integer integral) {
        this.authorities = authorities;
        this.token = token;
        this.userName = userName;
        this.integral = integral;
    }

    public Login() {
    }
}
