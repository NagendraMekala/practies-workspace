# Start with a base image containing Java runtime
FROM alpine-jdk:base

MAINTAINER nagendra.kldm@gmail.com

RUN apk --no-cache add netcat-openbsd

# The application's jar file
COPY springboot-elk-logger-0.0.1-SNAPSHOT.jar /opt/lib/

# Make port 9411 available to the world outside this container
EXPOSE 7777

# Run the jar file 
ENTRYPOINT ["java","-jar","/opt/lib/springboot-elk-logger-0.0.1-SNAPSHOT.jar"]

# Add a volume pointing to /var/lib/micro-meter-volume
VOLUME /var/lib/springboot-elk-volume