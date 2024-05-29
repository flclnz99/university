# MyMusicSpace

Tecniche e architetture avanzate per lo sviluppo del software (TAASS)
- Year 2023-24
- Prof. Giovanna Petrone

Students: 
- Aldo Rambaudo
- Corrado Picone
- Lorenzo Falchi
- Michele Colombino

---
# Package explorer
This section contains a quick refer to the package structure of the application
- **My Music Space**
    - **Back-end**: all the micro-services developed are located under the back-end folder
    - **Front-end**: the react application we developed

- **Spikes**: This folder contains all the principal spikes we work about during the project management
- **Kubernetes**: This folder contains all the kubernetes manifest used to initialize the minikube cluster

---
# How to run
This section contains a tutorial to update the images on Docker Hub (if needed) and set up the minikube cluster.

## Run Containers
For simply build and run locally all the docker containers for testing purposes, under the **My Music Space** directory there is a **docker-compose.yaml**. 
Run the file with the following command:
```bash
docker compose up --build
```
All the docker container described in the docker-compose file will run locally, at the specified ports. In detail, we show some of the main docker containers refered to the microservices developed:
```bash
- api-gatway-service:   (localhost:8080)      # each routes will pass by the api gateway service
- discovery-service     (localhost:8761)
- auth-service          (localhost:8090)
- chat-service          (localhost:8091)
- music-service         (localhost:8092)
- shop-service          (localhost:8093)
- log-service           (localhost:8094)
```

## Docker Hub
In alternative to the **docker-compose.yaml** file, in the main folder there is a .sh, **docker-container-build**, which describe a list of sh command for build, update and push all the docker images on the personal docker-hub, needed for running the application, both in local and in the minikube cluster. 
Here is the content of the file:

```bash
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
## Previously run "docker login" ##
docker push aamshaegar/chat-service:1.0.0
docker push aamshaegar/discovery-service:1.0.0
docker push aamshaegar/log-service:1.0.0
docker push aamshaegar/music-service:1.0.0
docker push aamshaegar/shop-service:1.0.0
docker push aamshaegar/front-end:1.0.0
docker push aamshaegar/auth-service:1.0.0
docker push aamshaegar/api-gateway-service:1.0.0
```
*Feel free to change aamshaegar with your docker hub account!*

## Minikube [Linux]
First of all, install Minikube: https://minikube.sigs.k8s.io/docs/start/ and kubectl https://kubernetes.io/docs/tasks/tools/. 
In the kubernetes folder there are different file .sh, we explain in detail:
- **start_minikube.sh**: Run the cluster and add all necessary Addons
- **run_all_components.sh**: Execute in block some kubectl apply to run all the minikube components
- **delete_all_components.sh**: Remove all the previous component

**[First time]**
Before run assure to modify correctly the **/etc/hosts** file for linking the minikube ip to the app node ingresses

```bash
# Start minikube
./start_minikube.sh

# get minikube ip
minikube ip
# example output: 
# 192.168.49.2

# add minikube ip to /etc/hosts file linking it to the app node ingresses
sudo nano /etc/hosts
# ...
# End of section
# 192.168.49.2  api.mymusicspace.it
# 192.168.49.2  webui.mymusicspace.it
# 192.168.49.2  eureka.mymusicspace.it

# check edits on /etc/hosts file
cat /etc/hosts

# apply minikube manifests
./run_all_components.sh

# check pods status, more infos and wait untill all of them have 'Running' status, otherwise debug
watch kubectl get all -n homates

# check services
kubectl get services

# check persistent volumes
kubectl get pv

# check persistent volume claims
kubectl get pvc

# see usage metrics, needs metrics-server addon (two alternatives)
watch kubectl top pods

# delete all minikube manifests
./delete_all_components.sh

# stop minikube 
minikube stop
```
---

# Links
In this section we report all the links referred to the developing of the **MyMusicSpace** project

- **Github** page:&emsp;            https://github.com/aamshaegar/Pogetto-TAASS
- **DockerHub** page:&emsp;         https://hub.docker.com/repositories/aamshaegar  
- WebApp **documentation**:&emsp;         https://docs.google.com/document/d/1dm7XAdYdlLNTkll6YT95Sjmz3cM5pwcd7BrWNxiY8os/edit?usp=sharing 
- **Project review** (slide - 6/11/2023):&emsp;        https://docs.google.com/presentation/d/1i5Q6OMum0MDsFqxsMwcbwkR0nbPvQvySuCCmutZWVkY/edit?usp=sharing 
- **First release** (slide):&emsp;        https://docs.google.com/presentation/d/13RQ5hLLqxpc0NFc0QJqm8vffB8DbVakq/edit?usp=sharing&ouid=110250751011780247086&rtpof=true&sd=true
