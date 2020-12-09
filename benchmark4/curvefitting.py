#!/usr/bin/python3
import numpy as np
import pandas as pd
from scipy.optimize import curve_fit

def func(X, a, b, c, d, e, f, g, h):
    x, y, z = X
    return a * x + b * y + c * z + d * x * y + e * x * z + f * y * z + g * x * y * z + h


col_list = ["results","joins","selectivity","partitions"]
df = pd.read_csv("data.csv", usecols=col_list)

# initial guesses for a,b,c:
popt, error = curve_fit(func, (df["results"],df["joins"],df["selectivity"]), df["partitions"])
a, b, c, d, e, f, g, h = popt
print('%f %f %f %f %f %f %f %f' % (a, b, c, d, e, f, g, h))
#print (popt)
#print (error)
