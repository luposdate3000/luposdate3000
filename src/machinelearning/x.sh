grep -rl 12_joinopti_agent_eval.py tasks/* | sort | xargs -P 7 -n 1 bash -c
grep -rl 11_joinopti_agent_train.py  tasks/* | sort | xargs -P 7 -n 1 bash -c
