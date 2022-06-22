#!/bin/bash
while true
do
 echo "cleaning up" $(date) >> clean.log
 ./clean_models.py
 sleep 300
done
