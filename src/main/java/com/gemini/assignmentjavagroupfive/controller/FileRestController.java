package com.gemini.assignmentjavagroupfive.controller;


import com.gemini.assignmentjavagroupfive.dataModel.FileEntity;
import com.gemini.assignmentjavagroupfive.service.FilesService;
import com.gemini.assignmentjavagroupfive.exception.RecordNotFoundException;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

/**
 * REST API Controller Class
 * Implementing all CRUD APIs
 *
 * @BasePath localhost:8080/file
 * */

@RestController
@RequestMapping("/file")
public class FileRestController {
    private static final Logger logger= LoggerFactory.getLogger(FileRestController.class);
    @Autowired
    FilesService filesService;


    @Operation(summary = "Get all files",description = "REST Endpoint to get all the files")
    @ApiResponse()
    @GetMapping(value = "/",produces = "application/json")
    public ResponseEntity<List<FileEntity>> getAllFile() {
        /**
         *GET API method to get all the files in a directory
         * @apiNote localhost:8080/file/
         * @response JSON object containing id,fileName,fileContent,filePath
         * */
        List<FileEntity> list = filesService.getAllFiles();
        return new ResponseEntity<List<FileEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @Operation(summary = "Get File by its name",description = "REST Endpoint to get a file by its name")
    @GetMapping(value = "/{fileName}")
    public ResponseEntity<Object> getFileByName(@PathVariable("fileName") String fileName) throws RecordNotFoundException {
        /**
         * GET API BY FILE NAME method to get file by file name
         * @apiNote localhost:8080/file/{fileName}
         * @response JSON object containing id,fileName,fileContent,filePath
         *  */

        FileEntity entity = filesService.getFileByName(fileName);
        return new ResponseEntity<>(entity.getFileContent(),new HttpHeaders(),HttpStatus.OK);
    }

    @Parameter(name = "fileName",description = "Name of the file",required = true)
    @Parameter(name = "fileContent",description = "Content of the file",required = true)
    @Operation(summary = "Create a new File",description ="REST Endpoint that returns a object after creating a file")
    @PostMapping(value = "/")
    public ResponseEntity<Object> createFile(@RequestBody FileEntity fileEntity) throws IOException {
        /**
         * POST API method to create file
         * @apiNote localhost:8080/file/
         * @param args1 fileName
         * @param args2 fileContent
         * @response status message
         *  */
        FileEntity entity=filesService.createFile(fileEntity);
        return new ResponseEntity<Object>("File created successfully",new HttpHeaders(),HttpStatus.CREATED);
    }

    @Operation(summary = "Update a content of file by its name",description = "REST Endpoint to update a file by its name")
    @PutMapping(value = "/{fileName}",produces = "application/json")
    public ResponseEntity<FileEntity> updateFile(@PathVariable("fileName")String fileName,@RequestBody FileEntity fileEntity) throws RecordNotFoundException, IOException {
        /**
         * PUT API BY FILE NAME method to update the content of a file
         * @apiNote localhost:8080/file/{fileName}
         * @param args1 fileName
         * @param args2 fileContent
         * @response JSON object containing id,fileName,fileContent,filePath
         * */
        FileEntity entity=filesService.updateFile(fileName,fileEntity);
        return new ResponseEntity<FileEntity>(entity,new HttpHeaders(),HttpStatus.OK);
    }

    @Operation(summary = "Delete a file by its name",description = "REST Endpoint to delete a file by its name")
    @DeleteMapping(value = "/{fileName}")
    public HttpStatus deleteFileByName(@PathVariable("fileName")String fileName) throws RecordNotFoundException{
        /**
         * DELETE API BY FILE NAME method to delete a file
         * @apiNote localhost:8080/file/{fileName}
         * @response status message
         * */
        filesService.deleteFileByName(fileName);
        return HttpStatus.FORBIDDEN;
    }

}
