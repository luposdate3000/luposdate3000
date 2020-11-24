#!/usr/bin/python3
import matplotlib.pyplot as plt
import numpy as np
from mpl_toolkits.mplot3d import Axes3D

fig = plt.figure()
ax1 = fig.add_subplot(111,projection='3d')
ax1.set_xlabel('selectivity')
ax1.set_ylabel('triples (e^x)')
ax1.set_zlabel('triples/second')

def fx(x):
    return x
fx1=np.vectorize(fx)
def fy(y):
    return np.log2(y)
fy1=np.vectorize(fy)
def fz(z):
    return 1/z
fz1=np.vectorize(fz)

x1, y1, z1 = np.loadtxt('tmp/part_d1.csv', delimiter=',', unpack=True, usecols = (1,14,13))
xa1=fx1(x1)
ya1=fy1(y1)
za1=fz1(z1)
ax1.plot_trisurf(xa1,ya1,za1,alpha=0.3)
x6, y6, z6 = np.loadtxt('tmp/part_d6.csv', delimiter=',', unpack=True, usecols = (1,14,13))
xa6=fx1(x6)
ya6=fy1(y6)
za6=fz1(z6)
ax1.plot_trisurf(xa6,ya6,za6,alpha=0.3)

#plt.show()
plt.savefig('plot.png')

import vtk
import mayavi
from mayavi import mlab
mlab.clf()
mlab.points3d(xa1, ya1, za1)
mlab.savefig(filename='plot2.png')
