#!/bin/bash
set -e
set -o pipefail

mkdir -p /src/luposdate3000/src/machinelearning/models/



./11_joinopti_agent_train.py 4 128 128 0.7 /mnt/luposdate-testdata/sp2b/1048576/complete.n3 /src/luposdate3000/src/machinelearning/models/ &
./11_joinopti_agent_train.py 4 16 128 0.7 /mnt/luposdate-testdata/sp2b/1048576/complete.n3 /src/luposdate3000/src/machinelearning/models/ &
./11_joinopti_agent_train.py 4 32 128 0.7 /mnt/luposdate-testdata/sp2b/1048576/complete.n3 /src/luposdate3000/src/machinelearning/models/ &
./11_joinopti_agent_train.py 4 4 128 0.7 /mnt/luposdate-testdata/sp2b/1048576/complete.n3 /src/luposdate3000/src/machinelearning/models/ &
./11_joinopti_agent_train.py 4 64 128 0.7 /mnt/luposdate-testdata/sp2b/1048576/complete.n3 /src/luposdate3000/src/machinelearning/models/ &
./11_joinopti_agent_train.py 4 8 128 0.7 /mnt/luposdate-testdata/sp2b/1048576/complete.n3 /src/luposdate3000/src/machinelearning/models/ &
wait
./11_joinopti_agent_train.py 8 8 128 0.7 /mnt/luposdate-testdata/sp2b/1048576/complete.n3 /src/luposdate3000/src/machinelearning/models/ &
./11_joinopti_agent_train.py 16 16 128 0.7 /mnt/luposdate-testdata/sp2b/1048576/complete.n3 /src/luposdate3000/src/machinelearning/models/ &
./11_joinopti_agent_train.py 16 64 128 0.7 /mnt/luposdate-testdata/sp2b/1048576/complete.n3 /src/luposdate3000/src/machinelearning/models/ &
./11_joinopti_agent_train.py 32 32 128 0.7 /mnt/luposdate-testdata/sp2b/1048576/complete.n3 /src/luposdate3000/src/machinelearning/models/ &
./11_joinopti_agent_train.py 64 64 128 0.7 /mnt/luposdate-testdata/sp2b/1048576/complete.n3 /src/luposdate3000/src/machinelearning/models/ &
./11_joinopti_agent_train.py 128 128 128 0.7 /mnt/luposdate-testdata/sp2b/1048576/complete.n3 /src/luposdate3000/src/machinelearning/models/ &
wait





