# docker-stack-rest
A REST API to proxy requests for docker stack commands.

## Description
Docker has created a huge amount of REST API capabilities including the ability to create 
[secrets](https://docs.docker.com/engine/api/v1.30/#tag/Secret), 
[networks](https://docs.docker.com/engine/api/v1.30/#tag/Network), 
[volumes](https://docs.docker.com/engine/api/v1.30/#tag/Volume), and 
[services](https://docs.docker.com/engine/api/v1.30/#tag/Service). Secrets, 
networks, volumes, and services are what make up the fundamentals of a stack.

What is missing from Docker's REST API is the ability to deploy an entire Docker stack. This project implements 
a REST API that mimics the `docker stack` REST API (see the output from `docker stack --help`).

TODO
* implement security
* create container definition suitable for deploy to UCP manager nodes.

## Implementation
This is a simple Java based Spring Boot app that presents a REST API using spring-boot-starter-web.  Each REST service in turn calls out to a shell to invoke the underlying O/S specific `docker stack` commands.  This currently runs locally on a Mac with Docker for Mac installed.  Eventually this will have a Dockerfile with the intention of running only on the UCP manager nodes.

## Runtime
To deploy this application in a container:
```bash
docker run -it -p 8080:8080 -v /var/run/docker.sock:/var/run/docker.sock -v /usr/bin/docker:/usr/bin/docker dockerstack
```
