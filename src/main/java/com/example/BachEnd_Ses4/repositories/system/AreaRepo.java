package com.example.BachEnd_Ses4.repositories.system;

import com.example.BachEnd_Ses4.model.System.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaRepo extends JpaRepository<Area, Long> {
    @Query(value = "select u from Area u where u.username = ?1")
    public List<Area> findByUsername(String username);
}
