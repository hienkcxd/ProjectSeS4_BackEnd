package com.example.BachEnd_Ses4.service.file;

import com.example.BachEnd_Ses4.model.File.FileStorage;

import java.util.List;

public interface FileStorageService {
    public void addFile(FileStorage fileStorage);
    public List<FileStorage> findAll();
    public List<FileStorage> findByUsername(String username);

    public List<FileStorage> findByFileName(String[] fileName);

    public FileStorage detail(Long id);
    public void update(FileStorage fileStorage);
    public void delete(Long id);
}
