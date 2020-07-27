package com.gemini.assignmentjavagroupfive;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.gemini.assignmentjavagroupfive.dataModel.FileEntity;
import com.gemini.assignmentjavagroupfive.service.FilesService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@WebMvcTest
public class TestController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilesService filesService;

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void tesGetAllFile() throws Exception {
        List<FileEntity> files = new ArrayList<>();
        FileEntity fileEntity = new FileEntity();
        fileEntity.setId((long) 1);
        fileEntity.setFileName("file1.txt");
        fileEntity.setFileContent("This is file 1");
        fileEntity.setFilePath("C:\\Users\\Abhinav\\Downloads\\assignment-java-group-five\\assignment-java-group-five\\file1.txt");
        files.add(fileEntity);
        Mockito.when(filesService.getAllFiles()).thenReturn(files);
        mockMvc.perform(get("/file/")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0]fileName", Matchers.equalTo("file1.txt")))
                .andExpect(jsonPath("$[0]fileContent", Matchers.equalTo("This is file 1")))
                .andExpect(jsonPath("$[0]filePath", Matchers.equalTo("C:\\Users\\Abhinav\\Downloads\\assignment-java-group-five\\assignment-java-group-five\\file1.txt")));
    }

    @Test
    public void testGetFileByName() throws Exception {

    }

    @Test
    public void testCreateFile() throws Exception {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setId((long) 1);
        fileEntity.setFileName("file1.txt");
        fileEntity.setFileContent("This is file 1");
        fileEntity.setFilePath("C:\\Users\\Abhinav\\Downloads\\assignment-java-group-five\\assignment-java-group-five\\file1.txt");
        Mockito.when(filesService.createFile(ArgumentMatchers.any())).thenReturn(fileEntity);
        String json = mapper.writeValueAsString(fileEntity);
        mockMvc.perform(post("/file").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andExpect(jsonPath("$.id",Matchers.equalTo(1)))
                .andExpect(jsonPath("$.fileName",Matchers.equalTo("file1.txt")))
                .andExpect(jsonPath("$.fileContent",Matchers.equalTo("This is file 1")))
                .andExpect(jsonPath("$.filePath",Matchers.equalTo("C:\\Users\\Abhinav\\Downloads\\assignment-java-group-five\\assignment-java-group-five\\file1.txt")));


    }
}
