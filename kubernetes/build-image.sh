#!/bin/sh

SHELL_COMMAND_PATH=$(pwd)
CURRENT_FILE_PATH="$(dirname $0)"

DOCKER_IMAGE_NAME="progress0407/simple-app"
DOCKER_FILE_PATH="./BuildJarDockerfile"
DOCKER_CONTEXT_PATH="." # 실행 위치는 현재 모듈이라 가정

cd "$CURRENT_FILE_PATH"

docker build  \
  --build-arg SPRING_PROFILE="prod" \
  -t $DOCKER_IMAGE_NAME \
  -f $DOCKER_FILE_PATH  $DOCKER_CONTEXT_PATH

# 원래의 디렉터리로 돌아가지 않아도 정상 동작한다 ...
#   cd $SHELL_COMMAND_PATH
#   echo "NOW RETURN = $(pwd)"

# --build-arg SERVER_VERSION="100" \
