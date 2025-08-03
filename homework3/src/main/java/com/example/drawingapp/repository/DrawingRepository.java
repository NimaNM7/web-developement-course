package com.example.drawingapp.repository;

import com.example.drawingapp.entity.Drawing;
import com.example.drawingapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrawingRepository extends JpaRepository<Drawing, Long> {
    List<Drawing> findByUserOrderByUpdatedAtDesc(User user);
    List<Drawing> findByUserAndTitleContainingIgnoreCaseOrderByUpdatedAtDesc(User user, String title);
} 