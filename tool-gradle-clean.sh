#!/bin/bash
gradle --build-file="build.gradle.jvm" clean &
gradle clean &
wait
