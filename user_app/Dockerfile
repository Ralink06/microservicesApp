FROM gradle:5.4.1-jdk8-alpine AS appimage

ADD build/libs/user_app*.jar ./user_app.jar

EXPOSE 8080
CMD ["java","-jar","user_app.jar"]