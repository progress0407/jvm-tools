#!/bin/bash

k="minikube kubectl -- "

# kg -n pod_name pods -l instance='?', blue-green-type='blue' -o jsonpath='{.items[*].status.containerStatuses[*].ready}'

# ka deploy-was-blue.yml
# ka svc-nodeport-blue.yml

$k apply -f deploy-was-green.yml

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

$k delete svc svc-nodeport-blue

$k apply -f svc-nodeport-green.yml

$k delete deploy deploy-was-blue

curl $(minikube ip):30000/actuator/health
echo ""
curl $(minikube ip):30000/version