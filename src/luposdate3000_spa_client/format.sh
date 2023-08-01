js-beautify *html *js src/*js *json resources/**/*json resources/*json 
rm index.html2
xmllint --html --format src/index.html > index.html2
mv index.html2 src/index.html
