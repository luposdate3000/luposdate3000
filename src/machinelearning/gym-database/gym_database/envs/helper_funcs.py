"""Helper functions for the database Gym environment"""
import os
import math
import numpy as np
from typing import List, Tuple, Dict
import pickle

def create_action_list(matrix_size):
    """Function that creates a list of possible actions.

    An action is a join of two rows. The number of  ways to join a row is restricted.
    The row with the higher index is joined into the lower row. This reduces the action space.
    The size of the action space is the Gauss sum of the size of the matrix in one dimension.

    Parameters
    ----------
    matrix_size: int
        The size of the observation matrix in the first or second dimension. As it is a square
        matrix the first and second dimensions are the same.

    Returns
    -------
    List[Tuple[int, int]]
        List of possible actions.
        (0,1),(0,2),...,(0,matrix_size),(1,2),(1,3),...,(2,3),(2,4)...,(matrix_size-2,n_triples-1)
    """
    action_list = []
    for i in range(matrix_size):
        for j in range(i+1,matrix_size):
            action_list.append((i, j))
    return action_list

def fill_matrix(sorted_query, observation_matrix) :
    """Function to initially fill the observation matrix with triples and its join candidates.

    Takes triples from query and sets the diagonal values of the corresponding entry in the
    observation matrix to the values of the corresponding triple. The possible join candidates
    are marked with a [-1, -1, -1]. Join variables are negative integers.


    Parameters
    ----------
    sorted_query : List[List[Tuple[int, int, int]]
        list of lists of triples and join candidates in query:
        [triple_a, triple_b, ...]. triple_a = [triple, join_candidate0, join_candidate1, ...]
    observation_matrix : np.ndarray
        empty observation matrix with all entries set to zero

    Returns
    -------
    np.ndarray
        initial observation matrix with triples and join candidates
    """

    for x in range (len(sorted_query)):
        for y in range (len(sorted_query)):
            if x !=y :
               observation_matrix[x][y]=[-1,-1,-1]
    for index, triples in enumerate(sorted_query): # triples is a list with the triple and its join candidates
        observation_matrix[index, index] = triples
    print("fill_matrix",observation_matrix)
    return observation_matrix

def perform_join(index_a, index_b, observation_matrix):
    """ Joins triple a and triple b.

    To join a and b, the corresponding entries of a and b are merged.
    A filled cell with positive integers represents the corresponding
    triple and a -1 for its join candidates.
    Therefore the triple b is placed in the row of a at the column of b.
    And all the join candidates and other triples are copied to row a.
    And the triples and the join candidates in the row of b are deleted.

    Parameters
    ----------
    index_a : int
        index of triple_a in obervation_matrix
    index_b : int
        index of triple_b in observation_matrix
    observation_matrix : np.ndarray
        observation_matrix

    Returns
    -------
    None
    """

    # invariant joining, join into lower numbered row
    temp_one = min(index_a, index_b)
    temp_two = max(index_a, index_b)
    index_a = temp_one
    index_b = temp_two
    for i, v in enumerate(observation_matrix[index_b, :]):
        if v[0] != 0:
            if observation_matrix[index_a, i, 0] == -1 and \
                    observation_matrix[index_a, i, 1] == -1 and \
                    observation_matrix[index_a, i, 2] == -1 or \
                    observation_matrix[index_a, i, 0] == 0 and \
                    observation_matrix[index_a, i, 1] == 0 and \
                    observation_matrix[index_a, i, 2] == 0:
                observation_matrix[index_a, i] = v  # overwrite value with the value from b row
            else:
                None    # dont overwrite triples
        observation_matrix[index_b, i] = 0  # set entries in b row to 0

def is_empty(index, observation_matrix):
    """Function to check if a row in the observation matrix is empty

    Parameters
    ----------
    index: int
        Index of row in observation matrix to check if it is empty.
    observation_matrix: np.ndarray
        observation matrix

    Returns
    -------
    bool
        If the row at index in observation matrix is empty.
    """
    for triple in observation_matrix[index, :]:
        if triple[0] != 0:
            return False
    else:
        return True



def check_if_done(observation_matrix: np.ndarray) -> bool:
    """Function to check if the episode is finished.

    Takes the observation matrix and checks if all triples that can be joined
    are in one row. This means that all triples have been joined and the episode is finished.

    Parameters
    ----------
    observation_matrix : np.ndarray
        observation matrix

    Returns
    -------
    bool
        if the episode is finished
    """

    # search if there are joins possible, if yes -> episode is not done, if not -> episode is done
    for row in observation_matrix:
        for triple in row:
            if triple[0] == -1 and triple[1] == -1 and triple[2] == -1: # if there are possible joins left
                return False
    else:
        return True


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
        tmp=[]
        for x in map(int, i.split(",")):
            if(x<0):
                tmp.append(x-1)
            else:
                tmp.append(x)
        query.append(tuple(tmp))
    query.sort(key=lambda x: x[1])
    print("load_query",query_string,query)
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
    out = out[:-1] # remove last ";" for easy split operation
    return out


def update_join_order(left, right, join_order, join_order_h):
    index = -len(join_order)/2-1
    tmp = [join_order_h[left],join_order_h[right]]
    tmp.sort()
    join_order.extend(tmp)
    join_order_h[left] = index
    join_order_h[right] = index

def calculate_reward( benched_query, join_order):
    choosen_id = joinOrderToID(join_order)
    execution_times = [float(query[2]) for query in benched_query]
    time_choosen=execution_times[choosen_id]
    time_min=min(execution_times)
    time_max=max(execution_times)
    reward = 100 - abs((np.log(time_choosen) - np.log(time_min))/(np.log(time_max)-np.log(time_min)))*100
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
            available = set(range(0, n)).union(
                set(range(int( - len(c) / 2), 0))) - set(c)
            for a in available:
                for b in available:
                    if (a < b):
                        res.append(c + [a, b])
    return res

def generateJoinOrderHelperSort(res,input, index):
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
        oSorted = generateJoinOrderHelperSort([],oCpy, len(oCpy) - 2)
        res[tuple(o)] = res1.setdefault(tuple(oSorted), len(res1))
    return res

tripleCount=int(os.environ["tripleCount"])
joinOrderCache=generateJoinOrder(tripleCount)

def joinOrderToID(joinOrder):
    return joinOrderCache[tuple(joinOrder)]
