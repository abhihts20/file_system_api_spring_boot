package com.gemini.assignmentjavagroupfive.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gemini.assignmentjavagroupfive.dataModel.FileEntity;
import com.gemini.assignmentjavagroupfive.service.FilesService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


@WebMvcTest(value = FileRestController.class)
public class TestController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilesService filesService;

    @Test
    public void tesGetAllFile() throws Exception {
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
        Mockito.when(filesService.getAllFiles()).thenReturn(files);

        String URI = "/file/";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = this.mapToJson(files);
        String outputInJson = result.getResponse().getContentAsString();
        assertEquals(expectedJson,outputInJson);

    }

    @Test
    public void testGetFileByName() throws Exception {
        FileEntity fileEntity=new FileEntity();
        fileEntity.setId((long) 1);
        fileEntity.setFileName("file1.txt");
        fileEntity.setFileContent("This is file 1");
        fileEntity.setFilePath("C:\\Users\\Abhinav\\Downloads\\assignment-java-group-five\\assignment-java-group-five\\file1.txt");
        String expectedJson=fileEntity.getFileContent();
        Mockito.when(filesService.getFileByName(Mockito.anyString())).thenReturn(fileEntity);

        String URI="/file/file1.txt";
        RequestBuilder requestBuilder=MockMvcRequestBuilders.get(URI)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result=mockMvc.perform(requestBuilder).andReturn();
        String outputInJson = result.getResponse().getContentAsString();
        assertEquals(expectedJson,outputInJson);
    }

    @Test
    public void testCreateFile() throws Exception {
        FileEntity fileEntity=new FileEntity();
        fileEntity.setId((long) 1);
        fileEntity.setFileName("file1.txt");
        fileEntity.setFileContent("This is file 1");
        fileEntity.setFilePath("C:\\Users\\Abhinav\\Downloads\\assignment-java-group-five\\assignment-java-group-five\\file1.txt");
        String inputInJson=this.mapToJson(fileEntity);

        String URI="/file";
        Mockito.when(filesService.createFile(Mockito.any(FileEntity.class))).thenReturn(fileEntity);

        RequestBuilder requestBuilder=MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result=mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response=result.getResponse();

        String outputInJson = response.getContentAsString();

        assertEquals(outputInJson,inputInJson);
        assertEquals(HttpStatus.CREATED.value(),response.getStatus());
    }

    public void testPutByFile() throws Exception{

    }

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

}
