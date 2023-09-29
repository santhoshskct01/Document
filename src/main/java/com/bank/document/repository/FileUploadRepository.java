package com.bank.document.repository;


import com.bank.document.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileUploadRepository extends JpaRepository<Document,String> {

    Optional<Document> findByName(String filename);
}
