package com.example.mvccrud.dao;

import com.example.mvccrud.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleDao extends JpaRepository<Role,Integer> {
    Optional<Role> findRoleByName(String name);
}
