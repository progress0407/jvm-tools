#!/bin/sh

while true; do
    status=$(curl -s "$(minikube ip):30000/actuator/health")

    if echo "$status" | grep -q "UP"; then
        echo "## connect!"
    else
        echo "## non conn T.T"
    fi
    sleep 1
done
