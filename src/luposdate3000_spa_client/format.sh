js-beautify *js src/*js
tidy -config .tidy.config src/index.html
for f in $(find resources -name "*json") *json
do
content=$(cat $f)
jq . -S <<<$content >$f
done
