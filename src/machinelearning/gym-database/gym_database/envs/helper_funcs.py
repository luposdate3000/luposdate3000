"""Helper functions for the database Gym environment"""

import math
import numpy as np
from typing import List, Tuple, Dict
import pickle

def create_action_list(matrix_size: int) -> List[Tuple[int, int]]:
    #print("Create action list")
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
    #print(matrix_size)
    action_list = []
    for i in range(matrix_size):
        for j in range(i+1,matrix_size):
            action_list.append((i, j))
    #print(action_list)
    return action_list

def fill_matrix(sorted_query: List[List[Tuple[int, int, int]]], observation_matrix: np.ndarray) -> np.ndarray:
    #print("Fill matrix")
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

    # Create dictionary to map triple to its index in matrix and vice versa.
    #print(sorted_query)
    index_dict = _create_matrix_index_triple_dict(sorted_query)
    #print(index_dict)
    # Fill matrix with triples from query.
    for index, triples in enumerate(sorted_query): # triples is a list with the triple and its join candidates
        observation_matrix[index, index] = triples[0]
        observation_matrix[index, index, 0] -= 1 # fix join variable offset
        observation_matrix[index, index, 2] -= 1
        for join_candidate in triples[1:]:  # fill matrix with join candidates
            observation_matrix[index_dict[join_candidate], index] = -1 # possible joins marked with -1
    #print(observation_matrix)
    return observation_matrix

def perform_join(index_a: int, index_b: int, observation_matrix: np.ndarray):
    #print("Perform join")
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
    #print(observation_matrix)
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
    #print(observation_matrix)
    

def is_empty(index: int, observation_matrix: np.ndarray) -> bool:
    #print("is_empty")
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


def _create_matrix_index_triple_dict(sorted_query: List[List[Tuple[int, int, int]]]) -> Dict:
    #print("create_matrix_index_triple_dict")
    """Function that maps every triple to an index in the observation matrix and vice versa.

    Parameters
    ----------
    sorted_query: List[List[Tuple[int, int, int]]]
        The sorted query where the occuring triple are in.

    Returns
    -------
    Dict
        The Dict containing a mapping between an index in the observation matrix and a triple
        and vice versa.
    """
    new_dict = {}
    for index, list_of_triples in enumerate(sorted_query):
        new_dict[index] = list_of_triples[0]
        new_dict[list_of_triples[0]] = index
    return new_dict


def check_if_done(observation_matrix: np.ndarray) -> bool:
    #print("check if done")
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
    #print("load_query")
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

    # Build query list from string
    query = _query_from_string(query_string)
    #print(query)
    # Order triples in query by predicate (2nd ID)
    _order_query_by_predicate(query)
    return query


def _query_from_string(query_string: str) -> List[List[Tuple[int, int, int]]]:
    #print("query_from_string")
    """Function that turns a string representation of a query into a query object.

    Parameters
    ----------
    query_string: str
        Serialized string representation of a query object.

    Returns
    -------
    List[List[Tuple[int, int, int]]
        query object
    """

    # Build query list from string
    #print(query_string)
    query = []
    #join_variables = query_string.split(";")[:-1]
    join_variables = query_string.split(";")
    #print(join_variables)
    for i in join_variables:
        query.append([tuple(map(int, i.split(",")))])
    # Add join candidates to each triple
    for i in query:
        join_variables = []
        # Find all join variables of current triple i
        for j in i[0]:
            if j < 0:
                join_variables.append(j)
        # For all join variables in current triple find other triples with same join variable
        for j in query:
            if i != j: # If its not the same triple
                for k in join_variables:
                    if k in j[0]:  # If triples have same join variables
                        i.append(j[0])  # Append join candidate to triple i
                        break
    #print(query)
    return query


def _order_query_by_predicate(unsorted_query: List[List[Tuple[int, int, int]]]):
    #print("order_query_by_predicate")
    """Method that orders a query by its predicate (2nd ID) in ascending order.

    Parameters
    ----------
    unsorted_query: List[List[Tuple[int, int, int]]]
        An unsorted query, a list of Triples and its join candidates.
    """
    
    unsorted_query.sort(key=lambda x: x[0][1])
    #print(unsorted_query)


def join_order_to_string(join_order: Dict) -> str:
    #print("join_order_to_string")
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


