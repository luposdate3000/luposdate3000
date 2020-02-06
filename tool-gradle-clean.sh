#!/bin/bash
mkdir log
gradle --build-file="build.gradle.jvm" clean &
gradle clean &
wait
