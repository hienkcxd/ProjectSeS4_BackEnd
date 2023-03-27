package com.example.BachEnd_Ses4.service.map;

import com.example.BachEnd_Ses4.model.MapData.DeviceInGroup;

import java.util.List;

public interface DeviceInGroupService {
    public List<DeviceInGroup> findAll();

    public List<DeviceInGroup> findByUsername(String username);
    public void addDeviceInGroup(DeviceInGroup deviceInGroup);
    public DeviceInGroup detail(Long Id);
    public DeviceInGroup detailByGroupName(String groupName);
    public void delete(Long Id);
    public void update(DeviceInGroup deviceInGroup);
}
