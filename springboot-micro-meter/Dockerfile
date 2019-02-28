# Start with a base image containing Java runtime
FROM alpine-jdk:base

MAINTAINER nagendra.kldm@gmail.com

RUN apk --no-cache add netcat-openbsd

# The application's jar file
COPY springboot-micro-meter-0.0.1-SNAPSHOT.jar /opt/lib/

# Make port 9411 available to the world outside this container
EXPOSE 9595

# Run the jar file 
ENTRYPOINT ["java","-jar","/opt/lib/springboot-micro-meter-0.0.1-SNAPSHOT.jar"]

# Add a volume pointing to /var/lib/micro-meter-volume
VOLUME /var/lib/micro-meter-volume