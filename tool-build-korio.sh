#!/bin/bash
cd korio
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
gradle publishToMavenLocal
