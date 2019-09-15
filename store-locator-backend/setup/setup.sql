
USE master
GO

IF NOT EXISTS(SELECT name
              FROM sysdatabases
              WHERE name = N'storedb')
BEGIN
  CREATE DATABASE storedb
END
GO


USE master
GO

IF NOT EXISTS(SELECT *
              FROM sys.server_principals
              WHERE name = N'storedb')
BEGIN
  CREATE LOGIN storedb WITH PASSWORD = N'Password123', DEFAULT_DATABASE = storedb, DEFAULT_LANGUAGE =[us_english], CHECK_EXPIRATION = OFF, CHECK_POLICY = OFF
END
GO

USE storedb
GO

IF NOT EXISTS(SELECT *
              FROM sys.database_principals
              WHERE (name = N'storedb_app') AND (type = N'R'))
BEGIN
  CREATE ROLE storedb_app
  CREATE USER storedb FOR LOGIN storedb
  ALTER ROLE storedb_app ADD MEMBER storedb
END
GO