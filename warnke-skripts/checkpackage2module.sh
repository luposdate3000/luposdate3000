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
for f in $(grep -r package src | grep "\.kt:" | sed "s-^src/luposdate3000_--g" | sed "s-/.*:package lupos.-:-g" | sed "s/\./_/g" | sort | uniq | grep -v package)
do
arrIN=(${f//:/ })
if [[ "${arrIN[0]}" == "${arrIN[1]}"* ]] || [[ "${arrIN[1]}" == "${arrIN[0]}"* ]] ;
then
echo ok >> /dev/null
else
echo wrong .. module "'" ${arrIN[0]} "'" contains package "'" ${arrIN[1]} "'"
fi
done
