package com.example.BachEnd_Ses4.converter.Device;

import com.example.BachEnd_Ses4.DTO.Device.DeviceDTO;
import com.example.BachEnd_Ses4.model.Device.Device;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DeviceDTOConverter {
    public DeviceDTO entityToDTO(Device ent){
        DeviceDTO dto = new DeviceDTO();
        String[] arrFileName = null;
        String fileName = ent.getFileName();
        arrFileName=fileName.substring(1, fileName.length()-1).split(", ");
        dto.setDeviceID(ent.getId().toString());
        dto.setDeviceName(ent.getDeviceName());
        dto.setArea(ent.getArea());
        dto.setGroupName(ent.getGroupName());
        dto.setStoreName(ent.getStoreName());
        dto.setId(ent.getId());
        dto.setUsername(ent.getUsername());
        dto.setFileName(arrFileName);
        return dto;
    }
    public List<DeviceDTO> ListEntityToDTO(List<Device> deviceList){
        return deviceList.stream().map(x->entityToDTO(x)).collect(Collectors.toList());
    }

    public Device dtoToEntity(DeviceDTO dto){
        Device ent = new Device();
        String fileName = new String();
        fileName = Arrays.toString(dto.getFileName());
        ent.setDeviceID(dto.getDeviceID());
        ent.setArea(dto.getArea());
        ent.setUsername(dto.getUsername());
        ent.setStoreName(dto.getStoreName());
        ent.setGroupName(dto.getGroupName());
        ent.setDeviceID(dto.getDeviceID());
        ent.setDeviceName(dto.getDeviceName());
        ent.setFileName(fileName);
        ent.setId(dto.getId());
        return ent;
    }

    public List<Device> ListDtoToEntity(List<DeviceDTO> dto){
        return dto.stream().map(x->dtoToEntity(x)).collect(Collectors.toList());
    }
}
