package com.example.demo2.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private String url;

    public MyAuthenticationSuccessHandler(String url) {
        this.url = url;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
             User user=(User) authentication.getPrincipal();
        System.out.println(request.getRemoteAddr());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        Collection<GrantedAuthority> authorities = user.getAuthorities();
        System.out.println(authorities);

        response.sendRedirect(url);
    }
}
