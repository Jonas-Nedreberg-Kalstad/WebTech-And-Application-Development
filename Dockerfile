FROM openjdk:20
EXPOSE 8050
ADD target/proflex-springboot-application.jar proflex-springboot-application.jar
ENTRYPOINT ["java","-jar","/proflex-springboot-application.jar"]