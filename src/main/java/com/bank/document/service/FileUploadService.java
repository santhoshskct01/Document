package com.bank.document.service;

import com.bank.document.client.ApiClient;
import com.bank.document.entity.Document;
import com.bank.document.entity.Post;
import com.bank.document.repository.FileUploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class FileUploadService {

    @Autowired
    private ApiClient apiClient;

    @Autowired
    FileUploadRepository fileUploadRepository;

    public void uploadFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String contentType = file.getContentType();
        byte[] fileContent = file.getBytes();
        Document savefile = new Document(fileName, contentType, fileContent);
        fileUploadRepository.save(savefile);
    }

    public  List<Document>  getAllFile() {
        try {
            List<Document> documents = fileUploadRepository.findAll();
            return documents;
        } catch(Exception e){

        }
        return null;
    }
    public Document getFile(String filename) throws Exception {
        Document doc = fileUploadRepository.findByName(filename)
                .orElseThrow(() -> new Exception("File not found for this id :: " + filename));;
        Post post = Optional.ofNullable(doc)
                .map(d-> apiClient.readPostById(String.valueOf(d.getId())))
                .orElseThrow();
        doc.setPost(post);
        return doc;
    }

    public Boolean deleteFile(String id) throws Exception {
        Document document = fileUploadRepository.findById(id)
                .orElseThrow(() -> new Exception("File not found for this id :: " + id));
        fileUploadRepository.delete(document);
        return Boolean.TRUE;
    }
}
