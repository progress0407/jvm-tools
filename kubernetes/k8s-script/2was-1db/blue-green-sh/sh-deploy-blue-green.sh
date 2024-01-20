#!/bin/bash

# assign k keyword
k="minikube kubectl -- "

# blue deploy script
# ka deploy-was-blue.yml
# ka svc-nodeport-blue.yml

# deploy blue
$k apply -f deploy-was-green.yml

# wait while blue deploy
while true; do

    # status=$(curl -s "$(minikube ip):30000/actuator/health")
    status=$($k get pods | grep deploy-was-green | awk '{print $3}')

    # if [[ $status == *"UP"* ]]; then
    if [[ $status == "Running" ]]; then
        echo "Blue is Running!"
        break;
    fi;
    sleep 1;
done;

# delete blue traffic
$k delete svc svc-nodeport-blue

# allow green traffic
$k apply -f svc-nodeport-green.yml

# now, close blue traffic
$k delete deploy deploy-was-blue

# display blue's status
curl $(minikube ip):30000/actuator/health
echo ""
curl $(minikube ip):30000/version