#! /bin/bash
### RUN ALL COMPONENTS ###

######## DISCOVERY ########
kubectl apply -f ./discovery

######## API GATEWAY ########
kubectl apply -f ./api-gateway

######## FRONT END ########
kubectl apply -f ./front-end



######## DATABASE ########
kubectl apply -f ./mongoDB-chat
kubectl apply -f ./mongoDB-log
kubectl apply -f ./mongoDB-shop
kubectl apply -f ./mysqlDB-auth
kubectl apply -f ./mysqlDB-music


###### MICSROSERVICES ######
kubectl apply -f ./microservices


######## RABBITMQ ########
kubectl apply -f ./rabbit



