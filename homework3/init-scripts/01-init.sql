-- Initialize the drawing database
-- This script runs when the PostgreSQL container starts for the first time

-- Create the database if it doesn't exist (PostgreSQL creates it automatically via environment variables)
-- But we can add any additional setup here

-- Grant necessary permissions
GRANT ALL PRIVILEGES ON DATABASE drawingdb TO drawinguser;

-- Create extensions if needed
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Set timezone
SET timezone = 'UTC';

-- Log the initialization
DO $$
BEGIN
    RAISE NOTICE 'Drawing App Database initialized successfully';
END $$; 