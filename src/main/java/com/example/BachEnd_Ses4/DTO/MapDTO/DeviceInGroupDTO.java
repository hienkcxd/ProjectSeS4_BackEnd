package com.example.BachEnd_Ses4.DTO.MapDTO;
import lombok.Data;

@Data
public class DeviceInGroupDTO {
    private Long id;
    private String username;
    private String groupName;
    private String[] fileName;
    private String[] deviceName;
}
