#!/bin/bash

rm $1.javap
javap -c $1 > $(echo $1 | sed 's/\$/_/g').javap
