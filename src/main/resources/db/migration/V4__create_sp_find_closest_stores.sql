
IF EXISTS(SELECT *
          FROM sys.objects
          WHERE object_id = OBJECT_ID(N'find_closest_stores') AND type IN (N'P', N'PC'))
  DROP PROCEDURE find_closest_stores
GO

CREATE PROCEDURE find_closest_stores
    @latitude          FLOAT,
    @longitude         FLOAT
AS
    SELECT TOP 5
        s.id,
        s.address_name,
        s.street,
        s.street2,
        s.open_time,
        s.close_time,
        s.longitude,
        s.latitude,
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
    ORDER BY distance
GO

