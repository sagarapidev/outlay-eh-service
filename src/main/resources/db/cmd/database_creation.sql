-- 1. Create the database with proper naming convention
CREATE DATABASE [outlay-eh-service-db];
GO

-- 2. Create a SQL Server login at the instance level
CREATE LOGIN outlayeh_user WITH PASSWORD = 'password!',
    CHECK_POLICY = OFF, CHECK_EXPIRATION = OFF;
GO

-- 3. Map that login to the new database as a user
USE [outlay-eh-service-db];
CREATE USER outlayeh_user FOR LOGIN outlayeh_user;
GO

-- 4. Grant db_owner role (for dev convenience)
ALTER ROLE db_owner ADD MEMBER outlayeh_user;
GO