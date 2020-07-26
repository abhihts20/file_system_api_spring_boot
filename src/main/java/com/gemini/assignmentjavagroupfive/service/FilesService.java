package com.gemini.assignmentjavagroupfive.service;

import com.gemini.assignmentjavagroupfive.controller.FileRestController;
import com.gemini.assignmentjavagroupfive.dataModel.FileEntity;
import com.gemini.assignmentjavagroupfive.repository.FileRepository;
import com.gemini.assignmentjavagroupfive.exception.RecordNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FilesService {

    private static final Logger logger= LoggerFactory.getLogger(FilesService.class);

    @Autowired
    FileRepository fileRepository;

    public List<FileEntity> getAllFiles() {
        List<FileEntity> fileEntities = fileRepository.findAll();
        if (fileEntities.size() > 0) {
            return fileEntities;
        } else {
            return new ArrayList<FileEntity>();
        }
    }

    public FileEntity getFileByName(String fileName) throws RecordNotFoundException {
        Optional<FileEntity> files = fileRepository.findDistinctByFileName(fileName);
        if (files.isPresent()) {
            return files.get();
        } else {
            logger.error("File with name {} not found",fileName);
            throw new RecordNotFoundException("No file exists of this given name");
        }
    }

    public FileEntity createFile(FileEntity fileEntity) throws RecordNotFoundException, IOException {
        File file = new File(fileEntity.getFileName());
        if (!file.exists()){
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(fileEntity.getFileContent());
            fileEntity.setFilePath(file.getAbsolutePath());
            fileWriter.close();
            fileEntity = fileRepository.save(fileEntity);
            return fileEntity;
        }
        else{
            logger.info("File with name {} already exists",file.getName());
            throw new RecordNotFoundException("File Already Exits");
        }
    }

    public FileEntity updateFile(String fileName,FileEntity fileEntity) throws RecordNotFoundException, IOException {
        Optional<FileEntity> updateEntity = fileRepository.findDistinctByFileName(fileName);
        if (updateEntity.isPresent()){
            FileEntity newEntity=updateEntity.get();
            File file=new File(fileEntity.getFileName());
            FileWriter fileWriter=new FileWriter(file);
            fileWriter.write(fileEntity.getFileContent());
            newEntity.setFilePath(file.getAbsolutePath());
            newEntity.setFileContent(fileEntity.getFileContent());
            newEntity=fileRepository.save(newEntity);
            fileWriter.close();
            return newEntity;
        }else
        {
            logger.error("File named {} not exists",fileName);
            throw new RecordNotFoundException("No file exists of this name");
        }

    }

    public void deleteFileByName(String fileName) throws RecordNotFoundException {
        Optional<FileEntity> file = fileRepository.findDistinctByFileName(fileName);
        FileEntity fileEntity=file.get();
        if (file.isPresent()) {
            File file1=new File(fileEntity.getFilePath());
            file1.delete();
            fileRepository.deleteById(fileEntity.getId());

        } else {
            logger.error("File named {} not found",fileName);
            throw new RecordNotFoundException("No file exists of this name");
        }
    }
}
