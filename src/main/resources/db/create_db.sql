
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = N'storedb')
    CREATE DATABASE storedb
GO