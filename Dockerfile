FROM openjdk:8
COPY buildJvm/distributions/luposdate3000/lib /usr/src/myapp
COPY resources /usr/src/myapp/resources
WORKDIR /usr/src/myapp
EXPOSE 80
#ENTRYPOINT ["java", "-classpath", "./*", "lupos.TestKt"]
ENTRYPOINT ["java", "-classpath", "./*", "lupos.s14endpoint.EndpointImplKt"]
