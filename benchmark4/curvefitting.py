#!/usr/bin/python3
import numpy as np
import pandas as pd
from scipy.optimize import curve_fit

#a=0.001277 b=0.067444 c=1.706868 d=-0.000042 e=-0.000113 f=-0.016581 g=1.000000 h=1.286443
def func(X, a, b, c, d, e, f, g, h):
    x, y, z = X
    return \
	 (a) * x +\
	 (b) * y +\
	 (c) * z +\
	 (d) * x * x +\
	 (e) * y * y +\
	 (f) * z * z +\
	 (h)
#	 (g) * x * y * z +


col_list = ["results","joins","selectivity","partitions"]
df = pd.read_csv("data.csv", usecols=col_list)

# initial guesses for a,b,c:
popt, error = curve_fit(func, (df["results"],df["joins"],df["selectivity"]), df["partitions"])
a, b, c, d, e, f, g, h = popt
print('a=%f b=%f c=%f d=%f e=%f f=%f g=%f h=%f' % (a, b, c, d, e, f, g, h))
#print (popt)
#print (error)
