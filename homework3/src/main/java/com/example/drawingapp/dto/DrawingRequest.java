package com.example.drawingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DrawingRequest {
    private String title;
    private List<ShapeDto> shapes;

    public DrawingRequest(String title, List<ShapeDto> shapes) {
        this.title = title;
        this.shapes = shapes;
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