# Drawing App Backend

This is a Spring Boot backend for the drawing application that allows users to save and retrieve their drawings.

## Features

- User registration with just a name
- Save drawings with shapes (circle, square, triangle)
- Retrieve user's drawings
- Search drawings by title
- Delete drawings
- RESTful API endpoints

## API Endpoints

### User Management

#### Register User
```
POST /api/users/register
Content-Type: application/json

{
  "name": "user_name"
}
```

#### Check if User Exists
```
GET /api/users/{name}/exists
```

### Drawing Management

#### Save Drawing
```
POST /api/drawings/{userName}
Content-Type: application/json

{
  "title": "My Drawing",
  "shapes": [
    {
      "type": "circle",
      "x": 100.0,
      "y": 150.0
    },
    {
      "type": "square",
      "x": 200.0,
      "y": 250.0
    }
  ]
}
```

#### Get User's Drawings
```
GET /api/drawings/{userName}
```

#### Get Specific Drawing
```
GET /api/drawings/{userName}/{drawingId}
```

#### Search Drawings by Title
```
GET /api/drawings/{userName}/search?title=search_term
```

#### Delete Drawing
```
DELETE /api/drawings/{userName}/{drawingId}
```

## Running the Application

1. Make sure you have Java 11+ installed
2. Navigate to the project directory
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```
4. The application will start on `http://localhost:8080`

## Database

- Uses H2 in-memory database for development
- H2 console available at `http://localhost:8080/h2-console`
- Database credentials: username=sa, password=password

## Example Usage

### 1. Register a user
```bash
curl -X POST http://localhost:8080/api/users/register \
  -H "Content-Type: application/json" \
  -d '{"name": "john"}'
```

### 2. Save a drawing
```bash
curl -X POST http://localhost:8080/api/drawings/john \
  -H "Content-Type: application/json" \
  -d '{
    "title": "My First Drawing",
    "shapes": [
      {"type": "circle", "x": 100.0, "y": 100.0},
      {"type": "square", "x": 200.0, "y": 200.0}
    ]
  }'
```

### 3. Get user's drawings
```bash
curl http://localhost:8080/api/drawings/john
```

## Integration with Frontend

To integrate this backend with your React frontend, you'll need to:

1. Update the frontend to make API calls to these endpoints
2. Add user registration/login functionality
3. Replace the local export/import with server save/load operations

The backend is designed to work with the existing frontend structure and supports all the shape types (circle, square, triangle) that your React app uses. 