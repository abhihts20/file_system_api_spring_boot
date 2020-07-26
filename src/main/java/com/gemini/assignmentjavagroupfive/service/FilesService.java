package com.gemini.assignmentjavagroupfive.service;

import com.gemini.assignmentjavagroupfive.dataModel.FileEntity;
import com.gemini.assignmentjavagroupfive.repository.FileRepository;
import com.gemini.assignmentjavagroupfive.exception.RecordNotFoundException;
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
            throw new RecordNotFoundException("No file exists of this given name");
        }
    }

    public FileEntity createOrUpdateFile(FileEntity fileEntity) throws RecordNotFoundException, IOException {
        Optional<FileEntity> updateEntity = fileRepository.findDistinctByFileName(fileEntity.getFileName());
//        if (updateEntity.isPresent()){
//            FileEntity newEntity1=updateEntity.get();
//            newEntity1.setFileContent(fileEntity.getFileContent());
//            newEntity1=fileRepository.save(newEntity1);
//            return newEntity1;
//        }
        File file = new File(fileEntity.getFileName());
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(fileEntity.getFileContent());
        fileEntity.setFilePath(file.getAbsolutePath());
        fileWriter.close();
        fileEntity = fileRepository.save(fileEntity);
        return fileEntity;


    }

    public void deleteFileByName(String fileName) throws RecordNotFoundException {
        Optional<FileEntity> file = fileRepository.findDistinctByFileName(fileName);
        if (file.isPresent()) {
            fileRepository.deleteDistinctByFileName(fileName);
        } else {
            throw new RecordNotFoundException("No file exists of this name");
        }
    }
}
