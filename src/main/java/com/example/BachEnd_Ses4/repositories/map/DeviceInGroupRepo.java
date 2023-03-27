package com.example.BachEnd_Ses4.repositories.map;

import com.example.BachEnd_Ses4.model.MapData.DeviceInGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeviceInGroupRepo extends JpaRepository<DeviceInGroup, Long> {
    public List<DeviceInGroup> findByUsername(String username);
    @Query(value = "select s from DeviceInGroup s where s.groupName =? 1")
    public DeviceInGroup findByGroupName(String groupName);
}
