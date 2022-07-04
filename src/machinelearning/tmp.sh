#!/bin/bash
nohup ./12_lupos_eval.py &>lupos.out &
rm -rf tasks
mkdir tasks models
i=0
for x in 3_3 4_4 5_5 6_6 7_7 8_8 9_9 10_10 11_11 12_12 13_13 14_14 15_15 16_16 17_17 18_18 19_19 20_20
do
arr=(${x//_/ })
ii=$(printf "%03d" $i)
echo ./11_joinopti_agent_train.py ${arr[0]} ${arr[1]} 128 0.7 /mnt/luposdate-testdata/sp2b/1048576/complete.n3 /src/luposdate3000/src/machinelearning/models/ > tasks/$ii.sh
chmod +x tasks/$ii.sh
i=$((i+1))
done
for upper in 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
do
for x in 3_3 4_4 5_5 6_6 7_7 8_8 9_9 10_10 11_11 12_12 13_13 14_14 15_15 16_16 17_17 18_18 19_19 20_20
do
arr=(${x//_/ })
for y in 2048 4096 8192 16384 32768 65536 131072 262144
do
ii=$(printf "%03d" $i)
echo ./12_joinopti_agent_eval.py 1 $upper 128 0.7 /mnt/luposdate-testdata/sp2b/1048576/complete.n3 models/model_${arr[0]}_${arr[1]}_128_0.7_${y}.model > tasks/$ii.sh
chmod +x tasks/$ii.sh
i=$((i+1))
done
done
done
find tasks/ -type f | sort | xargs -P 6 -n 1 bash -c
wait
