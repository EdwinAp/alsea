#!/bin/bash

mvn clean install

docker build -t alsea_backend:latest .

docker-compose up -d
