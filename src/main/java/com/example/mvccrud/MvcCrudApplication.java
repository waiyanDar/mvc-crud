package com.example.mvccrud;

import com.example.mvccrud.dao.RoleDao;
import com.example.mvccrud.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

import javax.sound.midi.Soundbank;
import java.util.List;

@SpringBootApplication
public class MvcCrudApplication {

    @Autowired
    private RoleDao roleDao;

    @Transactional
    @Bean @Profile("test")
    public ApplicationRunner runner() {
        return r -> {
            Role role1 = new Role();
            role1.setName("ROLE_USER");
            Role role2 = new Role("ROLE_ADMIN");
            roleDao.save(role1);
            roleDao.save(role2);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(MvcCrudApplication.class, args);
    }

}
