FROM openjdk:8-alpine

# Required for starting application up.
RUN apk update && apk add bash

WORKDIR /app

COPY target/planetapi.jar /app/

CMD ["java", "-jar","./planetapi.jar"]