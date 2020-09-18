#!/bin/bash
./generate-buildfile-inline.kts
find src.generated -name *.kt -print0 | xargs -0 -P 12 -n 20 ./generate-buildfile-inline.kts Enable
