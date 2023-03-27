package com.example.BachEnd_Ses4.api.user.map;

import com.example.BachEnd_Ses4.DTO.MapDTO.DeviceInGroupDTO;
import com.example.BachEnd_Ses4.converter.MapConverter.DeviceInGroupConverter;
import com.example.BachEnd_Ses4.model.Device.Device;
import com.example.BachEnd_Ses4.model.Device.DeviceGroup;
import com.example.BachEnd_Ses4.model.MapData.DeviceInGroup;
import com.example.BachEnd_Ses4.service.device.DeviceService;
import com.example.BachEnd_Ses4.service.map.DeviceInGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/user/device-in-group")
@CrossOrigin("http://localhost:4200")
@Slf4j
//@PreAuthorize("hasAnyAuthority('ROLE_USER')")
public class DeviceInGroupController {
    @Autowired
    private DeviceInGroupService deviceInGroupService;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private DeviceInGroupConverter converter;
    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }

        return userName;
    }
    @GetMapping("")
    public List<DeviceInGroupDTO> findByUsername(){
        List<DeviceInGroup> ent = deviceInGroupService.findByUsername(getPrincipal());
        return converter.ListEntityToDTO(ent);
    }
    @GetMapping("/{idGroup}")
    public DeviceInGroupDTO findById(@PathVariable String idGroup){
        DeviceInGroup ent = deviceInGroupService.detail(Long.valueOf(idGroup));
        DeviceInGroupDTO dto = converter.entityToDTO(ent);
        return dto;
    }
    @PostMapping("")
    public DeviceInGroupDTO saveDeviceInGroup(@RequestBody DeviceInGroupDTO dto){
        String[] deviceName = deviceService.deviceInGroupGetDevice(dto.getGroupName());
        log.info(deviceName.toString());
        dto.setDeviceName(deviceName);
        DeviceInGroup ent = converter.dtoToEntity(dto);
        deviceInGroupService.addDeviceInGroup(ent);
        return dto;
    }
    @PutMapping("")
    public void updateContent(@RequestBody DeviceInGroupDTO dto){
        //cập nhập file phát trên device ingroup
        DeviceInGroupDTO dtoCur = converter.entityToDTO(deviceInGroupService.detailByGroupName(dto.getGroupName()));
        String[] fileName = dto.getFileName();
        dtoCur.setFileName(fileName);
        DeviceInGroup ent = converter.dtoToEntity(dtoCur);
        deviceInGroupService.update(ent);
        //cập nhập lại tất cả các device theo file đã thay đổi
        String[] deviceName = deviceService.deviceInGroupGetDevice(dto.getGroupName());
        for (int i = 0; i<deviceName.length; i++){
            Device device = deviceService.detailByDeviceName(deviceName[i]);
            device.setFileName(ent.getFileName());
            deviceService.updateDevice(device);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteDevice(@PathVariable String id){
        DeviceInGroup deviceCur = deviceInGroupService.detail(Long.valueOf(id));
        if (getPrincipal().equals(deviceCur.getUsername())){
            deviceInGroupService.delete(Long.valueOf(id));
        }else {
            log.info("device controller - line 95: user khong co quyen xoa device group nay");
            ResponseEntity.badRequest();
        }
    }
}
