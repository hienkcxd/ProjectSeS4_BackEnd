package com.example.BachEnd_Ses4.api.admin.device;

import com.example.BachEnd_Ses4.DTO.Device.DeviceDTO;
import com.example.BachEnd_Ses4.converter.Device.DeviceDTOConverter;
import com.example.BachEnd_Ses4.model.Device.Device;
import com.example.BachEnd_Ses4.model.MapData.DeviceInGroup;
import com.example.BachEnd_Ses4.service.device.DeviceService;
import com.example.BachEnd_Ses4.service.file.FileStorageService;
import com.example.BachEnd_Ses4.service.map.DeviceInGroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/admin/device")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("http://localhost:4200")
//@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
public class AdminDeviceController {
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private DeviceInGroupService deviceInGroupService;


    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private DeviceDTOConverter converter;

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
    public List<Device> findByUsername(){
        return deviceService.findAll();
    }
    @GetMapping("/DeviceDTO")
    public List<DeviceDTO> findByUsernameDTO(){
        List<Device> ent = deviceService.findAll();
        return converter.ListEntityToDTO(ent);
    }
    @GetMapping("/dto={id}")
    public DeviceDTO findByUsernameV2(@PathVariable String id){
        Device ent =  deviceService.detail(Long.valueOf(id));
        DeviceDTO dto = converter.entityToDTO(ent);
        dto.setFileStorage(fileStorageService.findByFileName(dto.getFileName()));
        return dto;
    }

    @GetMapping("/device-no-group")
    public List<Device> deviceNoGroup(){
        return deviceService.deviceNoGroup(getPrincipal());
    }

    @GetMapping("/{deviceId}")
    public Device detailDevice(@PathVariable String deviceId){
        Device deviceCur = deviceService.detail(Long.valueOf(deviceId));
        if(getPrincipal().equals(deviceCur.getUsername())){
            return deviceCur;
        }else {
            return (Device) ResponseEntity.badRequest();
        }
    }
    @GetMapping("/group={groupName}")
    public String[] deviceName(@PathVariable String groupName){
        return deviceService.deviceInGroupGetDevice(groupName);
    }

    @PostMapping("")
    public DeviceDTO addDevice(@RequestBody DeviceDTO dto){
        Device device = converter.dtoToEntity(dto);
        if(device.getGroupName()==null){
            device.setGroupName("No_Group");
            deviceService.addDevice(device);
            return dto;
        }else {
            String fileName= deviceInGroupService.detailByGroupName(device.getGroupName()).getFileName();
            device.setFileName(fileName);
            deviceService.addDevice(device);
            String[] deviceName = deviceService.deviceInGroupGetDevice(dto.getGroupName());
            DeviceInGroup deviceInGroupCur = deviceInGroupService.detailByGroupName(dto.getGroupName());
            deviceInGroupCur.setDeviceName(Arrays.toString(deviceName));
            deviceInGroupService.update(deviceInGroupCur);
            return dto;
        }
    }
    @PutMapping("")
    public DeviceDTO updateDeviceDTO(@RequestBody DeviceDTO dto){
        Device device = converter.dtoToEntity(dto);
        if(getPrincipal().equals(device.getUsername())){
            deviceService.updateDevice(device);
            return dto;
        }else {
            return (DeviceDTO) ResponseEntity.badRequest();
        }
    }
    @PutMapping("/update-v2")
    public void updDesDevice(@RequestBody Device deviceUpdate){
        //Update file in device after accept update
        String fileName = deviceService.getFileNameByGroup(deviceUpdate.getGroupName());
        log.info("update v2 - file name: "+fileName);
        deviceUpdate.setFileName(fileName);
        Device deviceCur = deviceService.detail(deviceUpdate.getId());
        if(deviceCur.getGroupName().equals("No_Group")){
            deviceCur.setGroupName(deviceUpdate.getGroupName());
            //Khong can remove, add truc tiep
            DeviceInGroup newDeviceInGroup = deviceInGroupService.detailByGroupName(deviceUpdate.getGroupName());
            String adđevice = deviceService.addDeviceToGroup(deviceUpdate);
            newDeviceInGroup.setDeviceName(adđevice);
            deviceInGroupService.update(newDeviceInGroup);
        }else {
            //remove device from old group
            DeviceInGroup updateDeviceInGroup = deviceInGroupService.detailByGroupName(deviceCur.getGroupName());
            String deviceInGroup = deviceService.removeDeviceInGroup(deviceCur);
            updateDeviceInGroup.setDeviceName(deviceInGroup);
            deviceInGroupService.update(updateDeviceInGroup);

            //add device to new group
            DeviceInGroup newDeviceInGroup = deviceInGroupService.detailByGroupName(deviceUpdate.getGroupName());
            String addDevice = deviceService.addDeviceToGroup(deviceUpdate);
            newDeviceInGroup.setDeviceName(addDevice);
            deviceInGroupService.update(newDeviceInGroup);
        }

        if (getPrincipal().equals(deviceCur.getUsername())){
            deviceService.updateDesDevice(deviceUpdate);
        }else {
            log.info("device controller - line 65: user khong co quyen update ten va khu vuwjc hoat dong thiet bi nay");
            ResponseEntity.badRequest();
        }


    }


    @PutMapping("/active-device")
    public void updActiveDevice(@RequestBody Device device){
        Device deviceCur = deviceService.detail(device.getId());
        if (getPrincipal().equals(deviceCur.getUsername())){
            deviceService.updateActiveDevice(device);
        }else {
            log.info("device controller - line84: user khong co quyen active thiet bi nay");
            ResponseEntity.badRequest();
        }
    }

    @DeleteMapping("/{deleteId}")
    public void deleteDevice(@PathVariable String deleteId){
        Device deviceCur = deviceService.detail(Long.valueOf(deleteId));
        if (getPrincipal().equals(deviceCur.getUsername())){
            deviceService.delete(Long.valueOf(deleteId));
        }else {
            log.info("device controller - line 95: user khong co quyen xoa device nay");
            ResponseEntity.badRequest();
        }
    }
}
