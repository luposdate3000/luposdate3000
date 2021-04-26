#!/bin/bash
jmap -dump:live,format=b,file="$(date +"%Y_%m_%d_%H_%M_%S").hprof" $1
