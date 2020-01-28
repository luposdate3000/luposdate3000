#!/bin/bash
gradle --build-file="build.gradle.jvm" build -x test &
gradle linkReleaseExecutableLinuxX64 &
wait
(
        cd buildJvm/distributions
        tar -xf luposdate3000.tar
)
cat ./buildJvm/distributions/luposdate3000/bin/luposdate3000 | sed "s/lupos.TestKt/lupos.s11endpoint.P2PKt/g"> ./buildJvm/distributions/luposdate3000/bin/luposdate3000-p2p
chmod +x ./buildJvm/distributions/luposdate3000/bin/luposdate3000-p2p
