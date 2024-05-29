#! /bin/bash

kubectl delete pod --all
kubectl delete deployment --all
kubectl delete statefulset --all
kubectl delete ingress --all

kubectl delete service discovery-service-svc
kubectl delete service front-end-svc
kubectl delete service api-gateway-service
kubectl delete service mongo-chat-svc
kubectl delete service mongo-log-svc
kubectl delete service mongo-shop-svc
kubectl delete service mysql-auth-svc
kubectl delete service mysql-music-svc
kubectl delete service rabbitmq

