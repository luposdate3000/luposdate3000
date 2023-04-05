#!/usr/bin/env -S python3 -OO -u
import os

mapping = {}
for file in os.listdir("_tmpdata/model/"):
    if file.endswith(".model"):
        x = int(file.split("_")[-1][:-len(".model")])
        basename = file[:-len(".model") - len(str(x))]
        try:
            ll = mapping[basename]
            if ll < x:
                mapping[basename] = x
        except:
            mapping[basename] = x


def is_power_of_two(n):
    return (n != 0) and (n & (n - 1) == 0)


def is_multiple_of_131072(n):
    return n % 131072 == 0


for file in os.listdir("_tmpdata/model/"):
    if file.endswith(".model"):
        x = int(file.split("_")[-1][:-len(".model")])
        basename = file[:-len(".model") - len(str(x))]
        if (not is_power_of_two(x)) and (not is_multiple_of_131072(x)) and (basename + str(mapping[basename]) + ".model" != file):
            os.remove("_tmpdata/model//" + file)
