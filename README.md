# docker-stack-rest
A REST API to proxy requests for docker stack commands.


Docker has created a huge amount of REST API capabilities including the ability to create 
[secrets](https://docs.docker.com/engine/api/v1.30/#tag/Secret), 
[networks](https://docs.docker.com/engine/api/v1.30/#tag/Network), 
[volumes](https://docs.docker.com/engine/api/v1.30/#tag/Volume), and 
[services](https://docs.docker.com/engine/api/v1.30/#tag/Service). Secrets, 
networks, volumes, and services are what make up the fundamentals of a stack.

What is missing from Docker's REST API is the ability to deploy an entire Docker stack. This project implements 
a REST API that mimics the `docker stack` REST API (see the output from `docker stack --help`).
