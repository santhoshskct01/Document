package com.bank.document.service;

import com.bank.document.client.ApiClient;
import com.bank.document.entity.PostEntity;
import com.bank.document.modal.Document;
import com.bank.document.entity.DocumentEntity;
import com.bank.document.modal.Post;
import com.bank.document.exception.NotFoundException;
import com.bank.document.exception.NotSaveException;
import com.bank.document.repository.FileUploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FileUploadService {

    @Autowired
    private ApiClient apiClient;

    @Autowired
    FileUploadRepository fileUploadRepository;

    public void uploadFile(MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            String contentType = file.getContentType();
            byte[] fileContent = file.getBytes();
            DocumentEntity savefile = new DocumentEntity(fileName, contentType, fileContent);
            fileUploadRepository.save(savefile);
        }catch(Exception e){
            throw new NotSaveException(e.getMessage());
         }
    }

    public  List<Document> getAllFile() {
        try {
            List<DocumentEntity> documents = fileUploadRepository.findAll();
            if(Optional.ofNullable(documents).isPresent()){
            return documents.stream().map(doc-> mapDocument(doc)).collect(Collectors.toList());
            }
        } catch(Exception e){
           throw new NotFoundException("File not found");
        }
        return new ArrayList<>();
    }

    private Document mapDocument(DocumentEntity doc) {
        Document document = new Document();
        document.setId(doc.getId());
        document.setName(doc.getName());
        document.setData(doc.getData());
        document.setType(doc.getType());
        document.setPost(getPostInformation(String.valueOf(document.getId())));
        return document;
    }

    private Post mapPost(PostEntity postEntity) {
        Post post = new Post();
        post.setId(postEntity.getId());
        post.setBody(postEntity.getBody());
        post.setTitle(postEntity.getTitle());
        post.setUserId(post.getUserId());
        return post;
    }

    public Document getFile(String filename) throws Exception {

        DocumentEntity doc = fileUploadRepository.findByName(filename)
                .orElseThrow(() -> new NotFoundException("File not found for this id :: " + filename));
        Document document =  mapDocument(doc);
        document.setPost(getPostInformation(String.valueOf(document.getId())));
        return document;
    }

    public Post getPostInformation(String postId){
        return Optional.ofNullable(postId)
                .map(d-> apiClient.readPostById(postId))
                .orElseThrow();
    }

    public Boolean deleteFile(String id) throws Exception {
        DocumentEntity document = fileUploadRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("File not found"));
        fileUploadRepository.delete(document);
        return Boolean.TRUE;
    }
}
