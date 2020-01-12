#!/bin/bash

grep -rw "import" --include "*.kt" | grep -F "*" | sed "s/:import.*//g" | sort | uniq
