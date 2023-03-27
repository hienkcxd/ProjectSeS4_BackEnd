package com.example.BachEnd_Ses4.repositories.system;

import com.example.BachEnd_Ses4.model.System.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
