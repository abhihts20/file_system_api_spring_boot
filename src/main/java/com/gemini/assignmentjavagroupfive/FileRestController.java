package com.gemini.assignmentjavagroupfive;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<FileEntity> getFileByName(@PathVariable("fileName") String fileName) throws RecordNotFoundException {
        FileEntity entity = filesService.getFileByName(fileName);
        return new ResponseEntity<FileEntity>(entity,new HttpHeaders(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createFile(@RequestBody FileEntity fileEntity) throws IOException {
        FileEntity entity=filesService.createOrUpdateFile(fileEntity);
        return new ResponseEntity<Object>("File created succesfully",new HttpHeaders(),HttpStatus.CREATED);
    }

}
