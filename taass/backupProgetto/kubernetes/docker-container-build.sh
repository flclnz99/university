#! /bin/bash

##### BUILD #####
docker build ./My\ Music\ Space//Back-end/chat-service --tag aamshaegar/chat-service:1.0.0
docker build ./My\ Music\ Space//Back-end/discovery-service --tag aamshaegar/discovery-service:1.0.0
docker build ./My\ Music\ Space//Back-end/log-service --tag aamshaegar/log-service:1.0.0
docker build ./My\ Music\ Space//Back-end/music-service --tag aamshaegar/music-service:1.0.0
docker build ./My\ Music\ Space//Back-end/shop-service --tag aamshaegar/shop-service:1.0.0 
docker build ./My\ Music\ Space//Front-end --tag aamshaegar/front-end:1.0.0 
docker build ./My\ Music\ Space//Back-end/auth-service --tag aamshaegar/auth-service:1.0.0 
docker build ./My\ Music\ Space//Back-end/api-gateway-service --tag aamshaegar/api-gateway-service:1.0.0


##### PUSH #####
docker push aamshaegar/chat-service:1.0.0
docker push aamshaegar/discovery-service:1.0.0
docker push aamshaegar/log-service:1.0.0
docker push aamshaegar/music-service:1.0.0
docker push aamshaegar/shop-service:1.0.0
docker push aamshaegar/front-end:1.0.0
docker push aamshaegar/auth-service:1.0.0
docker push aamshaegar/api-gateway-service:1.0.0