
package com.kaituo.pms.config;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.kaituo.pms.securityhandler.MyAuthenctiationFailureHandler;
import com.kaituo.pms.securityhandler.MyAuthenctiationSuccessHandler;
import com.kaituo.pms.service.UserService;
import com.kaituo.pms.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@CrossOrigin(origins = "*", maxAge = 3600)
public class BrowerSecurityConfig extends WebSecurityConfigurerAdapter {
/*  @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;*/


    @Autowired
UserServiceImpl userService;
    @Autowired
    @Qualifier("dataSource")
    DataSource dataSource;

    @Autowired
    private MyAuthenctiationFailureHandler myAuthenctiationFailureHandler;
    @Autowired
    private MyAuthenctiationSuccessHandler myAuthenctiationSuccessHandler;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/authority/one/**").hasAnyRole("1","6")
                .antMatchers("/authority/two/**").hasAnyRole("2","6")
                .antMatchers("/authority/three/**").hasAnyRole("3","6")
                .antMatchers("/authority/four/**").hasAnyRole("4","6")
                .antMatchers("/authority/five/**").hasAnyRole("5","6")
                .antMatchers("/authority/all/**").hasAnyRole("6")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/user/login")//默认post
                .failureHandler(myAuthenctiationFailureHandler)
                .successHandler(myAuthenctiationSuccessHandler)
/*  .defaultSuccessUrl("/user/success")

.successForwardUrl("/user/success")*/

                .usernameParameter("username").passwordParameter("password").permitAll()
                .and()
                .rememberMe()                                   // 记住我相关配置
/*                .tokenRepository(persistentTokenRepository())   // 设置TokenRepository
                // 配置Cookie过期时间
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                // 配置UserDetailsService
                .userDetailsService(rememberMeServices())*/
                .and().logout().logoutSuccessUrl("/user/loginout").permitAll()
                .and()
                .csrf().disable();
    }
   @Bean
//密码加密
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider
                = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder());
        System.out.println(authProvider.toString());
        return authProvider;
    }
    @Override
//身份验证管理生成器

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
auth.authenticationProvider(authenticationProvider());
      try {
          auth.userDetailsService(userService);
      }catch (Exception e){
          e.printStackTrace();
      }

    }
/**
     * 返回 RememberMeServices 实例
     *
     * @return the remember me services*/


    @Bean
    public RememberMeServices rememberMeServices() {

        JdbcTokenRepositoryImpl rememberMeTokenRepository = new JdbcTokenRepositoryImpl();
        // 此处需要设置数据源，否则无法从数据库查询验证信息
        rememberMeTokenRepository.setDataSource(dataSource);
        // 此处的 key 可以为任意非空值(null 或 "")，单必须和起前面
        // rememberMeServices(RememberMeServices rememberMeServices).key(key)的值相同
        PersistentTokenBasedRememberMeServices rememberMeServices =
                new PersistentTokenBasedRememberMeServices("INTERNAL_SECRET_KEY", userService, rememberMeTokenRepository);

        // 该参数不是必须的，默认值为 "remember-me", 但如果设置必须和页面复选框的 name 一致
        rememberMeServices.setParameter("remember-me");
        return rememberMeServices;
    }


/*@Bean
public FilterRegistrationBean corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    // 设置你要允许的网站域名，如果全允许则设为 *
    config.addAllowedOrigin("*");
    // 如果要限制 HEADER 或 METHOD 请自行更改
    config.addAllowedHeader("*");
    config.addAllowedMethod("*");
    source.registerCorsConfiguration("/**", config);
    FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
    // 这个顺序很重要哦，为避免麻烦请设置在最前
    bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
    return bean;
}*/

@Bean
public FilterRegistrationBean corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.addAllowedOrigin("*");
    config.addAllowedHeader(CorsConfiguration.ALL);
    config.addAllowedMethod(CorsConfiguration.ALL);
    source.registerCorsConfiguration("/**", config);
    FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
    bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
    return bean;
}

}
