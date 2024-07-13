package com.emamagic.spring_security.repository;

import com.emamagic.spring_security.entity.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Optional;

@Repository
public class CustomRepository {

    private final HashMap<String, User> store = new HashMap<>();

    public Optional<User> setUser(User user) {
        store.put(user.getEmail(), user);
        return getUser(user.getEmail());
    }

    public Optional<User> getUser(String email) {
        return Optional.of(store.get(email));
    }




}
