#!/bin/bash

set -e
source ./color-echo.sh

echo_blue "[ Building Jar And Running ... ]"

docker-compose -f ./docker-script/docker-compose.yml up -d

echo_green "[ Process completed successfully ! ]"
