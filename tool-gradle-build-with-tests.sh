#!/bin/bash
gradle --build-file="build.gradle.jvm" build &
gradle build &
wait
(
        cd buildJvm/distributions
        tar -xf luposdate3000.tar
)
