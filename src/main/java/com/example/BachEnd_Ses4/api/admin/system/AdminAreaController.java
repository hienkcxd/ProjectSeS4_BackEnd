package com.example.BachEnd_Ses4.api.admin.system;

import com.example.BachEnd_Ses4.model.System.Area;
import com.example.BachEnd_Ses4.service.system.AreaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/area")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
@Slf4j
//@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
public class AdminAreaController {
    @Autowired
    private AreaService areaService;

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
    public List<Area> listArea(){
        return areaService.areaList();
    }

    @GetMapping("/{id}")
    public Area detailArea(@PathVariable String id){
        Area areaCur = areaService.detailArea(Long.valueOf(id));
        if(areaCur!=null){
            return areaService.detailArea(Long.valueOf(id));
        }else {
            return (Area) ResponseEntity.badRequest();
        }
    }

    @PostMapping("")
    public Area addArea(@RequestBody Area area){
        area.setUsername(getPrincipal());
        areaService.addArea(area);
        return area;
    }

    @PutMapping("")
    public void updArea(@RequestBody Area area){
        Area areaCur = areaService.detailArea(area.getId());
        if (areaCur!=null){
            area.setUsername(getPrincipal());
            areaService.update(area);
        }else {
            ResponseEntity.badRequest();
        }
    }

    @DeleteMapping("{id}")
    public void deleteArea(@PathVariable String id){
        Area areaCur = areaService.detailArea(Long.valueOf(id));
        if(areaCur.getUsername().equals(getPrincipal())){
            areaService.delArea(Long.valueOf(id));
        }else {
            ResponseEntity.badRequest();
        }
    }
}
