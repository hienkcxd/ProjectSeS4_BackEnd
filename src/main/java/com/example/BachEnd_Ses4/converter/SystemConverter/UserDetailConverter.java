package com.example.BachEnd_Ses4.converter.SystemConverter;

import com.example.BachEnd_Ses4.DTO.MapDTO.DeviceInGroupDTO;
import com.example.BachEnd_Ses4.DTO.SystemDTO.UserDetailDTO;
import com.example.BachEnd_Ses4.model.MapData.DeviceInGroup;
import com.example.BachEnd_Ses4.model.System.User;
import com.example.BachEnd_Ses4.service.device.DeviceService;
import com.example.BachEnd_Ses4.service.system.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDetailConverter {
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private StoreService storeService;
    public UserDetailDTO entityToDTO(User user){
        UserDetailDTO dto = new UserDetailDTO();
        Long deviceQuantity = Long.valueOf(deviceService.findByUsername(user.getUserName()).size());
        Long storeQuantity = Long.valueOf(storeService.listStoreByUsername(user.getUserName()).size());
        dto.setId(user.getId());
        dto.setUsername(user.getUserName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setStoreQuantity(storeQuantity);
        dto.setDeviceQuantity(deviceQuantity);
        return dto;
    }

    public List<UserDetailDTO> ListEntityToDTO(List<User> ent){
        return ent.stream().map(x->entityToDTO(x)).collect(Collectors.toList());
    }
}
