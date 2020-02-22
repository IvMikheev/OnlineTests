FROM java:8
VOLUME /tmp
ADD target/online-test.jar online-test.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "online-test.jar"]