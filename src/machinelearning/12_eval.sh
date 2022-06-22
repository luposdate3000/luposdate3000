#!/bin/bash
#for f in models/*
#do
#    ./12_joinopti_agent_eval.py 2 4 128 0.7 "/mnt/luposdate-testdata/sp2b/1048576/complete.n3" $f &> $f.out &
#done

find models -name "*model" | xargs -P 1 -n 1 -t -I {} ./12_joinopti_agent_eval.py 2 4 128 0.7 "/mnt/luposdate-testdata/sp2b/1048576/complete.n3" {}
find models -name "*model" | xargs -P 1 -n 1 -t -I {} ./12_joinopti_agent_eval.py 2 8 128 0.7 "/mnt/luposdate-testdata/sp2b/1048576/complete.n3" {}
find models -name "*model" | xargs -P 1 -n 1 -t -I {} ./12_joinopti_agent_eval.py 2 16 128 0.7 "/mnt/luposdate-testdata/sp2b/1048576/complete.n3" {}
find models -name "*model" | xargs -P 1 -n 1 -t -I {} ./12_joinopti_agent_eval.py 2 32 128 0.7 "/mnt/luposdate-testdata/sp2b/1048576/complete.n3" {}
find models -name "*model" | xargs -P 1 -n 1 -t -I {} ./12_joinopti_agent_eval.py 2 64 128 0.7 "/mnt/luposdate-testdata/sp2b/1048576/complete.n3" {}
find models -name "*model" | xargs -P 1 -n 1 -t -I {} ./12_joinopti_agent_eval.py 2 128 128 0.7 "/mnt/luposdate-testdata/sp2b/1048576/complete.n3" {}
wait
