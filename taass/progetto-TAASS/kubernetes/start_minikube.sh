#! /bin/bash
minikube --extra-config apiserver.cors-allowed-origins=["http://webui.mymusicspace.it:30010"] start
minikube addons enable ingress
#kubectl delete -A ValidatingWebhookConfiguration ingress-nginx-admission