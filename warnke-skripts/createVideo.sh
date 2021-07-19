#!/bin/bash
./gradlew :src:luposdate3000_simulator_iot:jvmTest --tests=*campusDistributedCase_getAllSpacesOfParkingArea
ffmpeg -i './src/luposdate3000_simulator_iot/visual-frame-%04d.svg' -vf format=yuv420p out.mp4
