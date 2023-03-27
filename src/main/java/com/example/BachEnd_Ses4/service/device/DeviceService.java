package com.example.BachEnd_Ses4.service.device;

import com.example.BachEnd_Ses4.model.Device.Device;
import com.example.BachEnd_Ses4.model.MapData.DeviceInGroup;

import java.util.ArrayList;
import java.util.List;

public interface DeviceService {
    public void addDevice(Device device);

    public String[] deviceInGroupGetDevice(String groupName);
    public String[] deviceInGroupGetFileName(String groupName);

    public String getFileNameByGroup(String groupName);
    public List<Device> findByUsername(String username);
    public List<Device> findByStore(String username, String storeName);
    public List<Device> findAll();
    public List<Device> deviceNoGroup(String username);
    public Device detail(Long id);
    public Device detailByDeviceName(String deviceName);
    public void updateDesDevice(Device device);

    public void addGroupToDevice(Device device);
    public void updateActiveDevice(Device device);

    public void updateDevice(Device device);

    public void updateSchedule(Device device);
    public void delete(Long id);

    public String removeDeviceInGroup(Device deviceRemove);
    public String addDeviceToGroup(Device deviceUpdate);
}
