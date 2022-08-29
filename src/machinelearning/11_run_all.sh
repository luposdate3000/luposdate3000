#!/bin/bash
nohup ./12_lupos_eval.py &>lupos.out &
rm -rf tasks
mkdir tasks models
i=0
for x in 3_3 4_4 5_5 6_6 7_7 8_8 9_9 10_10 11_11 12_12 13_13 14_14 15_15 16_16 17_17 18_18 19_19 20_20 3_20 5_18 7_16 10_20 12_18 12_20
do
arr=(${x//_/ })
ii=$(printf "%04d" $i)
echo "./11_joinopti_agent_train.py ${arr[0]} ${arr[1]} 128 0.7 /mnt/luposdate-testdata/wordnet/wordnet.nt /src/luposdate3000/src/machinelearning/models/" > tasks/$ii.sh
echo "retVal=\$?" >> tasks/$ii.sh
echo "if [ \$retVal -eq 0 ]; then" >> tasks/$ii.sh
echo "truncate -s0 tasks/$ii.sh" >> tasks/$ii.sh
echo "fi" >> tasks/$ii.sh
chmod +x tasks/$ii.sh
i=$((i+1))
for y in 2048 4096 8192 16384 32768 65536 131072 262144 393216 524288 655360 786432 917504 1048576
# 1179648 1310720 1441792 1572864
do
ii=$(printf "%04d" $i)
echo "./12_joinopti_agent_eval.py 1 20 128 0.7 /mnt/luposdate-testdata/wordnet/wordnet.nt models/model_${arr[0]}_${arr[1]}_128_0.7_${y}.model" > tasks/$ii.sh
echo "retVal=\$?" >> tasks/$ii.sh
echo "if [ \$retVal -eq 0 ]; then" >> tasks/$ii.sh
echo "truncate -s0 tasks/$ii.sh" >> tasks/$ii.sh
echo "fi" >> tasks/$ii.sh
chmod +x tasks/$ii.sh
i=$((i+1))
done
done
find tasks/ -type f | sort | xargs -P 7 -n 1 bash -c
wait
