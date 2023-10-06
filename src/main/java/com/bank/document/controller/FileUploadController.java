package com.bank.document.controller;

import com.bank.document.modal.Document;
import com.bank.document.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/* File Upload Controller*/
@RestController
public class FileUploadController {

    @Autowired
    FileUploadService  fileUploadService;

    /* File Upload */
    @PostMapping(value = "/upload", consumes = {"multipart/form-data"})
    public String saveFile(@RequestPart("file") MultipartFile file){
            fileUploadService.uploadFile(file);
            return "File saved successfully";
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
