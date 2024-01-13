#!/bin/bash

#
# input example.
# `sh build-and-push-docker-image-script.sh local-mem`
#

set -e
source ./color-echo.sh

SPRING_PROFILE=${1:-prod}  # Default to 'prod' if no argument provided
DOCKER_IMAGE_NAME="progress0407/docker-app"

echo_blue "[ Building Jar file ... ]"
../gradlew :docker:assemble

echo_blue "[ Building Docker image: $DOCKER_IMAGE_NAME ... ]"
docker build \
    -t $DOCKER_IMAGE_NAME \
    --build-arg SPRING_PROFILE="$SPRING_PROFILE" \
    -f docker-script/app/Build-Jar-Dockerfile .

echo_blue "[ Pushing Docker image: $DOCKER_IMAGE_NAME ... ]"
docker push $DOCKER_IMAGE_NAME

echo_green "[ Process completed successfully ! ]"
