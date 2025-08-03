package com.example.drawingapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class DrawingResponse {
    private Long id;
    private String title;
    private String userName;
    private List<ShapeDto> shapes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public DrawingResponse(Long id, String title, String userName, List<ShapeDto> shapes, 
                         LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.userName = userName;
        this.shapes = shapes;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    @Data
    @NoArgsConstructor
    public static class ShapeDto {
        private String type;
        private Double x;
        private Double y;
        
        public ShapeDto(String type, Double x, Double y) {
            this.type = type;
            this.x = x;
            this.y = y;
        }
    }
} 