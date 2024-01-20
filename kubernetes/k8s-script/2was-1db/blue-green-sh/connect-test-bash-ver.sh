#!/bin/bash

# source ~/.zsh_aliases
# k='minikube kubectl -- '

while true; do

    # status=$(curl -s "$(minikube ip):30000/actuator/health")
    pod_status=$(minikube kubectl -- get pods | grep deploy-was-green | awk '{print $3}')

    # if [[ $status == *"UP"* ]]; then
    if [[ $pod_status == "Running" ]]; then
        echo "## connect!"
    else
        echo "## non conn T.T"
    fi;
    sleep 1;
done;
