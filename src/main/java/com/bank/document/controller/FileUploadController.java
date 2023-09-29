package com.bank.document.controller;

import com.bank.document.entity.Document;
import com.bank.document.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class FileUploadController {

    @Autowired
    FileUploadService  fileUploadService;

    @PostMapping(value = "/upload", consumes = {"multipart/form-data"})
    public String saveFile(@RequestPart("file") MultipartFile file){
        try {
            fileUploadService.uploadFile(file);
            return "File saved successfully";
        }

        catch(Exception e) {
            return "File not saved";
        }
    }

    @GetMapping( "/getall")
    public ResponseEntity<List<Document>> getFiles() {
        return ResponseEntity.ok().body(fileUploadService.getAllFile());
    }

    @GetMapping("/getbyname")
    public ResponseEntity<Document> getFile(@RequestParam String fileName) throws Exception {
        return ResponseEntity.ok().body(fileUploadService.getFile(fileName));
    }

    @DeleteMapping("/deletebyId/{id}")
    public Boolean deleteEmployee(@PathVariable(value = "id") String fileId) throws Exception {
        fileUploadService.deleteFile(fileId);
        return Boolean.TRUE;
    }

}