def update_join_order(left: int, right: int, join_order: Dict, join_order_h: Dict):#need to change this
    #print("update_join_order")
    """Method to update the current join order.

    Parameters
    ---------
    left: int
        Row in observation matrix to join.
    right: int
        Row in observation matrix to join.
    join_order: Dict
        The join order is saved as a graph and encoded in a Dict. It gets updated by this method.
        For every join that happened there is one entry of its join partners at join_order[i],
        where i is the i'th join. i is negative to avoid collisions.
    join_order_h: Dict
        This is a Dict for assistant values to build join_order. It attributes every row number to
        its content as a pointer (index) to that join.
    """

    # invariant joining, join into lower numbered row, important for this to work
    temp_one = min(left, right)
    temp_two = max(left, right)
    left = temp_one
    right = temp_two

    # index has negative values, starting from -1, to avoid collisions with IDs
    index = -(len(join_order)+1)

    # if right and left have been joined before
    if left in join_order_h and right in join_order_h:
        # save the join of left and right in join_order as {index: [pointer to row of left, pointer to row of right]}
        join_order[index] = [join_order_h[left], join_order_h[right]]
        # update pointer to row of left
        join_order_h[left] = index
        join_order_h[right] = index
        #print("If:",end="")
        #print(join_order_h,join_order)
    # if left has been joined before, but not right
    elif left in join_order_h:
        # save the join of left and right in join_order as {index: [pointer to row of left, right]}
        join_order[index] = [join_order_h[left], right]
        # update pointer to row of left
        join_order_h[left] = index
        join_order_h[right] = index
        #print("elif1:",end="")
        #print(join_order_h,join_order)
    # if right has been joined before, but not left
    elif right in join_order_h:
        # save the join of left and right in join_order as {index: [left, pointer to row of right]}
        join_order[index] = [left, join_order_h[right]]
        # update pointer to row of right
        join_order_h[right] = index 
        join_order_h[left] = index
        #print("elif2:",end="")
        #print(join_order_h,join_order)                   # need to change this block
    # if left and right have not been involved in a join so far
    else:
        # save the join of left and right in join_order as {index: [left, right]}
        join_order[index] = [left, right]
        # save the pointer to that join as {left: index}
        join_order_h[left] = index
        join_order_h[right] = index
        #print("else:",end="")
        #print(join_order_h,join_order)
    #print(join_order)


def calculate_reward(max_exec_t, min_exec_t, benched_query, join_order,check_orderings):
    #print("calculate_reward")
    """Function that calculates the reward.

    Parameters
    ----------
    max_exec_t
        Maximum execution time of benched queries, used to normalize.
    min_exec_t
        Minimum execution time of benched queries, used to normalize.
    benched_query
        Reference query that has been benchmarked.
    join_order
        Join order of the ml optimizer.

    Returns
    -------
    float
        Reward.
    """
    #print(join_order)
    # get number of join order
    join_order_n = _join_order_to_number(join_order,check_orderings)
    ##print("Join order n value is:"+str(join_order_n))
    #print(benched_query)
    # get execution time of this join order
    '''
    execution_times = [float(benched_query[0][2]), float(benched_query[1][2]),
                       float(benched_query[2][2]),float(benched_query[3][2]), float(benched_query[4][2]),
                       float(benched_query[5][2]),float(benched_query[6][2]), float(benched_query[7][2]),
                       float(benched_query[8][2])]
    '''
    ##print(benched_query)
    ##print(join_order_n)
    '''
    execution_times = [float(benched_query[0][2]), float(benched_query[1][2]),
                       float(benched_query[2][2]),float(benched_query[3][2]), float(benched_query[4][2]),
                       float(benched_query[5][2]),float(benched_query[6][2]), float(benched_query[7][2]),
                       float(benched_query[8][2]),float(benched_query[9][2]), float(benched_query[10][2]),
                       float(benched_query[11][2]),float(benched_query[12][2]),float(benched_query[13][2]),
                       float(benched_query[14][2])]
    '''
    execution_times = [float(query[2]) for query in benched_query]
    # calculate reward on base of execution time relative to maximum and minimum
    # execution times of all benched queries
    #reward = -(math.sqrt(abs(execution_times[join_order_n]-max_exec_t))/math.sqrt(max_exec_t-min_exec_t)*10)
    #reward = 100 - abs((min(execution_times)-execution_times[join_order_n])/(max(execution_times)-min(execution_times)))*100
    reward = 100 - abs((np.log(execution_times[join_order_n]) - np.log(min(execution_times)))/(np.log(max(execution_times))-np.log(min(execution_times))))*100
    ##print(reward)
    return reward


