#!/bin/bash
for f in figure*gnuplot ; do ./$f;done
for i in *.svg;do rsvg-convert -f pdf -o ${i%.*}.pdf $i;done
pdftk figureevaluatedimage*.pdf output figureevaluatedimage.pdf
pdftk figurestepimage*.pdf output figurestepimage.pdf
