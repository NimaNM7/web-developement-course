package com.example.drawingapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "TBL_SHAPES")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    
    public Shape(ShapeType type, Double x, Double y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }
    
    public enum ShapeType {
        CIRCLE, SQUARE, TRIANGLE
    }
} 