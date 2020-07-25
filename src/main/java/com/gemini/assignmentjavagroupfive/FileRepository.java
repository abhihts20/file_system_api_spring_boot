package com.gemini.assignmentjavagroupfive;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepository extends JpaRepository<FileEntity,Long> {
    Optional<FileEntity> findDistinctByFileName(String name);

    void deleteDistinctByFileName(String fileName);
}
