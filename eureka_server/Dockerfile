FROM gradle:5.4.1-jdk8-alpine AS appimage

ADD build/libs/eureka_server*.jar ./eureka_server.jar

EXPOSE 8761
CMD ["java","-jar","eureka_server.jar"]