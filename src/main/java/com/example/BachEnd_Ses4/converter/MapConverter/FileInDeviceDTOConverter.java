package com.example.BachEnd_Ses4.converter.MapConverter;

import com.example.BachEnd_Ses4.DTO.MapDTO.FileInDeviceDTO;
import com.example.BachEnd_Ses4.model.MapData.DeviceInGroup;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FileInDeviceDTOConverter {
    public FileInDeviceDTO entityToiDTO(List<DeviceInGroup> deviceInGroupList){
        String[] device =null;
        FileInDeviceDTO dto = new FileInDeviceDTO();
        return dto;
    }
}
