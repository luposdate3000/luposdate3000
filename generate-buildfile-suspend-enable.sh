#!/bin/bash
./generate-buildfile-suspend.kts
find src.generated -name *.kt -print0 | xargs -0 -P 12 -n 20 ./generate-buildfile-suspend.kts Enable
