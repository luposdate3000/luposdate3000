rm -rf src/luposdate3000_coverage_merged
mkdir src/luposdate3000_coverage_merged
for f in $(ls src \
	| grep -v machinelearning \
	| grep -v luposdate3000_scripting \
	| grep -v luposdate3000_spa_client \
	| grep -v luposdate3000_launch \
	)
do
echo $f
cp -r src/$f/* src/luposdate3000_coverage_merged
done
cat src/*/module_config | sort | uniq | grep dependency > src/luposdate3000_coverage_merged/module_config
./launcher.main.kts --setup --intellijMode=Disable
cat src/luposdate3000_coverage_merged/build.gradle.kts \
	| grep -v evaluationDependsOn \
	| grep -v "implementation.project" \
	| grep -v srcDir.*luposdate3000_shared_inline \
	> src/luposdate3000_coverage_merged/build.gradle.kts2
mv src/luposdate3000_coverage_merged/build.gradle.kts2 src/luposdate3000_coverage_merged/build.gradle.kts
