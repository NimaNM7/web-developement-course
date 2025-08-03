# Docker Setup for Drawing App

This document explains how to set up and run the Drawing App with PostgreSQL using Docker Compose.

## Prerequisites

- Docker and Docker Compose installed on your system
- Java 11+ for running the Spring Boot application

## Quick Start

### 1. Start the Database

```bash
# Start PostgreSQL and pgAdmin
docker-compose up -d

# Check if services are running
docker-compose ps
```

### 2. Verify Database Connection

The PostgreSQL database will be available at:
- **Host**: localhost
- **Port**: 5432
- **Database**: drawingdb
- **Username**: drawinguser
- **Password**: drawingpass

### 3. Access pgAdmin (Optional)

pgAdmin is available at: http://localhost:8081
- **Email**: admin@drawingapp.com
- **Password**: admin123

To connect to the database in pgAdmin:
1. Login to pgAdmin
2. Right-click on "Servers" → "Register" → "Server"
3. In the "General" tab, name it "Drawing App DB"
4. In the "Connection" tab:
   - Host: postgres (or localhost if connecting from outside Docker)
   - Port: 5432
   - Database: drawingdb
   - Username: drawinguser
   - Password: drawingpass

### 4. Run the Spring Boot Application

```bash
# Start the Spring Boot app
mvn spring-boot:run
```

The application will connect to the PostgreSQL database automatically.

## Docker Services

### PostgreSQL Database
- **Image**: postgres:15-alpine
- **Port**: 5432
- **Database**: drawingdb
- **Username**: drawinguser
- **Password**: drawingpass
- **Data Persistence**: Docker volume `postgres_data`

### pgAdmin (Database Management)
- **Image**: dpage/pgadmin4:latest
- **Port**: 8081
- **Email**: admin@drawingapp.com
- **Password**: admin123
- **Data Persistence**: Docker volume `pgadmin_data`

## Useful Commands

```bash
# Start all services
docker-compose up -d

# Stop all services
docker-compose down

# View logs
docker-compose logs postgres
docker-compose logs pgadmin

# Restart services
docker-compose restart

# Remove all data (WARNING: This will delete all data)
docker-compose down -v

# Check service health
docker-compose ps
```

## Database Schema

The application will automatically create the following tables:
- `users` - User information
- `drawings` - Drawing metadata
- `shapes` - Individual shapes in drawings

## Troubleshooting

### Connection Issues
1. Ensure Docker is running
2. Check if containers are healthy: `docker-compose ps`
3. Verify PostgreSQL is ready: `docker-compose logs postgres`

### Port Conflicts
If ports 5432 or 8081 are already in use, modify the `docker-compose.yml` file:
```yaml
ports:
  - "5433:5432"  # Change 5432 to 5433
```

### Data Persistence
Database data is stored in Docker volumes:
- `postgres_data` - PostgreSQL data
- `pgadmin_data` - pgAdmin settings

To backup data:
```bash
docker exec drawing-app-postgres pg_dump -U drawinguser drawingdb > backup.sql
```

## Development vs Production

### Development
- Uses `spring.jpa.hibernate.ddl-auto=update` to automatically create/update tables
- Data is persisted in Docker volumes
- pgAdmin available for database management

### Production
- Change `spring.jpa.hibernate.ddl-auto=validate` in application.properties
- Use proper database credentials
- Disable pgAdmin or secure it properly
- Use external PostgreSQL instance if needed

## Environment Variables

You can customize the setup by creating a `.env` file:

```env
POSTGRES_DB=drawingdb
POSTGRES_USER=drawinguser
POSTGRES_PASSWORD=drawingpass
PGADMIN_DEFAULT_EMAIL=admin@drawingapp.com
PGADMIN_DEFAULT_PASSWORD=admin123
```

Then update `docker-compose.yml` to use these variables:
```yaml
environment:
  POSTGRES_DB: ${POSTGRES_DB}
  POSTGRES_USER: ${POSTGRES_USER}
  POSTGRES_PASSWORD: ${POSTGRES_PASSWORD}
``` 