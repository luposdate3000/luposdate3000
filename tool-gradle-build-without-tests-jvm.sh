#!/bin/bash
gradle --build-file="build.gradle.jvm" build -x test
(
        cd buildJvm/distributions
        tar -xf luposdate3000.tar
)
