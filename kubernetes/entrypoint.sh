#!/bin/sh

java \
  -Dspring.profiles.active="${SPRING_PROFILE}" \
  -jar app.jar

  # -Duser.server.version=${SERVER_VERSION} \
  # -Dserver.port=${SERVER_PORT} \
  # -Dspring.datasource.url=${DB_URL} \
  # -Dspring.datasource.username=${DB_USERNAME} \
  # -Dspring.datasource.password=${DB_PASSWORD} \
