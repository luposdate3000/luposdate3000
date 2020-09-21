#!/bin/bash
$(echo $0 | sed "s-/[^/]*\$-/-g")generate-buildfile-inline.kts
find src.generated -name *.kt -print0 | xargs -0 -P 12 -n 20 $(echo $0 | sed "s-/[^/]*\$-/-g")generate-buildfile-inline.kts Enable
