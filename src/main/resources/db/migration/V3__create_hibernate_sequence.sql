
IF NOT EXISTS(SELECT *
          FROM sys.objects
          WHERE object_id = OBJECT_ID(N'hibernate_sequence') AND type = 'SO')
    CREATE SEQUENCE hibernate_sequence
    AS BIGINT
    START WITH 1
    INCREMENT BY 1
    MINVALUE-9223372036854775808
    MAXVALUE 9223372036854775807
    CACHE
GO

