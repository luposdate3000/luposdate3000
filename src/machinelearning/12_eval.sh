#!/bin/bash
for f in models/*
do
    ./12_joinopti_agent_eval.py 2 128 128 0.7 "/mnt/luposdate-testdata/sp2b/1048576/complete.n3" $f &> $f.out &
done
wait