def _join_order_to_number(join_order,check_orderings):
    #print("join_order_to_number")
    """Function that takes the join order and returns the ID of that join order.

    Parameters
    ----------
    join_order
        The calculated join order of a query.

    Returns
    -------
    int
        ID of the join order.
    """

    ##print(join_order)
    '''
    if join_order[-1] == [0, 1]:
        return 0
    elif join_order[-1] == [0, 2]:
        return 1
    elif join_order[-1] == [1, 2]:
        return 2
    # take a 4 value list
    0 {-1: [0, 1], -2: [-1, 2], -3: [-2, 3]}

    1 {-1: [0, 1], -2: [-1, 3], -3: [-2, 2]}

    2 {-1: [0, 1], -2: [2, 3], -3: [-1, -2]}

    3 {-1: [0, 2], -2: [-1, 1], -3: [-2, 3]}

    4 {-1: [0, 2], -2: [-1, 3], -3: [-2, 1]}

    5 {-1: [0, 2], -2: [1, 3], -3: [-1, -2]}

    6 {-1: [0, 3], -2: [-1, 1], -3: [-2, 2]}

    7 {-1: [0, 3], -2: [-1, 2], -3: [-2, 1]}

    8 {-1: [0, 3], -2: [1, 2], -3: [-1, -2]}

    9 {-1: [1, 2], -2: [-1, 3], -3: [0, -2]}

    10 {-1: [1, 2], -2: [0, -1], -3: [0, 3]}

    11 {-1: [1, 3], -2: [-1, 2], -3: [0, -2]}

    12 {-1: [1, 3], -2: [0, -1], -3: [0, 2]}

    13 {-1: [2, 3], -2: [0, -1], -3: [0, 1]}

    14 {-1: [2, 3], -2: [1, -1], -3: [0, 1]}
    

    #print(join_order)
    if join_order[-1]==[0,1] and join_order[-2]==[-1,2] and join_order[-3]==[-2,3]:
        return 0
    elif join_order[-1]==[0,1] and join_order[-2]==[-1,3] and join_order[-3]==[-2,2]:
        return 1
    elif (join_order[-1]==[0,1] and join_order[-2]==[2,3] and join_order[-3]==[-1,-2]) or (join_order[-1]==[2,3] and join_order[-2]==[0,1] and join_order[-3]==[-2,-1]):
        return 2
    elif join_order[-1]==[0,2] and join_order[-2]==[-1,1] and join_order[-3]==[-2,3]:
        return 3
    elif join_order[-1]==[0,2] and join_order[-2]==[-1,3] and join_order[-3]==[-2,1]:
        return 4
    elif (join_order[-1]==[0,2] and join_order[-2]==[1,3] and join_order[-3]==[-1,-2]) or (join_order[-1]==[1,3] and join_order[-2]==[0,2] and join_order[-3]==[-2,-1]):
        return 5
    elif join_order[-1]==[0,3] and join_order[-2]==[-1,1] and join_order[-3]==[-2,2]:
        return 6
    elif join_order[-1]==[0,3] and join_order[-2]==[-1,2] and join_order[-3]==[-2,1]:
        return 7
    elif (join_order[-1]==[0,3] and join_order[-2]==[1,2] and join_order[-3]==[-1,-2]) or (join_order[-1]==[1,2] and join_order[-2]==[0,3] and join_order[-3]==[-2,-1]):
        return 8
    elif join_order[-1]==[1,2] and join_order[-2]==[-1,3] and join_order[-3]==[0,-2]:
        return 9
    elif join_order[-1]==[1,2] and join_order[-2]==[0,-1] and join_order[-3]==[-2,3]:
        return 10
    elif join_order[-1]==[1,3] and join_order[-2]==[-1,2] and join_order[-3]==[0,-2]:
        return 11
    elif join_order[-1]==[1,3] and join_order[-2]==[0,-1] and join_order[-3]==[-2,2]:
        return 12
    elif join_order[-1]==[2,3] and join_order[-2]==[0,-1] and join_order[-3]==[-2,1]:
        return 13
    elif join_order[-1]==[2,3] and join_order[-2]==[1,-1] and join_order[-3]==[0,-2]:
        return 14

    '''
    sorted_lists = []
    for key in join_order.keys():
        sorted_lists.append(sorted(join_order[key]))
        sorted_lists.sort()
    if(sorted_lists not in check_orderings):
        check_orderings.append(sorted_lists)
        print(len(check_orderings)-1)
        return len(check_orderings)-1
        #print(join_order,len(check_orderings)-1)
    else:
        index_position = check_orderings.index(sorted_lists)
        print(index_position)
        return index_position
        #print(join_order,index_position)


    
    
    