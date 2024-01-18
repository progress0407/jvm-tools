#!/bin/sh

SHELL_PATH=$(pwd)
CURRENT_PATH="$(dirname $0)"

echo "SHELL_PATH = $SHELL_PATH"
echo "CURRENT_PATH = $CURRENT_PATH"

cd $CURRENT_PATH

docker build  \
  --build-arg SPRING_PROFILE="local-mem" \
  --build-arg USER_SERVER_VERSION="100" \
  -t app-img \
  -f app/Simple-Build-Jar-Dockerfile ../..

#   cd $SHELL_PATH
#   echo "NOW RETURN = $(pwd)"
