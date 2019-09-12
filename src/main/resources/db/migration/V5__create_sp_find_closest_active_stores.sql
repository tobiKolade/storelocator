
IF EXISTS(SELECT *
          FROM sys.objects
          WHERE object_id = OBJECT_ID(N'find_closest_active_stores') AND type IN (N'P', N'PC'))
  DROP PROCEDURE find_closest_active_stores
GO

CREATE PROCEDURE find_closest_active_stores
    @latitude          FLOAT,
    @longitude         FLOAT
AS
    SELECT TOP 5 s.*,
    (
        6371 *
        ACOS( COS( RADIANS(@latitude) ) *
              COS( RADIANS( latitude) ) *
              COS( RADIANS( longitude ) - RADIANS(@longitude) ) +
              SIN( RADIANS(@latitude) ) *
              SIN( RADIANS( latitude) )
             )
    ) AS distance
    FROM store s
    WHERE s.active = 1
    ORDER BY distance
GO