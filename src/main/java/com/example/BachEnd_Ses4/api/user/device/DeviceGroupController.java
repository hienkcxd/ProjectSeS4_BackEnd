package com.example.BachEnd_Ses4.api.user.device;

import com.example.BachEnd_Ses4.converter.MapConverter.DeviceInGroupConverter;
import com.example.BachEnd_Ses4.model.Device.Device;
import com.example.BachEnd_Ses4.model.Device.DeviceGroup;
import com.example.BachEnd_Ses4.model.MapData.DeviceInGroup;
import com.example.BachEnd_Ses4.service.device.DeviceGroupService;
import com.example.BachEnd_Ses4.service.device.DeviceService;
import com.example.BachEnd_Ses4.service.map.DeviceInGroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/device-group")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("http://localhost:4200")
//@PreAuthorize("hasAnyAuthority('ROLE_USER')")
public class DeviceGroupController {
    @Autowired
    private DeviceGroupService deviceGroupService;
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
    public List<DeviceGroup> findByUsername(){
        return deviceGroupService.findByUsername(getPrincipal());
    }

    @GetMapping("/{deviceId}")
    public DeviceGroup detailDevice(@PathVariable String deviceId){
        DeviceGroup deviceCur = deviceGroupService.detailGroup(Long.valueOf(deviceId));
        if(getPrincipal().equals(deviceCur.getUsername())){
            return deviceCur;
        }else {
            return (DeviceGroup) ResponseEntity.badRequest();
        }
    }

    @PostMapping("")
    public void addDevice(@RequestBody DeviceGroup device){
        device.setUsername(getPrincipal());
        deviceGroupService.addGroup(device);
        //save lên device in group
        DeviceInGroup ent = new DeviceInGroup();
        ent.setGroupName(device.getGroupName());
        ent.setUsername(getPrincipal());
        ent.setFileName("[]");
        ent.setDeviceName("[]");
        deviceInGroupService.addDeviceInGroup(ent);
    }

    @PutMapping("")
    public void updDesDevice(@RequestBody DeviceGroup device){
        DeviceGroup deviceCur = deviceGroupService.detailGroup(device.getId());
        if (getPrincipal().equals(deviceCur.getUsername())){
            deviceGroupService.update(device);
        }else {
            log.info("device controller - line 65: user khong co quyen update ten va khu vuwjc hoat dong thiet bi nay");
            ResponseEntity.badRequest();
        }
    }


    @DeleteMapping("/{deleteId}")
    public void deleteDevice(@PathVariable String deleteId){
        DeviceGroup deviceCur = deviceGroupService.detailGroup(Long.valueOf(deleteId));
        if (getPrincipal().equals(deviceCur.getUsername())){
            deviceGroupService.delete(Long.valueOf(deleteId));
        }else {
            log.info("device controller - line 95: user khong co quyen xoa device group nay");
            ResponseEntity.badRequest();
        }
    }
}
