package com.gemini.assignmentjavagroupfive.controller;


import com.gemini.assignmentjavagroupfive.dataModel.FileEntity;
import com.gemini.assignmentjavagroupfive.service.FilesService;
import com.gemini.assignmentjavagroupfive.exception.RecordNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.sax.SAXResult;
import java.io.File;
import java.io.IOException;
import java.lang.management.OperatingSystemMXBean;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileRestController {
    private static final Logger logger= LoggerFactory.getLogger(FileRestController.class);
    @Autowired
    FilesService filesService;


    @Operation(summary = "Get all files")
    @GetMapping(value = "/")
    public ResponseEntity<List<FileEntity>> getAllFile() {
        List<FileEntity> list = filesService.getAllFiles();
        return new ResponseEntity<List<FileEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @Operation(summary = "Get File by its name")
    @GetMapping("/{fileName}")
    public ResponseEntity<Object> getFileByName(@PathVariable("fileName") String fileName) throws RecordNotFoundException {
        FileEntity entity = filesService.getFileByName(fileName);
        return new ResponseEntity<>(entity.getFileContent(),new HttpHeaders(),HttpStatus.OK);
    }

    @Parameter(name = "fileName",description = "Name of the file",required = true)
    @Parameter(name = "fileContent",description = "Content of the file",required = true)
    @Operation(summary = "Create a new File",description ="Rest Endpoint that returns a object after creating a file")
    @PostMapping
    public ResponseEntity<Object> createFile(@RequestBody FileEntity fileEntity) throws IOException {
        FileEntity entity=filesService.createFile(fileEntity);
        return new ResponseEntity<Object>("File created successfully",new HttpHeaders(),HttpStatus.CREATED);
    }

    @Operation(summary = "Update a content of file by its file name")
    @PutMapping("/{fileName}")
    public ResponseEntity<FileEntity> updateFile(@PathVariable("fileName")String fileName,@RequestBody FileEntity fileEntity) throws RecordNotFoundException, IOException {
        FileEntity entity=filesService.updateFile(fileName,fileEntity);
        return new ResponseEntity<FileEntity>(entity,new HttpHeaders(),HttpStatus.OK);
    }

    @Operation(summary = "Delete a file by its name")
    @DeleteMapping("/{fileName}")
    public HttpStatus deleteFileByName(@PathVariable("fileName")String fileName) throws RecordNotFoundException{
        filesService.deleteFileByName(fileName);
        return HttpStatus.FORBIDDEN;
    }

}
