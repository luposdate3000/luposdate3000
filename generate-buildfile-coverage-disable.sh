#!/bin/bash
./generate-buildfile-coverage.kts
find src.generated -name *.kt -print0 | xargs -0 -P 12 -n 20 ./generate-buildfile-coverage.kts Disable
