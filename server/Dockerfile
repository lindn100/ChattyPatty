FROM openjdk:jdk-alpine

COPY ChatServer.class /deployments/
COPY ChattyPatty.class /deployments/
COPY ClientHandler.class /deployments/
COPY responses.txt /deployments/

CMD cd /deployments; java ChatServer
