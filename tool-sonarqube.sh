#!/bin/bash
docker run -d --name sonarqube -p 9000:9000 sonarqube
gradle --build-file="build.gradle.jvm" sonarqube
