-- Sample data and useful queries for Drawing App
-- This script can be used for testing and development

-- Insert sample users
INSERT INTO TBL_USERS (name) VALUES 
    ('john_doe'),
    ('jane_smith'),
    ('artist_bob')
ON CONFLICT (name) DO NOTHING;

-- Insert sample drawings
INSERT INTO TBL_DRAWING (title, user_id, created_at, updated_at) VALUES 
    ('My First Drawing', (SELECT id FROM TBL_USERS WHERE name = 'john_doe'), NOW(), NOW()),
    ('Abstract Art', (SELECT id FROM TBL_USERS WHERE name = 'jane_smith'), NOW(), NOW()),
    ('Geometric Shapes', (SELECT id FROM TBL_USERS WHERE name = 'artist_bob'), NOW(), NOW())
ON CONFLICT DO NOTHING;

-- Insert sample shapes for the first drawing
INSERT INTO TBL_SHAPES (type, x, y, drawing_id) VALUES 
    ('CIRCLE', 100.0, 150.0, (SELECT id FROM TBL_DRAWING WHERE title = 'My First Drawing')),
    ('SQUARE', 200.0, 250.0, (SELECT id FROM TBL_DRAWING WHERE title = 'My First Drawing')),
    ('TRIANGLE', 300.0, 350.0, (SELECT id FROM TBL_DRAWING WHERE title = 'My First Drawing'))
ON CONFLICT DO NOTHING;

-- Insert sample shapes for the second drawing
INSERT INTO TBL_SHAPES (type, x, y, drawing_id) VALUES 
    ('CIRCLE', 50.0, 100.0, (SELECT id FROM TBL_DRAWING WHERE title = 'Abstract Art')),
    ('CIRCLE', 150.0, 200.0, (SELECT id FROM TBL_DRAWING WHERE title = 'Abstract Art')),
    ('SQUARE', 250.0, 300.0, (SELECT id FROM TBL_DRAWING WHERE title = 'Abstract Art'))
ON CONFLICT DO NOTHING;

-- Insert sample shapes for the third drawing
INSERT INTO TBL_SHAPES (type, x, y, drawing_id) VALUES 
    ('SQUARE', 75.0, 125.0, (SELECT id FROM TBL_DRAWING WHERE title = 'Geometric Shapes')),
    ('TRIANGLE', 175.0, 225.0, (SELECT id FROM TBL_DRAWING WHERE title = 'Geometric Shapes')),
    ('TRIANGLE', 275.0, 325.0, (SELECT id FROM TBL_DRAWING WHERE title = 'Geometric Shapes'))
ON CONFLICT DO NOTHING;

-- Log sample data insertion
DO $$
BEGIN
    RAISE NOTICE 'Sample data inserted successfully';
    RAISE NOTICE 'Users: john_doe, jane_smith, artist_bob';
    RAISE NOTICE 'Drawings: My First Drawing, Abstract Art, Geometric Shapes';
END $$; 