package com.example.BachEnd_Ses4.repositories.device;

import com.example.BachEnd_Ses4.model.Device.DeviceGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeviceGroupRepo extends JpaRepository<DeviceGroup, Long> {
    @Query(value = "select s from DeviceGroup s where s.username = ?1")
    public List<DeviceGroup> findByUsername(String username);
}
