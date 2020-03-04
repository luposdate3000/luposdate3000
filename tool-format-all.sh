#!/bin/bash
./tool-format-sort-imports.sh
/opt/idea-IC-193.5662.53/bin/format.sh $(find src -type f -name "*.kt" | grep -v "/resources/")
/opt/idea-IC-193.5662.53/bin/format.sh $(find . -type f -name "*.kts" | grep -v "/resources/")
