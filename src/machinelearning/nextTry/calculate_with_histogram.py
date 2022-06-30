#!/usr/bin/env python
import traceback
import os
import sys
import math
import gym
import time
import csv

maxbuckets = 100
histogramdata = {}  #(key,col) -> [(left,right,count)]
histogramtotal = 0

def is_nan(x):
 return x!=x
def init_histogram(filename):
    global histogramdata
    global histogramtotal
    with open(filename, "r") as f:
        reader = csv.reader(f, delimiter=',')
        for rowr in reader:
            row = [int(x) for x in rowr]
            histograms = []
            for i in range(1, int(len(row) / 3)):
                idx = i * 3
                left = row[idx]
                right = row[idx + 1]
                count = row[idx + 2]
                histogramtotal += count / 2
                if count > 0:
                    histograms.append((left, right, (count / (right - left))))  # density / width
            histogramdata[(row[0], row[1])] = minify_histogram(histograms)


def histogram_for_triple_pattern(triple):
    return {triple[0]: histogramdata[(triple[1], 0)], triple[2]: histogramdata[(triple[1], 1)]}


def map_join_inputs_to_histograms(join):
    inputs = []
    for i in range(int(len(join) / 3)):
        idx = i * 3
        if join[idx + 1] < 0:
            inputs.append({join[idx]: histogramtotal, join[idx + 1]: histogramtotal, join[idx + 2]: histogramtotal})
        else:
            inputs.append(histogram_for_triple_pattern((join[idx], join[idx + 1], join[idx + 2])))
    return inputs


def single_column_join(histogramA, histogramB):
    result = []
    idxA = 0
    idxB = 0
    left = 0
    while True:
        while histogramA[idxA][1] < histogramB[idxB][0] or histogramA[idxA][1] <= left:
            idxA += 1
            if idxA >= len(histogramA):
                return result
        while histogramB[idxB][1] < histogramA[idxA][0] or histogramB[idxB][1] <= left:
            idxB += 1
            if idxB >= len(histogramB):
                return result
        left = max(histogramA[idxA][0], histogramB[idxB][0], left)
        right = max(left + 1, min(histogramA[idxA][1], histogramB[idxB][1]))
        result.append((left, right, histogramA[idxA][2] * histogramB[idxB][2]))
        left = right
    return result


def myaverage(histogram):
    if len(histogram)==0:
     return 0
    res = 0
    for h in histogram:
        res += h[2]*(h[1]-h[0])
    return res / len(histogram)


def perform_join(histogramsA, histogramsB):
   joinkey = None
   for key in histogramsA:
        if key in histogramsB:
            joinkey = key
            break
   result = {}
   if joinkey is None:
    va = myaverage(list(histogramsA.values())[0])
    vb = myaverage(list(histogramsB.values())[0])
    for k, v in histogramsA.items():
           result[k] = minify_histogram([tuple([x[0], x[1], x[2] *vb]) for x in v])
    for k, v in histogramsB.items():
           result[k] = minify_histogram([tuple([x[0], x[1], x[2] *va ]) for x in v])
   else:
    result[joinkey] = minify_histogram(single_column_join(histogramsA[joinkey], histogramsB[joinkey]))
    va = myaverage(histogramsA[joinkey])
    vb = myaverage(histogramsB[joinkey])
    vn = myaverage(result[joinkey])
    a=vn/va
    b=vn/vb
    if is_nan(a):
     a=1
    if is_nan(b):
     b=1
    for k, v in histogramsA.items():
       if k != joinkey:
           result[k] = minify_histogram([tuple([x[0], x[1], x[2] *a]) for x in v])
    for k, v in histogramsB.items():
       if k != joinkey:
           result[k] = minify_histogram([tuple([x[0], x[1], x[2] *b]) for x in v])
   return result


def estimate_intermediates(join, joinorder):
    inputs = map_join_inputs_to_histograms(join)
    intermediates = []
    for i in range(int(len(joinorder) / 2)):
        idx = i * 2
        a = {}
        b = {}
        if joinorder[idx] < 0:
            a = intermediates[-1 - joinorder[idx]]
        else:
            a = inputs[joinorder[idx]]
        if joinorder[idx + 1] < 0:
            b = intermediates[-1 - joinorder[idx + 1]]
        else:
            b = inputs[joinorder[idx + 1]]
        intermediates.append(perform_join(a, b))

    result = 0
    for i in range(len(intermediates)):
        res = []
        for v in intermediates[i].values():
            r = 0
            for h in v:
                r += h[2] * (h[1] - h[0])
            res.append(r)
        result += max(res)
    print("estimate",result)
    return result


def minify_histogram(histogram):
    if len(histogram) <= maxbuckets:
        return histogram
    hist = []
    for i in range(len(histogram)):
        if len(hist) > 0 and hist[-1][1] < histogram[i][0]:
            hist.append((hist[-1][1], histogram[i][0], 0))
        hist.append(histogram[i])
    while len(hist) > maxbuckets:
        factor = abs(hist[0][2] - hist[1][2])
        for i in range(len(hist) - 1):
            factor = min(factor, abs(hist[i][2] - hist[i + 1][2]))
        hist2 = []
        i = 1
        left = hist[0][0]
        right = hist[0][1]
        value = hist[0][2]
        values = [value]
        while i < len(hist):
            if abs(hist[i][2] - value) > factor:
                hist2.append((left, right, sum(values) / len(values)))
                left = hist[i][0]
                value = hist[i][2]
                values = [value]
            else:
                values.append(hist[i][2])
            right = hist[i][1]
            i += 1
        hist2.append((left, right, sum(values) / len(values)))
        hist = hist2
    return hist


