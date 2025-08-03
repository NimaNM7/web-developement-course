package com.example.drawingapp.dto;

import java.util.List;

public class DrawingRequest {
    private String title;
    private List<ShapeDto> shapes;
    
    public DrawingRequest() {}
    
    public DrawingRequest(String title, List<ShapeDto> shapes) {
        this.title = title;
        this.shapes = shapes;
    }
    
    // Getters and Setters
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public List<ShapeDto> getShapes() {
        return shapes;
    }
    
    public void setShapes(List<ShapeDto> shapes) {
        this.shapes = shapes;
    }
    
    public static class ShapeDto {
        private String type;
        private Double x;
        private Double y;
        
        // Constructors
        public ShapeDto() {}
        
        public ShapeDto(String type, Double x, Double y) {
            this.type = type;
            this.x = x;
            this.y = y;
        }
        
        // Getters and Setters
        public String getType() {
            return type;
        }
        
        public void setType(String type) {
            this.type = type;
        }
        
        public Double getX() {
            return x;
        }
        
        public void setX(Double x) {
            this.x = x;
        }
        
        public Double getY() {
            return y;
        }
        
        public void setY(Double y) {
            this.y = y;
        }
    }
} 