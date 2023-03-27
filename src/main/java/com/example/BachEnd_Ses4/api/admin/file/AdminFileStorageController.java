package com.example.BachEnd_Ses4.api.admin.file;

import com.example.BachEnd_Ses4.model.File.FileStorage;
import com.example.BachEnd_Ses4.service.file.FileStorageService;
import com.example.BachEnd_Ses4.service.system.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/file-storage")
@CrossOrigin("http://localhost:4200")
@RequiredArgsConstructor
@Slf4j
//@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
public class AdminFileStorageController {
    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private UserService userService;

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
    public List<FileStorage> fileStorageList(){
        return fileStorageService.findAll();
    }

    @GetMapping("/fileId={fileId}")
    public FileStorage detailFile(@PathVariable String fileId){
        FileStorage detailFileCur = fileStorageService.detail(Long.valueOf(fileId));
        if(getPrincipal().equals(detailFileCur.getUsername())){
            return detailFileCur;
        }else {
            return (FileStorage) ResponseEntity.badRequest();
        }
    }

    @PostMapping("")
    public void saveFile(@RequestBody FileStorage fileStorage){
        fileStorage.setUsername(getPrincipal());
        fileStorageService.addFile(fileStorage);
    }

    @PutMapping("")
    public void updateFile(@RequestBody FileStorage fileStorage){
        FileStorage fileCheck = fileStorageService.detail(fileStorage.getId());
        if(getPrincipal().equals(fileCheck.getUsername())){
            fileStorageService.update(fileStorage);
        }else {
            log.info("user filestorage controller - line 67: user này không có quyền update file này");
            ResponseEntity.badRequest();
        }
    }

    @DeleteMapping("/fileId={fileId}")
    public void deleteFile(@PathVariable String fileId){
        FileStorage detailFile = fileStorageService.detail(Long.valueOf(fileId));
        if(getPrincipal().equals(detailFile.getUsername())){
            fileStorageService.delete(Long.valueOf(fileId));
        }else {
            log.info("user filestorage controller - line 79: user này không có quyền delete file naày");
            ResponseEntity.badRequest();
        }
    }

}
