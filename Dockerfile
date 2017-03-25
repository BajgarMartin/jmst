FROM java:8-jre
MAINTAINER Martin Bajgar <bajgarmartin@gmail.com>
ADD ./target/jmst.jar /app/
CMD ["java", "-jar", "/app/jmst.jar"]
EXPOSE 8080
