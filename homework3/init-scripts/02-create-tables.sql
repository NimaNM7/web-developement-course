-- Create tables for Drawing App based on JPA entity annotations
-- This script creates the database schema manually

-- Create TBL_USERS table
CREATE TABLE IF NOT EXISTS TBL_USERS (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

-- Create TBL_DRAWING table
CREATE TABLE IF NOT EXISTS TBL_DRAWING (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    user_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_drawing_user FOREIGN KEY (user_id) REFERENCES TBL_USERS(id) ON DELETE CASCADE
);

-- Create TBL_SHAPES table
CREATE TABLE IF NOT EXISTS TBL_SHAPES (
    id BIGSERIAL PRIMARY KEY,
    type VARCHAR(20) NOT NULL CHECK (type IN ('CIRCLE', 'SQUARE', 'TRIANGLE')),
    x DOUBLE PRECISION NOT NULL,
    y DOUBLE PRECISION NOT NULL,
    drawing_id BIGINT NOT NULL,
    CONSTRAINT fk_shape_drawing FOREIGN KEY (drawing_id) REFERENCES TBL_DRAWING(id) ON DELETE CASCADE
);

-- Create indexes for better performance
CREATE INDEX IF NOT EXISTS idx_users_name ON TBL_USERS(name);
CREATE INDEX IF NOT EXISTS idx_drawing_user_id ON TBL_DRAWING(user_id);
CREATE INDEX IF NOT EXISTS idx_drawing_created_at ON TBL_DRAWING(created_at);
CREATE INDEX IF NOT EXISTS idx_drawing_updated_at ON TBL_DRAWING(updated_at);
CREATE INDEX IF NOT EXISTS idx_shapes_drawing_id ON TBL_SHAPES(drawing_id);
CREATE INDEX IF NOT EXISTS idx_shapes_type ON TBL_SHAPES(type);

-- Add comments for documentation
COMMENT ON TABLE TBL_USERS IS 'Stores user information for the drawing application';
COMMENT ON TABLE TBL_DRAWING IS 'Stores drawing metadata and relationships to users';
COMMENT ON TABLE TBL_SHAPES IS 'Stores individual shapes within drawings';

COMMENT ON COLUMN TBL_USERS.id IS 'Primary key for users';
COMMENT ON COLUMN TBL_USERS.name IS 'Unique username for the application';

COMMENT ON COLUMN TBL_DRAWING.id IS 'Primary key for drawings';
COMMENT ON COLUMN TBL_DRAWING.title IS 'Title of the drawing';
COMMENT ON COLUMN TBL_DRAWING.user_id IS 'Foreign key to TBL_USERS';
COMMENT ON COLUMN TBL_DRAWING.created_at IS 'Timestamp when drawing was created';
COMMENT ON COLUMN TBL_DRAWING.updated_at IS 'Timestamp when drawing was last updated';

COMMENT ON COLUMN TBL_SHAPES.id IS 'Primary key for shapes';
COMMENT ON COLUMN TBL_SHAPES.type IS 'Type of shape: CIRCLE, SQUARE, or TRIANGLE';
COMMENT ON COLUMN TBL_SHAPES.x IS 'X coordinate of the shape';
COMMENT ON COLUMN TBL_SHAPES.y IS 'Y coordinate of the shape';
COMMENT ON COLUMN TBL_SHAPES.drawing_id IS 'Foreign key to TBL_DRAWING';

-- Log successful table creation
DO $$
BEGIN
    RAISE NOTICE 'Drawing App tables created successfully';
    RAISE NOTICE 'Tables created: TBL_USERS, TBL_DRAWING, TBL_SHAPES';
END $$; 