cd log
for f in $(find -name "*sp2b*Distributed*.tex" | sed "s/-[0-9]*-/-/g" | sort | uniq)
do
	pdflatex $f
done
rm *.aux *.log combined.pdf
pdfunite *.pdf combined.pdf
