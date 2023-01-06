package com.example.demo2.config;

import com.example.demo2.handler.MyAccessDeniedHandler;
import com.example.demo2.handler.MyAuthenticationSuccessHandler;
import com.example.demo2.handler.MyAuthentificationFailureHandler;
import com.example.demo2.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;
    @Autowired
PersistentTokenRepository persistentTokenRepository;
    @Autowired
    UserDetailServiceImpl userDetailService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().usernameParameter("username123").passwordParameter("password123").
                loginPage("/showLogin").
                loginProcessingUrl("/goto").
               successForwardUrl("/toMain").
                     //  successHandler(new MyAuthenticationSuccessHandler("/main.html")).
                failureForwardUrl("/toError");//自定义登入页面
                  //  failureHandler(new MyAuthentificationFailureHandler("/error.html"));
        http.authorizeRequests()
                .antMatchers("/error.html").permitAll()
                .antMatchers("/showLogin").access("permitAll()")
                //.antMatchers("/main1.html").hasAnyRole("abc","ABC")
                .antMatchers("/main1.html").access("hasAnyRole('abc','ABC')")
               // .antMatchers("/main1.html").hasIpAddress("192.168.56.1")
               // .antMatchers("/main1.html").hasAnyAuthority("admin","huahua")
//                .antMatchers("/image/**").permitAll()
//                .mvcMatchers("/demo").servletPath("/aaa").permitAll()
               // .regexMatchers("/aa/demo").permitAll()
                .anyRequest().authenticated();//所有请求必须认证才能登入
                    //   .anyRequest().access("@myServiceImpl.hasPermission(request,authentication)");

        http.exceptionHandling().accessDeniedHandler(new MyAccessDeniedHandler());
        http.rememberMe().tokenRepository(persistentTokenRepository)
                        //.rememberMeParameter()
                                .tokenValiditySeconds(60)
                                        .userDetailsService(userDetailService);

      //  http.csrf().disable();//关闭防火墙类似的东西
        http.logout().logoutSuccessUrl("/login.html");
    }
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        jdbcTokenRepository.setCreateTableOnStartup(false);
        return jdbcTokenRepository;

    }

    @Bean
    public  PasswordEncoder  getPw(){
        return new BCryptPasswordEncoder();
    }


}
