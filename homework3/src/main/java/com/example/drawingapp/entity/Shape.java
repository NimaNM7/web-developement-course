package com.example.drawingapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "TBL_SHAPES")
public class Shape {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ShapeType type;
    
    @Column(nullable = false)
    private Double x;
    
    @Column(nullable = false)
    private Double y;
    
    @ManyToOne
    @JoinColumn(name = "DRAWING_ID", nullable = false)
    private Drawing drawing;
    
    public Shape() {}
    
    public Shape(ShapeType type, Double x, Double y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public ShapeType getType() {
        return type;
    }
    
    public void setType(ShapeType type) {
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
    
    public Drawing getDrawing() {
        return drawing;
    }
    
    public void setDrawing(Drawing drawing) {
        this.drawing = drawing;
    }
    
    public enum ShapeType {
        CIRCLE, SQUARE, TRIANGLE
    }
} 