package com.example.BachEnd_Ses4.DTO.Device;

import com.example.BachEnd_Ses4.model.File.FileStorage;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Data
public class DeviceDTO {
    private Long id;
    private String deviceID;
    private String deviceName;
    private String username;
    private String storeName;
    private String groupName;
    private String area;
    private String[] fileName;
    private List<FileStorage> fileStorage;
}
