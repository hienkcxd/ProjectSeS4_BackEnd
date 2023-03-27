package com.example.BachEnd_Ses4.service.device;

import com.example.BachEnd_Ses4.model.Device.DeviceGroup;
import com.example.BachEnd_Ses4.repositories.device.DeviceGroupRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class DeviceGroupServiceImpl implements DeviceGroupService{
    @Autowired
    private DeviceGroupRepo deviceGroupRepo;
    @Override
    public List<DeviceGroup> findByUsername(String username) {
        return deviceGroupRepo.findByUsername(username);
    }


    @Override
    public List<DeviceGroup> findAll() {
        return deviceGroupRepo.findAll();
    }

    @Override
    public void addGroup(DeviceGroup deviceGroup) {
        deviceGroupRepo.save(deviceGroup);
    }

    @Override
    public void update(DeviceGroup deviceGroup) {
        DeviceGroup deviceGroupDb = detailGroup(deviceGroup.getId());
        deviceGroupDb.setId(deviceGroup.getId());
        deviceGroupDb.setGroupName(deviceGroup.getGroupName());
    }

    @Override
    public void delete(Long id) {
        deviceGroupRepo.delete(detailGroup(id));
    }

    @Override
    public DeviceGroup detailGroup(Long id) {
        return deviceGroupRepo.findById(id).get();
    }
}
