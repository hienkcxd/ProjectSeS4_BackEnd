package com.example.BachEnd_Ses4.service.system;

import com.example.BachEnd_Ses4.model.System.Area;
import com.example.BachEnd_Ses4.model.System.User;
import com.example.BachEnd_Ses4.repositories.system.AreaRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AreaServiceImpl implements AreaService{

    @Autowired
    private AreaRepo areaRepo;

//    @PostConstruct
//    public void init(){
//        areaRepo.save(new Area(null, "area 1", null));
//        areaRepo.save(new Area(null, "area 2",null));
//        areaRepo.save(new Area(null, "area 3", null));
//        areaRepo.save(new Area(null, "area 4", null));
//        areaRepo.save(new Area(null, "area 5", null));
//    }
    @Override
    public void addArea(Area area) {
        areaRepo.save(area);
    }

    @Override
    public List<Area> areaList() {
        return areaRepo.findAll();
    }

    @Override
    public List<Area> areaByUsername(String username) {
        return areaRepo.findByUsername(username);
    }

    @Override
    public Area detailArea(Long id) {
        return areaRepo.findById(id).get();
    }

    @Override
    public void delArea(Long id) {
        areaRepo.delete(detailArea(id));
    }

    @Override
    public void update(Area area) {
        Area areaDb = detailArea(area.getId());
        areaDb.setUsername(area.getUsername());
        areaDb.setId(area.getId());
        areaDb.setAreaName(area.getAreaName());
        areaRepo.save(areaDb);
    }
}
