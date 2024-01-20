#!/bin/sh

#
# input example.
# `sh build-image.sh prod 1`
#

set -e
# shellcheck disable=SC2039
#. ./color-echo.sh

SPRING_PROFILE=${1:-"local-mem"}
SERVER_VERSION=${2:-1}

# shellcheck disable=SC2034
SHELL_COMMAND_PATH=$(pwd)
CURRENT_FILE_PATH="$(dirname $0)"

DOCKER_IMAGE_NAME="progress0407/app-ver2"
DOCKER_FILE_PATH="./BuildJarDockerfile"
DOCKER_CONTEXT_PATH="." # 실행 위치는 현재 모듈이라 가정

cd "$CURRENT_FILE_PATH"

docker build  \
  --build-arg SPRING_PROFILE="$SPRING_PROFILE" \
  --build-arg SERVER_VERSION="$SERVER_VERSION" \
  -t $DOCKER_IMAGE_NAME \
  -f $DOCKER_FILE_PATH  $DOCKER_CONTEXT_PATH

# 원래의 디렉터리로 돌아가지 않아도 정상 동작한다 ...
#   cd $SHELL_COMMAND_PATH
#   echo "NOW RETURN = $(pwd)"


