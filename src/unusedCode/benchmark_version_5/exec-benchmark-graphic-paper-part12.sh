#!/bin/bash
column_x=2
column_y=11
column_z=7

cat > tmp/part_12a.matrix2.csv <<EOF
1.0,32.0,128.0,512.0,2048.0,8196.0,32768.0,131072.0,524288.0,
0.0,1.0,1.0,1.0,5.0,1.0,1.0,1.0,1.0,
1.0,1.0,1.0,5.0,6.0,6.0,3.0,3.0,1.0,
2.0,1.0,1.0,4.0,6.0,6.0,3.0,3.0,1.0,
3.0,1.0,1.0,5.0,6.0,3.0,3.0,3.0,3.0,
4.0,1.0,6.0,3.0,6.0,4.0,4.0,4.0,3.0,
5.0,1.0,3.0,3.0,6.0,4.0,4.0,4.0,3.0,
6.0,1.0,3.0,6.0,6.0,4.0,4.0,4.0,4.0,
7.0,1.0,4.0,4.0,6.0,4.0,4.0,4.0,4.0,
8.0,3.0,4.0,4.0,4.0,4.0,4.0,4.0,4.0,
9.0,1.0,4.0,3.0,4.0,4.0,4.0,4.0,4.0,
10.0,1.0,4.0,4.0,4.0,4.0,4.0,4.0,4.0,
11.0,1.0,3.0,4.0,4.0,4.0,4.0,4.0,4.0,
12.0,3.0,6.0,4.0,4.0,5.0,5.0,5.0,5.0,
13.0,3.0,3.0,4.0,4.0,5.0,5.0,5.0,5.0,
14.0,3.0,4.0,4.0,5.0,5.0,5.0,5.0,5.0,
15.0,3.0,4.0,4.0,5.0,5.0,5.0,5.0,6.0,
16.0,3.0,6.0,4.0,5.0,5.0,5.0,5.0,5.0,
17.0,3.0,4.0,5.0,5.0,5.0,5.0,5.0,5.0,
18.0,3.0,4.0,4.0,5.0,5.0,5.0,5.0,5.0,
19.0,3.0,4.0,4.0,5.0,6.0,6.0,6.0,5.0,
20.0,4.0,6.0,5.0,5.0,5.0,5.0,5.0,5.0,
21.0,3.0,5.0,4.0,6.0,6.0,6.0,6.0,6.0,
22.0,3.0,5.0,4.0,5.0,5.0,6.0,6.0,6.0,
23.0,3.0,4.0,5.0,5.0,6.0,6.0,6.0,6.0,
24.0,4.0,4.0,4.0,6.0,5.0,6.0,6.0,6.0,
25.0,3.0,4.0,5.0,5.0,6.0,6.0,6.0,6.0,
26.0,3.0,6.0,5.0,5.0,5.0,6.0,6.0,6.0,
27.0,4.0,5.0,4.0,5.0,6.0,6.0,6.0,6.0,
28.0,4.0,4.0,5.0,5.0,6.0,6.0,6.0,6.0,
29.0,3.0,4.0,5.0,5.0,6.0,6.0,6.0,6.0,
30.0,3.0,5.0,5.0,5.0,5.0,6.0,6.0,6.0,
31.0,3.0,5.0,6.0,5.0,6.0,6.0,6.0,6.0,
32.0,5.0,5.0,5.0,5.0,6.0,5.0,6.0,6.0,
33.0,3.0,4.0,5.0,5.0,6.0,5.0,6.0,6.0,
34.0,3.0,4.0,5.0,5.0,6.0,6.0,6.0,6.0,
35.0,4.0,5.0,5.0,5.0,5.0,6.0,6.0,6.0,
36.0,4.0,6.0,5.0,6.0,6.0,6.0,6.0,6.0,
37.0,3.0,4.0,5.0,5.0,6.0,6.0,5.0,6.0,
38.0,3.0,4.0,5.0,5.0,6.0,5.0,6.0,6.0,
39.0,3.0,4.0,5.0,5.0,6.0,6.0,5.0,6.0,
40.0,4.0,5.0,5.0,6.0,6.0,6.0,6.0,6.0,
EOF

