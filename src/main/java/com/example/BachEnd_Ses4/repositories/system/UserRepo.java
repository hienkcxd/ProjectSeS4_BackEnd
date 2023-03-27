package com.example.BachEnd_Ses4.repositories.system;

import com.example.BachEnd_Ses4.model.System.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    public User findByUserName(String userName);

}
