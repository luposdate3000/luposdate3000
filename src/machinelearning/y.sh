#!/bin/bash

./11_joinopti_agent_train.py 3 20 128 0.7 /mnt/luposdate-testdata/sp2b/1048576/complete.n3 /src/luposdate3000/src/machinelearning/models/ &
./11_joinopti_agent_train.py 5 18 128 0.7 /mnt/luposdate-testdata/sp2b/1048576/complete.n3 /src/luposdate3000/src/machinelearning/models/ &
./11_joinopti_agent_train.py 7 16 128 0.7 /mnt/luposdate-testdata/sp2b/1048576/complete.n3 /src/luposdate3000/src/machinelearning/models/ &
./11_joinopti_agent_train.py 10 20 128 0.7 /mnt/luposdate-testdata/sp2b/1048576/complete.n3 /src/luposdate3000/src/machinelearning/models/ &
./11_joinopti_agent_train.py 12 18 128 0.7 /mnt/luposdate-testdata/sp2b/1048576/complete.n3 /src/luposdate3000/src/machinelearning/models/ &
./11_joinopti_agent_train.py 12 20 128 0.7 /mnt/luposdate-testdata/sp2b/1048576/complete.n3 /src/luposdate3000/src/machinelearning/models/ &

wait
