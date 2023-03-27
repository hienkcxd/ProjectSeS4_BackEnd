package com.example.BachEnd_Ses4.repositories.device;

import com.example.BachEnd_Ses4.model.Device.Device;
import com.example.BachEnd_Ses4.model.MapData.DeviceInGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeviceRepo extends JpaRepository<Device, Long> {
    public List<Device> findByUsername(String username);

    @Query(value = "select s from Device s where s.groupName= 'No_Group' and s.username = ?1")
    public List<Device> deviceNoGroup(String username);

    @Query(value = "select s from Device s where s.groupName = ?1")
    public List<Device> findByGroupName(String groupName);
    @Query(value = "select s from DeviceInGroup s where s.groupName = ?1")
    public DeviceInGroup getFileName(String groupName);
    @Query(value = "select s from Device s where s.username = ?1 and s.storeName = ?2")
    public List<Device> findByStore(String username, String storeName);

    @Query(value = "select s from Device s where s.deviceName = ?1")
    public Device findByDeviceName(String deviceName);
}
