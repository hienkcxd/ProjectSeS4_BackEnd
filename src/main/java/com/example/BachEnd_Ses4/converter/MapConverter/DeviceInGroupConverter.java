package com.example.BachEnd_Ses4.converter.MapConverter;

import com.example.BachEnd_Ses4.DTO.MapDTO.DeviceInGroupDTO;
import com.example.BachEnd_Ses4.model.MapData.DeviceInGroup;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DeviceInGroupConverter {
    public DeviceInGroupDTO entityToDTO(DeviceInGroup deviceInGroup){
        DeviceInGroupDTO dto = new DeviceInGroupDTO();
        String[] arrFileName = null;
        String fileName = deviceInGroup.getFileName();
        arrFileName=fileName.substring(1, fileName.length()-1).split(", ");

        String[] arrDeviceName = null;
        String deviceName = deviceInGroup.getDeviceName();
        arrDeviceName=deviceName.substring(1, deviceName.length()-1).split(", ");

        dto.setId(deviceInGroup.getId());
        dto.setUsername(deviceInGroup.getUsername());
        dto.setGroupName(deviceInGroup.getGroupName());
        dto.setFileName(arrFileName);
        dto.setDeviceName(arrDeviceName);
        return dto;
    }


    public List<DeviceInGroupDTO> ListEntityToDTO(List<DeviceInGroup> deviceInGroupList){
        return deviceInGroupList.stream().map(x->entityToDTO(x)).collect(Collectors.toList());
    }

    public DeviceInGroup dtoToEntity(DeviceInGroupDTO dto){
        DeviceInGroup ent = new DeviceInGroup();
        String deviceName = new String();
        String fileName = new String();
        fileName = Arrays.toString(dto.getFileName());
        deviceName = Arrays.toString(dto.getDeviceName());
        ent.setId(dto.getId());
        ent.setUsername(dto.getUsername());
        ent.setGroupName(dto.getGroupName());
        ent.setDeviceName(deviceName);
        ent.setFileName(fileName);
        return ent;
    }

    public List<DeviceInGroup> ListDtoToEntity(List<DeviceInGroupDTO> dto){
        return dto.stream().map(x->dtoToEntity(x)).collect(Collectors.toList());
    }
}
