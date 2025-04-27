#!/bin/bash

echo "=> MariaDB service is up and running"

echo "Checking for databases to import from environment variables INSTALL_<DB_NAME>";

if [ -n "$INSTALL_APLAZO" ]; then
    echo "=> Importing example database 'aplazo'"
    mariadb -uroot -p${MYSQL_ROOT_PASSWORD} -e "CREATE DATABASE aplazodb"
    mariadb -uroot -p${MYSQL_ROOT_PASSWORD} aplazodb < data.sql
fi

echo "=> Granting access to all databases for '${MYSQL_USER}'"
#mysql -uroot -p${MYSQL_ROOT_PASSWORD} -e "CREATE USER '${MYSQL_USER}'@'%' IDENTIFIED BY '{$MYSQL_PASSWORD}'"
mariadb -uroot -p${MYSQL_ROOT_PASSWORD} -e "GRANT ALL PRIVILEGES ON *.* TO '${MYSQL_USER}'@'%' WITH GRANT OPTION"

echo "=> Done!"