#!/bin/bash
printf %s\\n {0..7} | xargs -n 1 -P 1 ./tool-import-all-helper.sh
