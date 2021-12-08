package com.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
