#!/bin/sh

CURRENT_FILE_PATH="$(dirname $0)"
cd $CURRENT_FILE_PATH

java \
  -Dspring.profiles.active="${SPRING_PROFILE}" \
  -Duser.server.version=${SERVER_VERSION} \
  -jar app.jar

  # -Dserver.port=${SERVER_PORT} \
  # -Dspring.datasource.url=${DB_URL} \
  # -Dspring.datasource.username=${DB_USERNAME} \
  # -Dspring.datasource.password=${DB_PASSWORD} \
