FROM java:8
EXPOSE 8027
ADD /target/Details-1.0.0.jar Details-1.0.0.jar
ENTRYPOINT ["java", "-Deureka.client.serviceUrl.defaultZone=http://{ip}:8023/eureka", "-Dspring.rabbitmq.host={ip}", "-jar","Details-1.0.0.jar"]