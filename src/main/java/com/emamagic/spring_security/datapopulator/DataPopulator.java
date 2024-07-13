package com.emamagic.spring_security.datapopulator;

import com.emamagic.spring_security.entity.User;
import com.emamagic.spring_security.repository.CustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DataPopulator implements CommandLineRunner {

    private final CustomRepository repo;


    @Override
    public void run(String... args) throws Exception {
        User user = new User("emamagic", "1234", "ema@gmail.com");
        repo.setUser(user);
    }
}
