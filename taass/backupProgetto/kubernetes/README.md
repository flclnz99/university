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

## Docker Hub

In the main folder there is a .sh (_docker-container-build_) which describe a list of sh command for build and update all the docker images needed for running the application.
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
docker push aamshaegar/chat-service:1.0.0
docker push aamshaegar/discovery-service:1.0.0
docker push aamshaegar/log-service:1.0.0
docker push aamshaegar/music-service:1.0.0
docker push aamshaegar/shop-service:1.0.0
docker push aamshaegar/front-end:1.0.0
docker push aamshaegar/auth-service:1.0.0
docker push aamshaegar/api-gateway-service:1.0.0

```

## Minikube [Linux]

First of all, install Minikube: https://minikube.sigs.k8s.io/docs/start/
In the kubernetes folder there are different file .sh, we explain in detail:

- **start_minikube.sh**: Run the cluster and add all necessary Addons
- **run_all_components.sh**: Execute in block some kubectl apply to run all the minikube components
- **delete_all_components.sh**: Remove all the previous component

**[First time]**
Before run assure to modify correctly the /etc/hosts file for linking the minikube ip to the app node ingresses

```bash

# Start minikube
./start_minikube.sh

# get minikube ip
minikube ip
# example output:
# 192.168.59.102

# add minikube ip to /etc/hosts file linking it to the app node ingresses
sudo nano /etc/hosts
# example edit:
# End of section
# 192.168.49.2  api.mymusicspace.it
# 192.168.49.2  webui.mymusicspace.it
# 192.168.49.2  eureka.mymusicspace.it

# 192.168.64.2 api.mymusicspace.it
# 192.168.64.2  webui.mymusicspace.it
# 192.168.64.2  eureka.mymusicspace.it


# check edits on /etc/hosts file
cat /etc/hosts

# apply minikube manifests
./run_all_components.sh

# only for MacOS
brew install watch

# check pods status, more infos and wait untill all of them have 'Running' status, otherwise debug
watch kubectl get all -n homates
# same command but for MacOS
kubectl get pods --watch

# check services
kubectl get services

# check persistent volumes
kubectl get pv

# check persistent volume claims
kubectl get pvc

# see usage metrics, needs metrics-server addon (two alternatives)
watch kubectl top pods

# stop minikube
minikube stop
```