rm tmp/part_d* tmp/v*
gnuplot_terminal="set terminal epslatex"
gnuplot_terminal_ending="tex"
gnuplot_terminal="set terminal png size 1920,1080"
gnuplot_terminal_ending="png"
echo "${gnuplot_terminal}" > tmp/graph_12a.plot
echo "set output 'graph_12a.${gnuplot_terminal_ending}'" >> tmp/graph_12a.plot
echo "set datafile separator ','" >> tmp/graph_12a.plot
echo "set notitle" >> tmp/graph_12a.plot
echo "set key inside right top" >> tmp/graph_12a.plot
echo "set xlabel 'triples'" >> tmp/graph_12a.plot
echo "set ylabel 'selectivity' " >> tmp/graph_12a.plot
echo "set zlabel 'triples/second'" >> tmp/graph_12a.plot
echo "set pm3d map" >> tmp/graph_12a.plot
#echo "set pm3d interpolate 0,0" >> tmp/graph_12a.plot
echo "set logscale x" >>tmp/graph_12a.plot
echo "splot 'tmp/part_12a.matrix2.csv' matrix nonuniform notitle" >>tmp/graph_12a.plot

echo "${gnuplot_terminal}" > tmp/graph_12b.plot
echo "set output 'graph_12b.${gnuplot_terminal_ending}'" >> tmp/graph_12b.plot
echo "set datafile separator ','" >> tmp/graph_12b.plot
echo "set notitle" >> tmp/graph_12b.plot
echo "set key inside right top" >> tmp/graph_12b.plot
echo "set xlabel 'triples'" >> tmp/graph_12b.plot
echo "set ylabel 'selectivity' " >> tmp/graph_12b.plot
echo "set zlabel 'triples/second'" >> tmp/graph_12b.plot
echo "set pm3d map" >> tmp/graph_12b.plot
echo "set pm3d interpolate 0,0" >> tmp/graph_12b.plot
echo "set logscale x" >>tmp/graph_12b.plot
#echo "g(x,z) = 3.120797872543335 + 0.15525072813034058 * x + -1.4453083276748657 * z + -0.22741258144378662 * x * z" >> tmp/graph_12b.plot
#echo "g(x,z) = 2.9560089111328125 + 0.22220611572265625 * x + -19.802474975585938 * z + 2.7935028076171875 * x * z + -0.00209808349609375 * x * x + 9.398078918457031 * z * z + -0.13790130615234375 * x * x * z + -1.200103759765625 * x * z * z + 0.0637054443359375 * x * x * z * z" >> tmp/graph_12b.plot
#echo "g(x,z) = 3.239339590072632 + 0.16974210739135742 * x + -3.380937874317169 * z + -0.47129690647125244 * x * z + 0.0 * x * x + 1.8585622310638428 * z * z + 0.0 * x * x * z + 0.33581554889678955 * x * z * z + -0.002288818359375 * x * x * z * z" >>tmp/graph_12b.plot
echo "g(x,z) = 3.5629481077194214 + 0.1655876636505127 * x + -10.080277919769287 * z + -0.3615468740463257 * x * z + 0.0 * x * x + 7.433122396469116 * z * z + + 0.2305924892425537 * x * z * z" >>tmp/graph_12b.plot

echo "log2(x) = log(x) / log(2)" >>tmp/graph_12b.plot
echo "mapX(x) = log2(x)" >>tmp/graph_12b.plot
echo "mapZ(z) = 1 / (1 + z)" >>tmp/graph_12b.plot
echo "round(x) = x - floor(x) < 0.5 ? floor(x) : ceil(x)" >>tmp/graph_12b.plot
echo "f(x,z) = g(mapX(x),mapZ(z)) >6?6:g(mapX(x),mapZ(z))<1?1:round(g(mapX(x),mapZ(z)))" >>tmp/graph_12b.plot
echo "splot [10 : 1000000] [0:40] f(x,y)" >>tmp/graph_12b.plot


for f in $(find tmp -name "graph_12*.plot")
do
	gnuplot $f
done
mv graph_12* tmp
