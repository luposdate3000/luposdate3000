#!/bin/bash
#grep -rnw -e class -e object -e interface src/luposdate3000_* | sed -E "s/^(([^:]*)\/([a-zA-Z]*).kt):[0-9]*:[ a-zA-Z]*(class|object|interface) *([a-zA-Z0-9]+).*$/\1,\3,\5/g"
grep -rnw -e class -e object -e interface src/luposdate3000_* | sort | sed -E "s/^(([^:]*)\/([a-zA-Z]*).kt):[0-9]*:[ a-zA-Z]*(class|object|interface) *([a-zA-Z0-9]+).*$/\1,\3/g" > f
grep -rnw -e class -e object -e interface src/luposdate3000_* | sort | sed -E "s/^(([^:]*)\/([a-zA-Z]*).kt):[0-9]*:[ a-zA-Z]*(class|object|interface) *([a-zA-Z0-9]+).*$/\1,\5/g" > g
diff f g \
	| grep -v "ValueDefinitions.kt" \
	| grep -v "TripleStoreFeatureParams.kt" \
	| grep -v "Exceptions.kt" \
	| grep -v "TurtleScanner.kt" \
	| grep -v "SPARQLScanner.kt" \
	| grep -v "Main.kt" \
	| grep -v "SPARQLParser.kt" \
	| grep -v "SPARQLScanner.kt" \
	| grep -v "SPARQLHelper.kt" \
	| grep -v "Runtime.kt" \
	| grep -v "Model.kt"
