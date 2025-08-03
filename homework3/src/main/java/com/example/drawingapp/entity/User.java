package com.example.drawingapp.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TBL_USERS")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String name;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Drawing> drawings;
    
    public User() {}
    
    public User(String name) {
        this.name = name;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<Drawing> getDrawings() {
        return drawings;
    }
    
    public void setDrawings(List<Drawing> drawings) {
        this.drawings = drawings;
    }
} 