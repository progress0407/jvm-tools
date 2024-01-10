#!/bin/bash
../gradlew :docker:assemble  && \
docker build -t progress0407/docker-app -f docker/Build-Jar-Dockerfile . && \
docker push progress0407/docker-app