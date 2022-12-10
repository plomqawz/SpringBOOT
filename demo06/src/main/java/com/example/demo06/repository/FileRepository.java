package com.example.demo06.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo06.model.FileBoard;

public interface FileRepository extends JpaRepository<FileBoard, Long>{

}
