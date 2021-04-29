#!/bin/bash
# This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
# Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
#
# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation, version 3.
#
# This program is distributed in the hope that it will be useful, but
# WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
# General Public License for more details.
#
# You should have received a copy of the GNU General Public License
# along with this program. If not, see <http://www.gnu.org/licenses/>.
echo "starting $1"
intermediates=( ${LUPOS_REAL_WORLD_DATA_ROOT}/yago1/yago-1.0.0-turtle.ttl ${LUPOS_REAL_WORLD_DATA_ROOT}/yago2/yago-2.n3 ${LUPOS_REAL_WORLD_DATA_ROOT}/yago3/yago3.ttl ${LUPOS_REAL_WORLD_DATA_ROOT}/barton/barton.nt ${LUPOS_REAL_WORLD_DATA_ROOT}/yago2s/yago-2.5.3-turtle-simple.ttl ${LUPOS_REAL_WORLD_DATA_ROOT}/btc2019/btc2019-triples.nt ${LUPOS_REAL_WORLD_DATA_ROOT}/yago4/yago-all.nt ${LUPOS_REAL_WORLD_DATA_ROOT}/btc2010/btc-2010.n4 )
i=$1
intermediate=${intermediates[i]}
echo $i $intermediate
(
./launcher.main.kts \
 --run \
 --mainClass=Import \
 --inlineMode=Disable \
 --releaseMode=Disable \
 --garbageCollector=Shenandoah \
 --partitionMode=None \
 --runArgument_Luposdate3000_Launch_Import:inputFileName=$intermediate \
 ;
) > ${intermediate}parse-logs.log 2>&1
