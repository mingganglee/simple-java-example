package com.authserver.service.impl;

import com.authserver.pojo.SecurityUser;
import com.authserver.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("my loadUserByUsername ==================== " + username);
        // com.buoyauth.data.model.User user = userService.findByUsername(username);
        // UserinfoDTO user = userService.findByUsername(username);

        // if (user == null) {
        //     throw new UsernameNotFoundException(username);
        // }

        String password = passwordEncoder.encode("123");

        return new SecurityUser(1L, "aaa", password, true, AuthorityUtils
                .commaSeparatedStringToAuthorityList("admin"));
    }

}
