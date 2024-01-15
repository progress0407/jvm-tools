#!/bin/bash

java \
  -Dspring.profiles.active=$SPRING_PROFILE \
  -Duser.server.version=$USER_SERVER_VERSION \
  -jar app.jar
