package com.example.BachEnd_Ses4.service.file;

import com.example.BachEnd_Ses4.model.File.FileStorage;
import com.example.BachEnd_Ses4.repositories.file.FileStorageRepo;
import com.example.BachEnd_Ses4.repositories.system.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FileStorageServiceImpl implements FileStorageService{
    @Autowired
    private FileStorageRepo fileStorageRepo;


    @Override
    public void addFile(FileStorage fileStorage) {
        fileStorageRepo.save(fileStorage);
    }

    @Override
    public List<FileStorage> findAll() {
        return fileStorageRepo.findAll();
    }

    @Override
    public List<FileStorage> findByUsername(String username) {
        return fileStorageRepo.findByUsername(username);
    }

    @Override
    public List<FileStorage> findByFileName(String[] fileName) {
        List<FileStorage> listFileName = new ArrayList<>();
        for (String f: fileName) {
            FileStorage fileStorage =  fileStorageRepo.findByFileName(f);
            listFileName.add(fileStorage);
        }
        return listFileName;
    }

    @Override
    public FileStorage detail(Long id) {
        return fileStorageRepo.findById(id).get();
    }

    @Override
    public void update(FileStorage fileStorage) {
        FileStorage fileStorageDb = fileStorageRepo.findById(fileStorage.getId()).get();
        fileStorageDb.setId(fileStorage.getId());
        fileStorageDb.setFileName(fileStorage.getFileName());
        fileStorageRepo.save(fileStorageDb);
    }

    @Override
    public void delete(Long id) {
        fileStorageRepo.delete(detail(id));
    }
}
