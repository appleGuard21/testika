package com.example.letsquiz.repository;

import com.example.letsquiz.entity.file.FileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<FileData, Long> {
    Optional<FileData> findFileDataByName(String fileName);
}
