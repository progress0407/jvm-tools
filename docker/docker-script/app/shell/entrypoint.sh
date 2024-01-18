#!/bin/sh

java \
  -Dspring.profiles.active=${SPRING_PROFILE} \
  -Duser.server.version=${SERVER_VERSION} \
  -jar app.jar
