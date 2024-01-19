#!/bin/sh

#
# input example.
# `sh build-image.sh prod`
#

set -e
# shellcheck disable=SC2039
source ./color-echo.sh

SPRING_PROFILE=${1:-"local-mem"}

# shellcheck disable=SC2034
SHELL_COMMAND_PATH=$(pwd)
CURRENT_FILE_PATH="$(dirname $0)"

DOCKER_IMAGE_NAME="progress0407/simple-app"
DOCKER_FILE_PATH="./BuildJarDockerfile"
DOCKER_CONTEXT_PATH="../.."

cd "$CURRENT_FILE_PATH"

docker build  \
  --build-arg SPRING_PROFILE="$SPRING_PROFILE" \
  -t $DOCKER_IMAGE_NAME \
  -f $DOCKER_FILE_PATH  $DOCKER_CONTEXT_PATH

# 원래의 디렉터리로 돌아가지 않아도 정상 동작한다 ...
#   cd $SHELL_COMMAND_PATH
#   echo "NOW RETURN = $(pwd)"

# --build-arg SERVER_VERSION="100" \
