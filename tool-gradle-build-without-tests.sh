#!/bin/bash
gradle --build-file="build.gradle.jvm" build -x test &
gradle linkReleaseExecutableLinuxX64 &
wait
(
        cd buildJvm/distributions
        tar -xf luposdate3000.tar
)
