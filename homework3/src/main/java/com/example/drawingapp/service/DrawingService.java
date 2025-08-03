package com.example.drawingapp.service;

import com.example.drawingapp.dto.DrawingRequest;
import com.example.drawingapp.dto.DrawingResponse;
import com.example.drawingapp.entity.Drawing;
import com.example.drawingapp.entity.Shape;
import com.example.drawingapp.entity.User;
import com.example.drawingapp.repository.DrawingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DrawingService {
    
    private final DrawingRepository drawingRepository;
    
    private final UserService userService;

    public DrawingService(DrawingRepository drawingRepository, UserService userService) {
        this.drawingRepository = drawingRepository;
        this.userService = userService;
    }

    public DrawingResponse saveDrawing(String userName, DrawingRequest request) {
        User user = userService.getUserByName(userName);
        
        Drawing drawing = new Drawing(request.getTitle(), user);
        
        List<Shape> shapes = request.getShapes().stream()
                .map(shapeDto -> {
                    Shape shape = new Shape();
                    shape.setType(Shape.ShapeType.valueOf(shapeDto.getType().toUpperCase()));
                    shape.setX(shapeDto.getX());
                    shape.setY(shapeDto.getY());
                    shape.setDrawing(drawing);
                    return shape;
                })
                .collect(Collectors.toList());
        
        drawing.setShapes(shapes);
        Drawing savedDrawing = drawingRepository.save(drawing);
        
        return convertToResponse(savedDrawing);
    }
    
    public DrawingResponse getDrawing(String userName, Long drawingId) {
        User user = userService.getUserByName(userName);
        
        Drawing drawing = drawingRepository.findById(drawingId)
                .orElseThrow(() -> new RuntimeException("Drawing not found"));
        
        if (!drawing.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Access denied");
        }
        
        return convertToResponse(drawing);
    }
    
    public List<DrawingResponse> getUserDrawings(String userName) {
        User user = userService.getUserByName(userName);
        
        List<Drawing> drawings = drawingRepository.findByUserOrderByUpdatedAtDesc(user);
        
        return drawings.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    public List<DrawingResponse> searchUserDrawings(String userName, String title) {
        User user = userService.getUserByName(userName);
        
        List<Drawing> drawings = drawingRepository.findByUserAndTitleContainingIgnoreCaseOrderByUpdatedAtDesc(user, title);
        
        return drawings.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    public void deleteDrawing(String userName, Long drawingId) {
        User user = userService.getUserByName(userName);
        
        Drawing drawing = drawingRepository.findById(drawingId)
                .orElseThrow(() -> new RuntimeException("Drawing not found"));
        
        if (!drawing.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Access denied");
        }
        
        drawingRepository.delete(drawing);
    }
    
    private DrawingResponse convertToResponse(Drawing drawing) {
        List<DrawingResponse.ShapeDto> shapeDtos = drawing.getShapes().stream()
                .map(shape -> new DrawingResponse.ShapeDto(
                        shape.getType().name().toLowerCase(),
                        shape.getX(),
                        shape.getY()
                ))
                .collect(Collectors.toList());
        
        return new DrawingResponse(
                drawing.getId(),
                drawing.getTitle(),
                drawing.getUser().getName(),
                shapeDtos,
                drawing.getCreatedAt(),
                drawing.getUpdatedAt()
        );
    }
} 