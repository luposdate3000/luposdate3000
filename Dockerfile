FROM openjdk:14
COPY buildJvm/distributions/luposdate3000/lib /usr/src/myapp
COPY resources /usr/src/myapp/resources
WORKDIR /usr/src/myapp
EXPOSE 80
ENTRYPOINT ["java", "-classpath", "./*", "MainKt"]
