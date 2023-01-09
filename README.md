# RSO: admin microservice

## Prerequisites


## Build and run commands
```bash
mvn clean package
cd api/target
java -jar admin-api-1.0.0-SNAPSHOT.jar
```
Available at: localhost:8080/v1/admin

## Run in IntelliJ IDEA
Add new Run configuration and select the Application type. In the next step, select the module api and for the main class com.kumuluz.ee.EeApplication.

Available at: localhost:8080/v1/admin

## Docker commands
```bash
docker build -t admin .   
docker images
docker run admin    
docker docker tag admin xineeeee/admin   
docker docker push xineeeee/admin
docker ps
```

## Kubernetes
```bash
kubectl version
kubectl --help
kubectl get nodes
kubectl create -f admin-deployment.yaml 
kubectl apply -f admin-deployment.yaml 
kubectl get services 
kubectl get deployments
kubectl get pods
kubectl logs admin-deployment-6f59c5d96c-rjz46
kubectl delete admin-deployment-6f59c5d96c-rjz46
```
Secrets: https://kubernetes.io/docs/concepts/configuration/secret/

