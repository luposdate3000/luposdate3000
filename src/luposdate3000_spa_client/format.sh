js-beautify *html *js src/*js *json resources/**/*json
rm index.html2
xmllint --html --format index.html > index.html2
mv index.html2 index.html
