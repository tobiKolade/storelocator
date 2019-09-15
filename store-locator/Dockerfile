FROM java:8

MAINTAINER Tobi Oladimeji <tobioladimeji@yahoo.com>

EXPOSE 8080

# Set OS time zone
ENV TZ Africa/Lagos

# Setup
RUN mkdir -p /opt/store-locator

WORKDIR /opt/store-locator

# Bundle config source
COPY . /opt/store-locator

ADD target/store-locator-1.0.jar /opt/store-locator/store-locator.jar
COPY target/classes/logback-spring.xml /opt/store-locator/config/

CMD ["java", "-jar", "-Dspring.profiles.active=docker", "store-locator.jar"]