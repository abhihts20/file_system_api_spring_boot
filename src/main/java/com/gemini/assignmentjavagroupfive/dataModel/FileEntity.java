package com.gemini.assignmentjavagroupfive.dataModel;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;

@Entity
@Table(name = "FILE_TABLE")
public class FileEntity {
    /**
     * Data Model Containing Columns to store the data
     * */

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "content")
    private String fileContent;

    @Column(name = "file_path")
    private String filePath;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "FileEntity [id="+id+"]"+"[file name="+fileName+"]"+"[file content="+fileContent+"]"+"[file path="+filePath+"]";
    }
}
