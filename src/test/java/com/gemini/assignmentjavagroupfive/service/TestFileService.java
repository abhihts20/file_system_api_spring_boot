package com.gemini.assignmentjavagroupfive.service;


import com.gemini.assignmentjavagroupfive.dataModel.FileEntity;
import com.gemini.assignmentjavagroupfive.repository.FileRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.junit.MockitoRule;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestFileService {

    @Autowired
    private FilesService filesService;

    @MockBean
    private FileRepository fileRepository;

    @Test
    public void testCreateFile() throws IOException {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setId((long) 1);
        fileEntity.setFileName("file2.txt");
        fileEntity.setFileContent("This is file 2");
        fileEntity.setFilePath("C:\\Users\\Abhinav\\Downloads\\assignment-java-group-five\\assignment-java-group-five\\file2.txt");
        Mockito.when(fileRepository.save(fileEntity)).thenReturn(fileEntity);
        assertEquals(filesService.createFile(fileEntity),fileEntity);
    }

    @Test
    public void testGetAllFiles(){
        List<FileEntity> files = new ArrayList<>();
        FileEntity fileEntity = new FileEntity();
        fileEntity.setId((long) 1);
        fileEntity.setFileName("file1.txt");
        fileEntity.setFileContent("This is file 1");
        fileEntity.setFilePath("C:\\Users\\Abhinav\\Downloads\\assignment-java-group-five\\assignment-java-group-five\\file1.txt");
        files.add(fileEntity);
        FileEntity fileEntity1 = new FileEntity();
        fileEntity1.setId((long) 2);
        fileEntity1.setFileName("file2.txt");
        fileEntity1.setFileContent("This is file 2");
        fileEntity1.setFilePath("C:\\Users\\Abhinav\\Downloads\\assignment-java-group-five\\assignment-java-group-five\\file2.txt");
        files.add(fileEntity1);
        Mockito.when(fileRepository.findAll()).thenReturn(files);
        assertEquals(filesService.getAllFiles(),files);
    }

    @Test
    public void testGetFileByName(){
        FileEntity fileEntity = new FileEntity();
        fileEntity.setId((long) 1);
        fileEntity.setFileName("file1.txt");
        fileEntity.setFileContent("This is file 1");
        fileEntity.setFilePath("C:\\Users\\Abhinav\\Downloads\\assignment-java-group-five\\assignment-java-group-five\\file1.txt");

        Mockito.when(fileRepository.findDistinctByFileName("file1.txt")).thenReturn(java.util.Optional.of(fileEntity));
        assertEquals(filesService.getFileByName("file1.txt"),fileEntity);
    }

    @Test
    public void testUpdateFileByName() throws IOException {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setId((long) 1);
        fileEntity.setFileName("file1.txt");
        fileEntity.setFileContent("This is file 1");
        fileEntity.setFilePath("C:\\Users\\Abhinav\\Downloads\\assignment-java-group-five\\assignment-java-group-five\\file1.txt");
        Mockito.when(fileRepository.findDistinctByFileName("file1.txt")).thenReturn(java.util.Optional.of(fileEntity));
        fileEntity.setFileContent("Updated Content");
        Mockito.when(fileRepository.save(fileEntity)).thenReturn(fileEntity);
        assertEquals(filesService.updateFile("file1.txt",fileEntity),fileEntity);
    }

    @Test
    public void testDeleteFileByName(){
        FileEntity fileEntity = new FileEntity();
        fileEntity.setId((long) 1);
        fileEntity.setFileName("file1.txt");
        fileEntity.setFileContent("This is file 1");
        fileEntity.setFilePath("C:\\Users\\Abhinav\\Downloads\\assignment-java-group-five\\assignment-java-group-five\\file1.txt");
        Mockito.when(fileRepository.findDistinctByFileName("file1.txt")).thenReturn(java.util.Optional.of(fileEntity));


    }
}
