package com.emamagic.spring_security.service;

import com.emamagic.spring_security.repository.CustomRepository;
import com.emamagic.spring_security.security.UserSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@RequiredArgsConstructor
@Service
public class CustomService implements UserDetailsService {

    private final CustomRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repo.getUser(username).map(UserSecurity::new).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
