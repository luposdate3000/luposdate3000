echo "#!/bin/kscript" > allSources.kts
for f in test* RadixTree.kts.txt
do
	cat $f | grep "^import" | grep -v "kscript" >> allSources.ktsa
done
cat allSources.ktsa | sort | uniq >> allSources.kts
rm allSources.ktsa
for f in test* RadixTree.kts.txt
do
	cat $f | grep -v "^import" | grep -v "kscript" >> allSources.kts
done
chmod +x allSources.kts
time ./allSources.kts
