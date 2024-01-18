#!/bin/sh

SHELL_PATH=$(pwd)
CURRENT_PATH="$(dirname $0)"

DOCKER_IMAGE_NAME="progress0407/app"
DOCKER_FILE_PATH="app/Build-Jar-Dockerfile"
DOCKER_CONTEXT_PATH="../.."

cd $CURRENT_PATH

docker build  \
  --build-arg SPRING_PROFILE="prod" \
  -t $DOCKER_IMAGE_NAME \
  -f $DOCKER_FILE_PATH  $DOCKER_CONTEXT_PATH

# 원래의 디렉터리로 돌아가지 않아도 정상 동작한다 ...
#   cd $SHELL_PATH
#   echo "NOW RETURN = $(pwd)"

  
  # --build-arg SERVER_VERSION="100" \