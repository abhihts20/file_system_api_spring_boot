package com.gemini.assignmentjavagroupfive.repository;

import com.gemini.assignmentjavagroupfive.dataModel.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepository extends JpaRepository<FileEntity,Long> {
    Optional<FileEntity> findDistinctByFileName(String name);
}
