package com.gemini.assignmentjavagroupfive.repository;

import com.gemini.assignmentjavagroupfive.dataModel.FileEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface FileRepository extends JpaRepository<FileEntity,Long> {
    Optional<FileEntity> findDistinctByFileName(String name);
}
