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
            throw new RecordNotFoundException("File Already Exits");
        }
    }

    public FileEntity updateFile(String fileName) throws RecordNotFoundException, IOException {
        Optional<FileEntity> updateEntity = fileRepository.findDistinctByFileName(fileName);
        if (updateEntity.isPresent()){
            FileEntity newEntity1=updateEntity.get();
            File file=new File(newEntity1.getFilePath());
            FileWriter fileWriter=new FileWriter(file);
            fileWriter.write(newEntity1.getFileContent());
            newEntity1.setFilePath(file.getAbsolutePath());
            newEntity1.setFileContent(newEntity1.getFileContent());
            newEntity1=fileRepository.save(newEntity1);
            fileWriter.close();
            return newEntity1;
        }else
        {
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
            throw new RecordNotFoundException("No file exists of this name");
        }
    }
}
