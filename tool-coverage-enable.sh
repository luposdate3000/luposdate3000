#!/bin/bash
./tool-coverage.kts Enable $(find src.generated -name *.kt | sort)
