#!/bin/bash

#
# input example.
# `sh build-and-push-docker-image-script.sh local-mem 2`
#

set -e
source ./color-echo.sh

DOCKER_IMAGE_NAME="progress0407/app"
SPRING_PROFILE=${1:-prod}     # default to 'prod' if no argument provided
SERVER_VERSION=${2:-1}        # default is 1

echo_blue "[ Building Jar file ... ]"
../gradlew :docker:assemble

echo_blue "[ Building Docker image: $DOCKER_IMAGE_NAME ... ]"
docker build \
    -t $DOCKER_IMAGE_NAME \
    --build-arg SPRING_PROFILE="$SPRING_PROFILE" \
    --build-arg SERVER_VERSION="$SERVER_VERSION" \
    -f docker-script/app/Build-Jar-Dockerfile .

echo_blue "[ Pushing Docker image: $DOCKER_IMAGE_NAME ... ]"
docker push $DOCKER_IMAGE_NAME

echo_green "[ Process completed successfully ! ]"
