#!/bin/bash
#for f in models/*
#do
#    ./12_joinopti_agent_eval.py 2 4 128 0.7 "/mnt/luposdate-testdata/sp2b/1048576/complete.n3" $f &> $f.out &
#done


for upper in 128
do
find models -name "*model" \
 | grep \
 -e "_2048.model" \
 -e "_8192.model" \
 -e "_16384.model" \
 -e "_32768.model" \
 -e "_65536.model" \
 -e "_131072.model" \
 -e "_262144.model" \
 -e "_524288.model" \
 -e "_1048576.model" \
 -e "_2097152.model" \
 | xargs -P 6 -n 1 -t -I {} ./12_joinopti_agent_eval.py 1 $upper 128 0.7 "/mnt/luposdate-testdata/sp2b/1048576/complete.n3" {}
done
