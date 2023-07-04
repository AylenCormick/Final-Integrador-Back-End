package com.example.FinalIntegradorBackEnd.services;

import com.example.FinalIntegradorBackEnd.entities.AppUser;
import com.example.FinalIntegradorBackEnd.entities.Rol;
import com.example.FinalIntegradorBackEnd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private UserRepository userRepository;

    @Autowired
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void run(ApplicationArguments args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode("juan123");
        BCryptPasswordEncoder passwordEncoder2 = new BCryptPasswordEncoder();
        String hashedPassword2 = passwordEncoder2.encode("ana456");
        userRepository.save(new AppUser("Juan", "juancito", "juan@finalback.com", hashedPassword, Rol.USER));
        userRepository.save(new AppUser("Ana", "anita", "anita@finalback.com", hashedPassword2, Rol.ADMIN));
    }
}
