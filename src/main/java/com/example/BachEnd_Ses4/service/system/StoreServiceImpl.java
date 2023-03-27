package com.example.BachEnd_Ses4.service.system;

import com.example.BachEnd_Ses4.model.System.Store;
import com.example.BachEnd_Ses4.repositories.system.StoreRepo;
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
public class StoreServiceImpl implements StoreService{
    @Autowired
    private StoreRepo storeRepo;

//    @PostConstruct
//    public void init(){
//        storeRepo.save(new Store("store 1"));
//        storeRepo.save(new Store("store 2"));
//        storeRepo.save(new Store("store 3"));
//        storeRepo.save(new Store("store 4"));
//        storeRepo.save(new Store("store 5"));
//        storeRepo.save(new Store("store 6"));
//    }
    @Override
    public void addStore(Store store) {
        storeRepo.save(store);
    }

    @Override
    public Store detailStore(Long id) {
        return storeRepo.findById(id).get();
    }

    @Override
    public List<Store> listStoreByUsername(String username) {
        return storeRepo.findByUsername(username);
    }

    @Override
    public List<Store> listStore() {
        return storeRepo.findAll();
    }

    @Override
    public void update(Store store) {
        Store storeDb = storeRepo.getReferenceById(store.getId());
        storeDb.setStoreName(store.getStoreName());
        storeRepo.save(storeDb);
    }

    @Override
    public void delete(Long id) {
        storeRepo.delete(detailStore(id));
    }
}
