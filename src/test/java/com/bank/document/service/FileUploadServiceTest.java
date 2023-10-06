package com.bank.document.service;

import com.bank.document.DocumentApplicationTests;
import com.bank.document.entity.DocumentEntity;
import com.bank.document.repository.FileUploadRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.when;

//@RunWith(MockitoJUnitRunner.class)
public class FileUploadServiceTest extends DocumentApplicationTests {

    @Autowired
    FileUploadService fileUploadService;

    @MockBean
    FileUploadRepository fileUploadRepository;
    @Test
    public void deleteFileTest() throws Exception {
        DocumentEntity doc =  new DocumentEntity();
        doc.setId(1);
        doc.setName("sample");
        when(fileUploadRepository.findById("1")).thenReturn(Optional.of(doc));
        Boolean result = fileUploadService.deleteFile("1");
        Assert.assertEquals(result,true);
    }
}
