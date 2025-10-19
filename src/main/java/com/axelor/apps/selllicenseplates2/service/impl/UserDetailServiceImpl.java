package com.axelor.apps.selllicenseplates2.service.impl;


import com.axelor.apps.selllicenseplates2.model.Role;
import com.axelor.apps.selllicenseplates2.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Transactional
    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) {
        var user = userService.findByEmail(username);
        return new User(
                user.getEmail(),
                user.getPassword(),
                user.getEnabled(),
                true,
                true,
                true,
                getRoles(Stream.of(user.getRole()).collect(Collectors.toSet()))
        );
    }

    private Collection<? extends GrantedAuthority> getRoles(Set<Role> roleList) {
        Collection<? extends GrantedAuthority> authorities = roleList.stream()
                .map(e -> new SimpleGrantedAuthority("ROLE_" + e.getRole()))
                .toList();
        if(authorities.isEmpty()){
            authorities = Set.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return authorities;
    }
}
