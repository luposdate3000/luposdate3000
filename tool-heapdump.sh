#!/bin/bash
jmap -dump:live,format=b,file="$(date +"%Y_%m_%d_%I_%M_%p").hprof" $1
