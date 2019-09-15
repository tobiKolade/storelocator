
IF NOT EXISTS(SELECT *
              FROM sys.objects
              WHERE object_id = OBJECT_ID(N'store') AND type IN (N'U'))
  CREATE TABLE store (
    id                      BIGINT PRIMARY KEY,
    city                    VARCHAR(50) NOT NULL,
    postal_code             VARCHAR(50) NOT NULL,
    street                  VARCHAR(50) NOT NULL,
    street2                 VARCHAR(30),
    street3                 VARCHAR(30),
    uuid                    VARCHAR(150),
    address_name            VARCHAR(300) NOT NULL,
    longitude               FLOAT NOT NULL,
    latitude                FLOAT NOT NULL,
    complex_number          INT NOT NULL,
    show_warning_message    BIT,
    open_time               TIME(7) NULL,
    close_time              TIME(7) NULL,
    location_type           VARCHAR(100),
    collection_point        BIT,
    sap_store_id            INT,
    active                  BIT
  )
  GO