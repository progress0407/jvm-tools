#!/bin/sh

CURRENT_FILE_PATH="$(dirname $0)"
cd $CURRENT_FILE_PATH

java \
  -Dspring.profiles.active="${SPRING_PROFILE}" \
  -jar app.jar

  # -Duser.server.version=${SERVER_VERSION} \
  # -Dserver.port=${SERVER_PORT} \
  # -Dspring.datasource.url=${DB_URL} \
  # -Dspring.datasource.username=${DB_USERNAME} \
  # -Dspring.datasource.password=${DB_PASSWORD} \
