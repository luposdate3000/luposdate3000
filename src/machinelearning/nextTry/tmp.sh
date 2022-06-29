#!/bin/bash
nohup ./04_joinopti_agent_eval.py &>lupos.out &
rm -rf tasks
mkdir tasks models
i=0
for x in 4_4 4_128 4_16 4_32 4_64 4_8 8_8 16_16 16_64 32_32 64_64 128_128
do
arr=(${x//_/ })
ii=$(printf "%03d" $i)
echo ./03_joinopti_agent_train.py ${arr[0]} ${arr[1]} 128 0.7 "/mnt/luposdate-testdata/yago1/yago-1.0.0-turtle.nt" /src/luposdate3000/src/machinelearning/models/ > tasks/$ii.sh
chmod +x tasks/$ii.sh
i=$((i+1))
done
for upper in 16 32 64 128
do
for x in 4_4 4_128 4_16 4_32 4_64 4_8 8_8 16_16 16_64 32_32 64_64 128_128
do
arr=(${x//_/ })
for y in 2048 4096 8192 16384 32768 65536 131072 262144
do
ii=$(printf "%03d" $i)
echo ./04_joinopti_agent_eval.py 1 $upper 128 0.7 "/mnt/luposdate-testdata/yago1/yago-1.0.0-turtle.nt" models/model_${arr[0]}_${arr[1]}_128_0.7_${y}.model > tasks/$ii.sh
chmod +x tasks/$ii.sh
i=$((i+1))
done
done
done
find tasks/ -type f | sort | xargs -P 6 -n 1 bash -c
wait
