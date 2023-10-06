package com.bank.document.repository;


import com.bank.document.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileUploadRepository extends JpaRepository<DocumentEntity,String> {

    Optional<DocumentEntity> findByName(String filename);
}
