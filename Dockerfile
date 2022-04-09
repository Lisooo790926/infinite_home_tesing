FROM openjdk:11.0.14.1

COPY ./target/library-1.0.jar library.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/library.jar"]