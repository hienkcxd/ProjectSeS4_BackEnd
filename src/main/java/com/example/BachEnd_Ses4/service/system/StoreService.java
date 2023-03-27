package com.example.BachEnd_Ses4.service.system;

import com.example.BachEnd_Ses4.model.System.Store;

import java.util.List;

public interface StoreService {
    public void addStore(Store store);
    public Store detailStore(Long id);
    public List<Store> listStoreByUsername(String username);
    public List<Store> listStore();
    public void update(Store store);
    public void delete(Long id);
}
