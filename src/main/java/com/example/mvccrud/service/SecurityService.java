package com.example.mvccrud.service;

import com.example.mvccrud.dao.RoleDao;
import com.example.mvccrud.dao.UserDao;
import com.example.mvccrud.entity.Role;
import com.example.mvccrud.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SecurityService {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;

    public SecurityService(UserDao userDao, RoleDao roleDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void signUp(User user){
        Role role= new Role("ROLE_USER");
        Role userRole= roleDao.findRoleByName("ROLE_USER").orElse(role);
        user.addRole(userRole);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }
}
