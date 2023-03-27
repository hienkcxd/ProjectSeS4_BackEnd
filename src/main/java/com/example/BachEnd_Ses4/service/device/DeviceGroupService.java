package com.example.BachEnd_Ses4.service.device;

import com.example.BachEnd_Ses4.model.Device.DeviceGroup;

import java.util.List;

public interface DeviceGroupService {
    public List<DeviceGroup> findByUsername(String username);
    public List<DeviceGroup> findAll();

    public void addGroup(DeviceGroup deviceGroup);
    public void update(DeviceGroup deviceGroup);
    public void delete(Long id);
    public DeviceGroup detailGroup(Long id);
}
