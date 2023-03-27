package com.example.BachEnd_Ses4.service.device;

import com.example.BachEnd_Ses4.model.Device.Device;
import com.example.BachEnd_Ses4.model.MapData.DeviceInGroup;
import com.example.BachEnd_Ses4.repositories.device.DeviceRepo;
import com.example.BachEnd_Ses4.repositories.map.DeviceInGroupRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class DeviceServiceImpl implements DeviceService{
    @Autowired
    private DeviceRepo deviceRepo;
    @Autowired
    private DeviceInGroupRepo deviceInGroupRepo;
    @Override
    public void addDevice(Device device) {
        deviceRepo.save(device);
    }

    @Override
    public String[] deviceInGroupGetDevice(String groupName) {
        List<Device> deviceList = deviceRepo.findByGroupName(groupName);
        List<String> deviceName = new ArrayList<>();
        String[] arr = new String[deviceList.size()];
        for (Device d :deviceList) {
            deviceName.add(d.getDeviceName());
        }
        for (int i = 0; i < deviceName.size(); i++) {
            arr[i] = deviceName.get(i);
        }
        return arr;
    }

    @Override
    public String[] deviceInGroupGetFileName(String groupName) {
        String[] arrFileName = null;
        String fileName = deviceRepo.getFileName(groupName).getFileName();
        arrFileName=fileName.substring(1, fileName.length()-1).split(", ");
        return arrFileName;
    }

    @Override
    public String getFileNameByGroup(String groupName) {
        String fileName = deviceRepo.getFileName(groupName).getFileName();
        return fileName;
    }

    @Override
    public List<Device> findByUsername(String username) {
        return deviceRepo.findByUsername(username);
    }

    @Override
    public List<Device> findByStore(String username, String storeName) {
        return deviceRepo.findByStore(username, storeName);
    }

    @Override
    public List<Device> findAll() {
        return deviceRepo.findAll();
    }

    @Override
    public List<Device> deviceNoGroup(String username) {
        return deviceRepo.deviceNoGroup(username);
    }

    @Override
    public Device detail(Long id) {
        return deviceRepo.findById(id).get();
    }

    @Override
    public Device detailByDeviceName(String deviceName) {
        return deviceRepo.findByDeviceName(deviceName);
    }

    @Override
    public void updateDesDevice(Device device) {
        Device deviceDb = detail(device.getId());
        deviceDb.setDeviceName(device.getDeviceName());
        deviceDb.setFileName(device.getFileName());
        deviceDb.setGroupName(device.getGroupName());
        deviceRepo.save(deviceDb);
    }

    @Override
    public void addGroupToDevice(Device device) {
        Device deviceDb = detail(device.getId());
        deviceDb.setGroupName(device.getGroupName());
        deviceRepo.save(deviceDb);
    }

    @Override
    public void updateActiveDevice(Device device) {
        Device deviceDb = detail(device.getId());
        deviceRepo.save(deviceDb);
    }

    @Override
    public void updateDevice(Device device) {
        Device deviceDb = detail(device.getId());
        deviceDb.setId(device.getId());
        deviceDb.setDeviceID(device.getDeviceID());
        deviceDb.setArea(device.getArea());
        deviceDb.setDeviceName(device.getDeviceName());
        deviceDb.setFileName(device.getFileName());
        deviceDb.setStoreName(device.getStoreName());
        deviceDb.setGroupName(device.getGroupName());
        deviceRepo.save(deviceDb);
    }

    @Override
    public void updateSchedule(Device device) {
        Device deviceDb = detail(device.getId());
        deviceRepo.save(deviceDb);
    }

    @Override
    public void delete(Long id) {
        deviceRepo.delete(detail(id));
    }


    @Override
    public String removeDeviceInGroup(Device deviceRemove) {
        String[] arrDeviceName = null;
        String deviceName = deviceInGroupRepo.findByGroupName(deviceRemove.getGroupName()).getDeviceName();
        arrDeviceName=deviceName.substring(1, deviceName.length()-1).split(", ");
        String[] newArr = new String[arrDeviceName.length-1];
        log.info("device khi la string " +deviceName);
        log.info("kieu cua list device" +arrDeviceName[0]);
        String deviceNameCur = deviceRemove.getDeviceName();

        for(int i=0, k=0;i<arrDeviceName.length;i++){
            if(!deviceNameCur.equals(arrDeviceName[i])){
                newArr[k]=arrDeviceName[i];
                k++;
            }
        }
        log.info("device sau khi remove " +Arrays.toString(newArr));
        return Arrays.toString(newArr);
    }

    @Override
    public String addDeviceToGroup(Device deviceUpdate) {
        String[] arrDeviceName = null;
        String deviceName = deviceInGroupRepo.findByGroupName(deviceUpdate.getGroupName()).getDeviceName();
        arrDeviceName=deviceName.substring(1, deviceName.length()-1).split(", ");
        String deviceNameCur = deviceUpdate.getDeviceName();
        String[] newArr = new String[arrDeviceName.length+1];

        if(arrDeviceName.length==1&&arrDeviceName[0].equals("")){
            arrDeviceName[0]=deviceNameCur;
            return Arrays.toString(arrDeviceName);
        }else {
            for(int i=0, k=0;i<arrDeviceName.length;i++){
                newArr[k]=arrDeviceName[i];
                k++;
            }
            newArr[arrDeviceName.length]=deviceNameCur;
            return Arrays.toString(newArr);
        }
    }
}