#init_histogram(sys.argv[1] + ".histograms")
#estimate_intermediates([-1, 1, -63, -1, 5, -64, -1, 6, -7, -1, 22, -10, -1, 33, -2, -2, 1, -65, -2, 5, -66, -2, 6, -14, -2, 22, -22, -2, 33, -3, -3, 1, -67, -3, 5, -68, -3, 6, -16, -3, 22, -15, -4, 5, -69, -4, 10, -26, -4, 11, -29, -5, 5, -70, -5, 10, -30, -5, 11, -44, -6, 5, -71, -6, 10, -41, -6, 11, -49, -7, 5, -72, -7, 10, -8, -8, 5, -73, -8, 10, -9, -9, 5, -74, -9, 10, -12, -10, 5, -75, -10, 10, -11, -11, 5, -76, -11, 10, -13, -12, 5, -77, -12, 10, -21, -13, 5, -78, -13, 10, -6, -14, 1, -79, -14, 5, -80, -14, 10, -20, -15, 5, -81, -15, 10, -17, -16, 5, -82, -16, 10, -18, -17, 5, -83, -17, 10, -19, -18, 5, -84, -18, 10, -23, -19, 5, -85, -19, 10, -4, -20, 5, -86, -20, 10, -28, -21, 5, -87, -21, 10, -25, -22, 5, -88, -22, 10, -24, -23, 5, -89, -23, 10, -32, -24, 5, -90, -24, 10, -27, -25, 5, -91, -25, 10, -35, -26, 5, -92, -26, 10, -38, -27, 5, -93, -27, 10, -5, -28, 5, -94, -28, 10, -36, -29, 5, -95, -29, 10, -33, -30, 5, -96, -30, 10, -31, -31, 5, -97, -31, 10, -55, -32, 5, -98, -32, 10, -34, -33, 5, -99, -33, 10, -37, -34, 5, -100, -34, 10, -40, -35, 5, -101, -35, 10, -56, -36, 5, -102, -36, 10, -39, -37, 5, -103, -37, 10, -45, -38, 5, -104, -38, 10, -57, -39, 5, -105, -39, 10, -42, -40, 5, -106, -40, 10, -47, -41, 5, -107, -41, 10, -43, -42, 5, -108, -42, 10, -52, -43, 5, -109, -43, 10, -58, -44, 5, -110, -44, 10, -46, -45, 5, -111, -45, 10, -48, -46, 5, -112, -46, 10, -54, -47, 5, -113, -47, 10, -60, -48, 5, -114, -48, 10, -61, -49, 5, -115, -49, 10, -50, -50, 5, -116, -50, 10, -51, -51, 5, -117, -51, 10, -62, -52, 5, -118, -52, 10, -53, -53, 5, -119, -53, 10, -59, -54, 5, -120, -54, 10, -121, -55, 5, -122, -56, 5, -123, -57, 5, -124, -58, 5, -125, -59, 5, -126, -60, 5, -127, -61, 5, -128, -62, 5, -129], [45, 71, -1, 66, -2, 104, -3, 46, 47, 120, -4, -5, 72, 73, -7, 70, -8, 50, 49, 100, -9, -10, -6, -11, 40, 124, -13, 41, 28, 29, 76, 88, -16, 39, -17, 30, -15, -18, 59, 75, -20, 60, 31, 119, -22, 68, -21, -23, -24, 105, -19, -25, 32, 34, -27, 33, -26, -28, 5, 118, 108, 110, -30, -31, -32, 113, 6, 83, -33, -34, 15, 121, -36, 0, -37, 97, 95, 96, 1, 63, -40, 61, -39, -41, -38, -42, 2, 86, -43, -44, 3, 80, -45, -46, -47, 4, -35, -48, 7, 54, -49, -50, 8, 48, -51, -52, 79, 107, -54, 77, 82, 87, -55, -56, 81, 85, -58, 10, -57, -59, -60, 9, -53, -61, 11, 14, -62, -63, -64, 12, 67, 69, 103, 123, -67, 55, -68, 84, -66, -69, -70, 13, -65, -71, 64, 91, 65, 125, -73, -74, 17, 112, -75, -76, 16, 122, -77, -78, 18, 115, -79, -80, -72, -81, 90, 106, 89, 109, -83, -84, -85, 98, -86, 99, 19, 101, -88, 78, 20, 57, -89, -90, -87, -91, -92, 127, -93, 126, 22, 117, -95, 21, -94, -96, 38, 111, 23, 62, -98, -99, 24, 116, -100, -101, -97, -102, -82, -103, 26, 74, -105, 25, 27, 56, -107, 53, -106, -108, -104, -109, -29, -110, 35, 36, -112, 37, 94, 102, -114, 92, -113, -115, -111, -116, -14, -117, -118, 42, 44, 93, -120, 58, -121, 43, -119, -122, -12, -123, 51, 114, -125, 52, -124, -126])
