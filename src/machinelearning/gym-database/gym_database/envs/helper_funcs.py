"""Helper functions for the database Gym environment"""
import os
import math
import numpy as np
from typing import List, Tuple, Dict
import pickle


def is_valid_action(left, right, observation_matrix):
    return observation_matrix[left][right][0] != 0 and observation_matrix[right][left][0] != 0


def is_done(observation_matrix):
    s = len(observation_matrix)
    c = 0
    for i in range(s):
        if observation_matrix[i][0][0] == 0:
            c = c + 1
    return s - c == 1 #  finished when there is exactly one non zero row in the matrix

def calculate_possible_actions(matrix_size):
    action_list = []
    for i in range(matrix_size):
        for j in range(i + 1, matrix_size):
            action_list.append((i, j))
    return action_list


def reset_observation(sorted_query, observation_matrix):
    for x in range(len(sorted_query)):
        for y in range(len(sorted_query)):
            if x != y:
                observation_matrix[x][y] = [-1, -1, -1] # set the top left square to all -1 depending on the current number of triples. Assume, everything else is all zero
    for index, triples in enumerate(sorted_query):
        observation_matrix[index, index] = triples # fill in the triples at the diagonal of the matrix
    return observation_matrix


def update_observation(left, right, observation_matrix):
    for i, v in enumerate(observation_matrix[right, :]):
        if v[0] != 0:
            if (observation_matrix[left, i, 0] == -1 and observation_matrix[left, i, 1] == -1 and observation_matrix[left, i, 2] == -1):
                observation_matrix[left, i] = v # copy the right row into the left row
            else:
                None
        observation_matrix[right, i] = 0 # set right row to zero, because it is now part of left row


def load_query(query_string: str) -> List[List[Tuple[int, int, int]]]:
    """Function that loads a new query.

    Function gets a serialized query in form of a string. It builds a query object and
    sorts the triples on their predicate (2nd ID).

    Parameters
    ----------
    query_string: str
        A serialized query object in form of a string.

    Returns
    -------
    List[List[Tuple[int, int, int]]
        The query is a list of Triples that represent the triples and its join candidates.
    """

    query = []
    for i in query_string.split(";"):
        tmp = []
        for x in map(int, i.split(",")):
            if (x < 0):
                tmp.append(x - 1)
            else:
                tmp.append(x)
        query.append(tuple(tmp))
    query.sort(key=lambda x: x[1])
    print("load_query", query_string, query)
    return query


def join_order_to_string(join_order: Dict) -> str:
    """Function that serializes the join order object to a string.

    Parameters
    ----------
    join_order: Dict
        The join order Dict.

    Returns
    -------
    str
        The serialized Dict as a string.
    """

    temp = [x for x in join_order.items()]
    out = ""
    for i in temp:
        out = out + str(i[0]) + ":" + str(i[1][0]) + "," + str(i[1][1]) + ";"
    out = out[:-1]  # remove last ";" for easy split operation
    return out


def remember_join_order(left, right, join_order, join_order_h):
    index = -len(join_order) / 2 - 1
    tmp = [join_order_h[left], join_order_h[right]]
    tmp.sort()
    join_order.extend(tmp) # join_order is a list which contains left and right alternating. The join order must be executed from lowest to highest index
    join_order_h[left] = index # the join_order_h is used to map the row number to possible intermediate results easy and fast
    join_order_h[right] = index


def calculate_reward(benched_query, join_order):
    choosen_id = joinOrderToID(join_order)
    execution_times = [float(query[2]) for query in benched_query]
    time_choosen = execution_times[choosen_id]
    time_min = min(execution_times)
    time_max = max(execution_times)
    reward = 100 - abs((np.log(time_choosen) - np.log(time_min)) / (np.log(time_max) - np.log(time_min))) * 100
    return reward


def generateJoinOrderHelper(depth, n):
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
            available = set(range(0, n)).union(set(range(int(-len(c) / 2), 0))) - set(c)
            for a in available:
                for b in available:
                    if (a < b):
                        res.append(c + [a, b])
    return res


def generateJoinOrderHelperSort(res, input, index):
    av = input[index]
    a = 0
    if (av < 0):
        res.extend(generateJoinOrderHelperSort(res.copy(), input, (-1 - av) * 2))
        a = -len(res) / 2
    else:
        a = av
    bv = input[index + 1]
    b = 0
    if (bv < 0):
        res.extend(generateJoinOrderHelperSort(res.copy(), input, (-1 - bv) * 2))
        b = -len(res) / 2
    else:
        b = bv
    res.append(a)
    res.append(b)
    return res


def generateJoinOrder(n):
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
        oSorted = generateJoinOrderHelperSort([], oCpy, len(oCpy) - 2)
        res[tuple(o)] = res1.setdefault(tuple(oSorted), len(res1))
    return res


tripleCount = int(os.environ["tripleCount"])
joinOrderCache = generateJoinOrder(tripleCount)


def joinOrderToID(joinOrder):
    return joinOrderCache[tuple(joinOrder)]
