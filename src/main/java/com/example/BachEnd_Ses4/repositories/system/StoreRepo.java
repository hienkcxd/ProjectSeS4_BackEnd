package com.example.BachEnd_Ses4.repositories.system;

import com.example.BachEnd_Ses4.model.System.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StoreRepo extends JpaRepository<Store, Long> {
        @Query(value = "select s from Store s where s.username = ?1")
        public List<Store> findByUsername(String username);
}
