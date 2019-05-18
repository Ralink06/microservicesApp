FROM gradle:5.4.1-jdk8-alpine AS appimage

ADD build/libs/gateway*.jar ./gateway.jar

EXPOSE 8080
CMD ["java","-jar","gateway.jar"]