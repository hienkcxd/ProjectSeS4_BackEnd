package com.example.BachEnd_Ses4.service.map;

import com.example.BachEnd_Ses4.model.MapData.DeviceInGroup;
import com.example.BachEnd_Ses4.repositories.map.DeviceInGroupRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DeviceInGroupServiceImpl implements DeviceInGroupService{
    @Autowired
    private DeviceInGroupRepo deviceInGroupRepo;

    @Override
    public List<DeviceInGroup> findAll() {
        return deviceInGroupRepo.findAll();
    }


    @Override
    public List<DeviceInGroup> findByUsername(String username) {
        return deviceInGroupRepo.findByUsername(username);
    }

    @Override
    public void addDeviceInGroup(DeviceInGroup deviceInGroup) {
        deviceInGroupRepo.save(deviceInGroup);
    }

    @Override
    public DeviceInGroup detail(Long Id) {
        return deviceInGroupRepo.findById(Id).get();
    }

    @Override
    public DeviceInGroup detailByGroupName(String groupName) {
        return deviceInGroupRepo.findByGroupName(groupName);
    }

    @Override
    public void delete(Long Id) {
        deviceInGroupRepo.delete(detail(Id));
    }

    @Override
    public void update(DeviceInGroup deviceInGroup) {
        DeviceInGroup deviceInGroupDb = detail(deviceInGroup.getId());
        deviceInGroupDb.setId(deviceInGroup.getId());
        deviceInGroupDb.setFileName(deviceInGroup.getFileName());
        deviceInGroupRepo.save(deviceInGroupDb);
    }
}
