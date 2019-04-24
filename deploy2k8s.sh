#!/bin/bash

######TODO TODO TODO######
######Modify Below Contents Begin######
echo "######setting######"
DOCKER_SERVER=TODO
DOCKER_REPO=TODO
DOCKER_USERNAME=TODO
DOCKER_PASSWORD=TODO
DOCKER_EMAIL=TODO

DATASOURCE_URL=jdbc:oracle:thin:@TODO?TNS_ADMIN=/app/wallet/
DATASOURCE_USER=TODO
DATASOURCE_PASSWORD=TODO
######Modify Below Contents End######


######IMPORTANT IMPORTANT IMPORTANT######
######DON'T Modify Below Contents before your understande them######

echo "######create app.yaml######"
DOCKER_SERVER=${DOCKER_SERVER} DOCKER_REPO=${DOCKER_REPO} DATASOURCE_URL=${DATASOURCE_URL} DATASOURCE_USER=${DATASOURCE_USER} DATASOURCE_PASSWORD=${DATASOURCE_PASSWORD} envsubst < app.tmpl > app.yaml

echo "######delete application######"
kubectl delete  -f app.yaml

echo "######delete secret ocirsecret######"
kubectl delete secret ocirsecret

echo "######mvn package######"
mvn package

echo "######build docker image######"
docker build -t ${DOCKER_SERVER}/${DOCKER_REPO}/helidon-atp target

echo "######login docker repo######"
docker login --username=${DOCKER_REPO}/${DOCKER_USERNAME} --password=${DOCKER_PASSWORD} ${DOCKER_SERVER}

echo "######push docker image######"
docker push ${DOCKER_SERVER}/${DOCKER_REPO}/helidon-atp

echo "######create secret ocirsecret######"
kubectl create secret docker-registry ocirsecret --docker-server=${DOCKER_SERVER} --docker-username=${DOCKER_REPO}/${DOCKER_USERNAME} --docker-password=${DOCKER_PASSWORD} --docker-email=${DOCKER_EMAIL}

echo "######deploy application######"
kubectl apply -f app.yaml
