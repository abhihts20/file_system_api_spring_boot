package com.gemini.assignmentjavagroupfive.controller;


import com.gemini.assignmentjavagroupfive.dataModel.FileEntity;
import com.gemini.assignmentjavagroupfive.service.FilesService;
import com.gemini.assignmentjavagroupfive.exception.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.sax.SAXResult;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileRestController {
    @Autowired
    FilesService filesService;

    @GetMapping("/")
    public ResponseEntity<List<FileEntity>> getAllFile() {
        List<FileEntity> list = filesService.getAllFiles();
        return new ResponseEntity<List<FileEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<Object> getFileByName(@PathVariable("fileName") String fileName) throws RecordNotFoundException {
        FileEntity entity = filesService.getFileByName(fileName);
        return new ResponseEntity<>(entity.getFileContent(),new HttpHeaders(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createFile(@RequestBody FileEntity fileEntity) throws IOException {
        FileEntity entity=filesService.createFile(fileEntity);
        return new ResponseEntity<Object>("File created successfully",new HttpHeaders(),HttpStatus.CREATED);
    }

    @PutMapping("/{fileName}")
    public ResponseEntity<FileEntity> updateFile(@PathVariable("fileName")String fileName,@RequestBody FileEntity fileEntity) throws RecordNotFoundException, IOException {
        FileEntity entity=filesService.updateFile(fileName);
        return new ResponseEntity<FileEntity>(entity,new HttpHeaders(),HttpStatus.OK);
    }

    @DeleteMapping("/{fileName}")
    public HttpStatus deleteFileByName(@PathVariable("fileName")String fileName) throws RecordNotFoundException{
        filesService.deleteFileByName(fileName);
        return HttpStatus.FORBIDDEN;
    }

}
