#!/bin/bash
p=$(pwd)
./exec-p2p.sh
cd /opt/bsbmtools-0.2
./benjamin_benchmark.sh
