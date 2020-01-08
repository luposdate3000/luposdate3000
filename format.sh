#for f in $(find -name "*.kt")
for f in $(git diff-tree --no-commit-id --name-only -r HEAD | grep ".kt")
do
	echo formatting $f
	/opt/idea-IC-193.5662.53/bin/format.sh $f
done
