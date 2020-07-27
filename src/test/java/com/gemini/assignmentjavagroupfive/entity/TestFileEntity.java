package com.gemini.assignmentjavagroupfive.entity;

import com.gemini.assignmentjavagroupfive.dataModel.FileEntity;
import com.gemini.assignmentjavagroupfive.repository.FileRepository;
import com.gemini.assignmentjavagroupfive.service.FilesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
public class TestFileEntity {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private FileRepository fileRepository;

    @Test
    public void testCreateFile(){
        FileEntity fileEntity=getFile();
        FileEntity saveInDb=testEntityManager.persist(fileEntity);
//        FileEntity getFromDb=fileRepository.findOne(saveInDb);

    }

    @Test
    public void testGetAllFile(){
        FileEntity fileEntity1=new FileEntity();
        fileEntity1.setId((long) 1);
        fileEntity1.setFileName("file1.txt");
        fileEntity1.setFileContent("This is file 1");
        fileEntity1.setFilePath("C:\\Users\\Abhinav\\Downloads\\assignment-java-group-five\\assignment-java-group-five\\file1.txt");
        FileEntity fileEntity2=new FileEntity();
        fileEntity2.setId((long) 2);
        fileEntity2.setFileName("file2.txt");
        fileEntity2.setFileContent("This is file 2");
        fileEntity2.setFilePath("C:\\Users\\Abhinav\\Downloads\\assignment-java-group-five\\assignment-java-group-five\\file2.txt");
        testEntityManager.persist(fileEntity1);
        testEntityManager.persist(fileEntity2);

        Iterable<FileEntity> allFilesFromDB=fileRepository.findAll();
        List<FileEntity> filesList = new ArrayList<>();

        for (FileEntity fileEntity:allFilesFromDB){
            filesList.add(fileEntity);
        }
        assertEquals(2,filesList.size());
    }

    @Test
    


    private FileEntity getFile(){
        FileEntity fileEntity=new FileEntity();
        fileEntity.setId((long) 1);
        fileEntity.setFileName("file1.txt");
        fileEntity.setFileContent("This is file 1");
        fileEntity.setFilePath("C:\\Users\\Abhinav\\Downloads\\assignment-java-group-five\\assignment-java-group-five\\file1.txt");
        return fileEntity;
    }

}
