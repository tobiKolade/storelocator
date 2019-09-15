#!/bin/bash
# wait for SQL Server to come up
echo importing data will start in 15s...
sleep 15s
echo importing data...

# run the init script to create the DB and the tables in /table
/opt/mssql-tools/bin/sqlcmd -S 0.0.0.0 -U sa -P Password123 -i ./setup.sql