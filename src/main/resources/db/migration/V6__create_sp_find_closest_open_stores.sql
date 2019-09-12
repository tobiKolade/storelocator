
IF EXISTS(SELECT *
          FROM sys.objects
          WHERE object_id = OBJECT_ID(N'find_closest_open_stores') AND type IN (N'P', N'PC'))
  DROP PROCEDURE find_closest_open_stores
GO

CREATE PROCEDURE find_closest_open_stores
    @latitude          FLOAT,
    @longitude         FLOAT,
    @time              TIME(7)
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
    WHERE (@time >= s.open_time AND @time <= S.close_time)
    ORDER BY distance
GO