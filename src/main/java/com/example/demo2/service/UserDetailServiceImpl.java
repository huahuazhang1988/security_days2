package com.example.demo2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("自定义登入逻辑");
        if(!"admin".equals(username)){
            throw  new UsernameNotFoundException("username not exist");
        }
        String encode = passwordEncoder.encode("123");

        return new User(username,encode, AuthorityUtils.commaSeparatedStringToAuthorityList("admin,normal,ROLE_abc,ROLE_ABC,/main.html,/delete,/insert"));
    }
}
