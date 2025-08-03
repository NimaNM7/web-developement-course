-- Useful SQL Queries for Drawing App Database Management
-- This file contains helpful queries for development and testing

-- =====================================================
-- BASIC QUERIES
-- =====================================================

-- 1. Get all users
SELECT id, name FROM TBL_USERS ORDER BY name;

-- 2. Get all drawings with user information
SELECT 
    d.id,
    d.title,
    u.name as user_name,
    d.created_at,
    d.updated_at
FROM TBL_DRAWING d
JOIN TBL_USERS u ON d.user_id = u.id
ORDER BY d.updated_at DESC;

-- 3. Get all shapes for a specific drawing
SELECT 
    s.id,
    s.type,
    s.x,
    s.y,
    d.title as drawing_title
FROM TBL_SHAPES s
JOIN TBL_DRAWING d ON s.drawing_id = d.id
WHERE d.title = 'My First Drawing'
ORDER BY s.id;

-- =====================================================
-- STATISTICS QUERIES
-- =====================================================

-- 4. Count shapes by type
SELECT 
    type,
    COUNT(*) as count
FROM TBL_SHAPES
GROUP BY type
ORDER BY count DESC;

-- 5. Count drawings per user
SELECT 
    u.name,
    COUNT(d.id) as drawing_count
FROM TBL_USERS u
LEFT JOIN TBL_DRAWING d ON u.id = d.user_id
GROUP BY u.id, u.name
ORDER BY drawing_count DESC;

-- 6. Get drawing statistics
SELECT 
    d.title,
    u.name as user_name,
    COUNT(s.id) as shape_count,
    d.created_at,
    d.updated_at
FROM TBL_DRAWING d
JOIN TBL_USERS u ON d.user_id = u.id
LEFT JOIN TBL_SHAPES s ON d.id = s.drawing_id
GROUP BY d.id, d.title, u.name, d.created_at, d.updated_at
ORDER BY d.updated_at DESC;

-- =====================================================
-- SEARCH QUERIES
-- =====================================================

-- 7. Search drawings by title (case-insensitive)
SELECT 
    d.title,
    u.name as user_name,
    d.created_at
FROM TBL_DRAWING d
JOIN TBL_USERS u ON d.user_id = u.id
WHERE LOWER(d.title) LIKE LOWER('%art%')
ORDER BY d.created_at DESC;

-- 8. Find drawings with specific shape types
SELECT DISTINCT
    d.title,
    u.name as user_name
FROM TBL_DRAWING d
JOIN TBL_USERS u ON d.user_id = u.id
JOIN TBL_SHAPES s ON d.id = s.drawing_id
WHERE s.type = 'CIRCLE'
ORDER BY d.title;

-- =====================================================
-- CLEANUP QUERIES
-- =====================================================

-- 9. Delete all data (WARNING: This will delete everything!)
-- DELETE FROM TBL_SHAPES;
-- DELETE FROM TBL_DRAWING;
-- DELETE FROM TBL_USERS;

-- 10. Delete drawings older than a specific date
-- DELETE FROM TBL_SHAPES WHERE drawing_id IN (
--     SELECT id FROM TBL_DRAWING WHERE created_at < '2024-01-01'
-- );
-- DELETE FROM TBL_DRAWING WHERE created_at < '2024-01-01';

-- =====================================================
-- MAINTENANCE QUERIES
-- =====================================================

-- 11. Check for orphaned shapes (shapes without drawings)
SELECT s.id, s.type, s.x, s.y
FROM TBL_SHAPES s
LEFT JOIN TBL_DRAWING d ON s.drawing_id = d.id
WHERE d.id IS NULL;

-- 12. Check for orphaned drawings (drawings without users)
SELECT d.id, d.title, d.created_at
FROM TBL_DRAWING d
LEFT JOIN TBL_USERS u ON d.user_id = u.id
WHERE u.id IS NULL;

-- 13. Get database size information
SELECT 
    schemaname,
    tablename,
    attname,
    n_distinct,
    correlation
FROM pg_stats
WHERE tablename IN ('tbl_users', 'tbl_drawing', 'tbl_shapes')
ORDER BY tablename, attname;

-- =====================================================
-- PERFORMANCE QUERIES
-- =====================================================

-- 14. Check index usage
SELECT 
    schemaname,
    tablename,
    indexname,
    idx_scan,
    idx_tup_read,
    idx_tup_fetch
FROM pg_stat_user_indexes
WHERE tablename IN ('tbl_users', 'tbl_drawing', 'tbl_shapes')
ORDER BY idx_scan DESC;

-- 15. Get table sizes
SELECT 
    schemaname,
    tablename,
    pg_size_pretty(pg_total_relation_size(schemaname||'.'||tablename)) as size
FROM pg_tables
WHERE tablename IN ('tbl_users', 'tbl_drawing', 'tbl_shapes')
ORDER BY pg_total_relation_size(schemaname||'.'||tablename) DESC;

-- =====================================================
-- BACKUP QUERIES
-- =====================================================

-- 16. Export data to CSV (run these in psql)
-- \copy (SELECT * FROM TBL_USERS) TO 'users.csv' CSV HEADER;
-- \copy (SELECT * FROM TBL_DRAWING) TO 'drawings.csv' CSV HEADER;
-- \copy (SELECT * FROM TBL_SHAPES) TO 'shapes.csv' CSV HEADER;

-- 17. Create a complete backup of the database
-- pg_dump -U drawinguser -d drawingdb > drawing_app_backup.sql

-- =====================================================
-- MONITORING QUERIES
-- =====================================================

-- 18. Get recent activity
SELECT 
    'Recent Users' as activity_type,
    COUNT(*) as count,
    MAX(created_at) as latest_activity
FROM (
    SELECT created_at FROM TBL_USERS WHERE created_at > NOW() - INTERVAL '7 days'
    UNION ALL
    SELECT created_at FROM TBL_DRAWING WHERE created_at > NOW() - INTERVAL '7 days'
) recent_activity;

-- 19. Get active users (users with drawings in last 30 days)
SELECT 
    u.name,
    COUNT(d.id) as recent_drawings
FROM TBL_USERS u
JOIN TBL_DRAWING d ON u.id = d.user_id
WHERE d.updated_at > NOW() - INTERVAL '30 days'
GROUP BY u.id, u.name
ORDER BY recent_drawings DESC; 