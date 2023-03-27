package com.example.BachEnd_Ses4.service.system;

import com.example.BachEnd_Ses4.model.System.Area;

import java.util.List;

public interface AreaService {
    public void addArea(Area area);
    public List<Area> areaList();
    public List<Area> areaByUsername(String username);
    public Area detailArea(Long id);
    public void delArea(Long id);
    public void update(Area area);
}
