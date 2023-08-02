js-beautify *html *js src/*js *json resources/**/*json resources/*json 
rm index.html2
tidy -indent -wrap 1000 -modify src/index.html
