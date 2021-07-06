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
mkdir tmp
./gradlew build
for testcase in Test_Kv Test_Triple_Index Test_Dictionary Test_Dictionary_Encoding Test_Vk
do
	for memorymode in Inmemory Persistent
	do
		for i in 0
		do
			(
				export LUPOS_HOME=/tmp/luposdate3000.$testcase.$memorymode.$i/
				j=$RANDOM
				echo "./launcher.main.kts --run --mainClass=$testcase --Buffer_Manager=$memorymode --runArgument_Luposdate3000_Launch_$testcase:arg=$j"
				./launcher.main.kts --run --mainClass=$testcase --Buffer_Manager=$memorymode --runArgument_Luposdate3000_Launch_$testcase:arg=$j > afl.$testcase.$memorymode.$i.log 2>&1
			)&
		done
	done
done
for testcase in Test_Buffermanager Test_Int_Array
do
	for memorymode in Inmemory Persistent Persistent_Cached
	do
		for i in 0
		do
			(
				export LUPOS_HOME=/tmp/luposdate3000.$testcase.$memorymode.$i/
				j=$RANDOM
				echo "./launcher.main.kts --run --mainClass=$testcase --Buffer_Manager=$memorymode --runArgument_Luposdate3000_Launch_$testcase:arg=$j"
				./launcher.main.kts --run --mainClass=$testcase --Buffer_Manager=$memorymode --runArgument_Luposdate3000_Launch_$testcase:arg=$j > afl.$testcase.$memorymode.$i.log 2>&1
			)&
		done
	done
done

wait
