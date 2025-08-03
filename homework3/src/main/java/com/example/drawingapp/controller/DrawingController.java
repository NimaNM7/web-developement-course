package com.example.drawingapp.controller;

import com.example.drawingapp.dto.DrawingRequest;
import com.example.drawingapp.dto.DrawingResponse;
import com.example.drawingapp.service.DrawingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/drawings")
public class DrawingController {
    
    private final DrawingService drawingService;

    public DrawingController(DrawingService drawingService) {
        this.drawingService = drawingService;
    }

    @PostMapping("/{userName}")
    public ResponseEntity<?> saveDrawing(@PathVariable String userName, @RequestBody DrawingRequest request) {
        try {
            DrawingResponse response = drawingService.saveDrawing(userName, request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    
    @GetMapping("/{userName}")
    public ResponseEntity<?> getUserDrawings(@PathVariable String userName) {
        try {
            List<DrawingResponse> drawings = drawingService.getUserDrawings(userName);
            return ResponseEntity.ok(drawings);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    
    @GetMapping("/{userName}/{drawingId}")
    public ResponseEntity<?> getDrawing(@PathVariable String userName, @PathVariable Long drawingId) {
        try {
            DrawingResponse drawing = drawingService.getDrawing(userName, drawingId);
            return ResponseEntity.ok(drawing);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    
    @GetMapping("/{userName}/search")
    public ResponseEntity<?> searchDrawings(@PathVariable String userName, @RequestParam String title) {
        try {
            List<DrawingResponse> drawings = drawingService.searchUserDrawings(userName, title);
            return ResponseEntity.ok(drawings);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    
    @DeleteMapping("/{userName}/{drawingId}")
    public ResponseEntity<?> deleteDrawing(@PathVariable String userName, @PathVariable Long drawingId) {
        try {
            drawingService.deleteDrawing(userName, drawingId);
            return ResponseEntity.ok(Map.of("message", "Drawing deleted successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
} 