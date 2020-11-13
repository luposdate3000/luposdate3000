echo "#!/usr/bin/env kotlin" > allSources.main.kts
for f in test* RadixTree.main.kts
do
	cat $f | grep "^import" | grep -v "kscript" >> allSources.main.ktsa
done
cat allSources.main.ktsa | sort | uniq >> allSources.main.kts
rm allSources.main.ktsa
for f in test* RadixTree.main.kts.txt
do
	cat $f | grep -v "^import" | grep -v "kscript" >> allSources.main.kts
done
chmod +x allSources.main.kts
time ./allSources.main.kts
