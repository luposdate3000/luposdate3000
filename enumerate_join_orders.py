#!/usr/bin/env python3


def generateJoinOrderHelper(depth: int, n: int) -> list[list[int]]:
    res = []
    if (depth == 1):
        available = range(0, n)
        for a in available:
            for b in available:
                if a < b:
                    res.append([a, b])
    else:
        child = generateJoinOrderHelper(depth - 1, n)
        for c in child:
            available = set(range(0, n)).union(
                set(range(int( - len(c) / 2), 0))) - set(c)
            for a in available:
                for b in available:
                    if (a < b):
                        res.append(c + [a, b])
    return res


def generateJoinOrderHelperSort(res:list[int],input: list[int], index: int) -> list[int]:
    av = input[index]
    a = 0
    if (av < 0):
        res.extend(generateJoinOrderHelperSort(res.copy(),input, (-1 - av) * 2))
        a = -len(res) / 2
    else:
        a = av
    bv = input[index + 1]
    b = 0
    if (bv < 0):
        res.extend(generateJoinOrderHelperSort(res.copy(),input, (-1 - bv) * 2))
        b = -len(res) / 2
    else:
        b = bv
    res.append(a)
    res.append(b)
    return res


def generateJoinOrder(n: int):
    if n < 2:
        return {}
    orders = generateJoinOrderHelper(n - 1, n)
    res = {}
    res1 = {}
    for o in orders:
        oCpy = o.copy()
        intermediateCtr = int(len(o) / 2)
        elements = []
        for it in range(0, intermediateCtr + n):
            elements.append({it - intermediateCtr})
        for i in range(0, intermediateCtr):
            ai = i * 2
            bi = i * 2 + 1
            a = o[ai]
            b = o[bi]
            ea = elements[a + intermediateCtr]
            eb = elements[b + intermediateCtr]
            elements[intermediateCtr - i - 1] = ea.union(eb)
            if min(eb) < min(ea):
                oCpy[ai] = b
                oCpy[bi] = a
        oSorted = generateJoinOrderHelperSort([],oCpy, len(oCpy) - 2)
        res[tuple(o)] = res1.setdefault(tuple(oSorted), len(res1))
    return res

joinOrdersMap=[generateJoinOrder(triples) for triples in range(0,6)]
