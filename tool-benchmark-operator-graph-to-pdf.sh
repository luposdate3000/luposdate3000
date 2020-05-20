cd log
for f in $(find -name "sp2b*Distributed*.tex")
do
	pdflatex $f
done
rm *.aux *.log combined.pdf
pdfunite *.pdf combined.pdf
