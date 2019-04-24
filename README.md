# Helidon ATP

This example implements a simple database access to oracle ATP using MicroProfile&Mybatis

## Prerequisites

1. Maven 3.5 or newer
2. Java SE 11
3. Docker 17 or newer (if you want to build and run docker images)
4. Kubectl 1.7.4 or newer for deploying to Kubernetes

Verify prerequisites
```
java -version
mvn --version
docker --version
kubectl version --short
```

## Build

### Prepare
Create table and insert date.

--------------------------------------------------------
--  DDL for Table EMPLOYEE
--------------------------------------------------------
CREATE TABLE "EMPLOYEE" 
   ("EMPLOYEE_ID" VARCHAR2(20 BYTE), 
	"FIRST_NAME" VARCHAR2(20 BYTE), 
	"LAST_NAME" VARCHAR2(20 BYTE)
   );

Insert into EMPLOYEE (EMPLOYEE_ID,FIRST_NAME,LAST_NAME) values ('101','Bill','Gates');

### Step.1 
Copy your atp wallet files to override /src/main/wallet

```sh
cp YOUR_WALLET_FILE ./src/main/wallet
```

### Step.2
Modify deploy2k8s.sh to input your information.

```sh
vi deploy2k8s.sh
```

### Step.3
Execute deploy2k8s.sh

```sh
./deploy2k8s.sh
```

### Step.4
Get your node ip and serivce port(default is 30080)

```sh
kubectl get nodes
```

```sh
kubectl get service
```

### Step.5
Try

```
curl YOUR_NODE_IP:YOUR_SERVICE_PORT/atp/employees
```
