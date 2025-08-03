package com.example.drawingapp.dto;

import java.time.LocalDateTime;
import java.util.List;

public class DrawingResponse {
    private Long id;
    private String title;
    private String userName;
    private List<ShapeDto> shapes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Constructors
    public DrawingResponse() {}
    
    public DrawingResponse(Long id, String title, String userName, List<ShapeDto> shapes, 
                         LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.userName = userName;
        this.shapes = shapes;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public List<ShapeDto> getShapes() {
        return shapes;
    }
    
    public void setShapes(List<ShapeDto> shapes) {
        this.shapes = shapes;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
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