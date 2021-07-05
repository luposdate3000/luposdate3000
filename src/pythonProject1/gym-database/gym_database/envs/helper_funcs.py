"""Helper functions for the database Gym environment"""

import numpy as np
from typing import List, Tuple, Dict


def create_action_list(matrix_size: int) -> List[Tuple[int, int]]:
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
        (0,1),(0,2),...,(0,matrix_size),(1,2),(1,3),...,(2,3),(2,4)...,(matrix_size-2,n_bgps-1)
    """

    action_list = []
    for i in range(matrix_size):
        for j in range(i+1,matrix_size):
            action_list.append((i, j))
    return action_list

def fill_matrix(sorted_query: List[List[Tuple[int, int, int]]], observation_matrix: np.ndarray) -> np.ndarray:
    """Function to initially fill the observation matrix with bgps and its join candidates.

    Takes bgps from query and sets the diagonal values of the corresponding entry in the
    observation matrix to the values of the corresponding bgp. The possible join candidates
    are marked with a [-1, -1, -1]. Join variables are negative integers.


    Parameters
    ----------
    sorted_query : List[List[Tuple[int, int, int]]
        list of lists of bgps and join candidates in query:
        [bgp_a, bgp_b, ...]. bgp_a = [bgp, join_candidate0, join_candidate1, ...]
    observation_matrix : np.ndarray
        empty observation matrix with all entries set to zero

    Returns
    -------
    np.ndarray
        initial observation matrix with bgps and join candidates
    """

    # Create dictionary to map bgp to its index in matrix and vice versa.
    index_dict = _create_matrix_index_bgp_dict(sorted_query)

    # Fill matrix with bgps from query.
    for index, bgps in enumerate(sorted_query): # bgps is a list with the bgp and its join candidates
        observation_matrix[index, index] = bgps[0]
        for join_candidate in bgps[1:]:  # fill matrix with join candidates
            observation_matrix[index_dict[join_candidate], index] = -1 # possible joins marked with -1

    return observation_matrix

# TODO: DOC!!
def perform_join(index_a: int, index_b: int, observation_matrix: np.ndarray):
    """ Joins bgp a and bgp b.

    To join a and b, the corresponding entries of a and b are merged.
    A 1 stands for the corresponding bgp and a 2 for its join candidates.
    Therefore a 1 is placed in the row of a at the column of b.
    And all the 2s are copied to row a.
    And the 1 and the 2s in the row of b are deleted.

    Parameters
    ----------
    index_a : int
        index of bgp_a in obervation_matrix
    index_b : int
        index of bgp_b in observation_matrix
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
                None    # dont overwrite bgps
        observation_matrix[index_b, i] = 0  # set entries in b row to 0


def is_empty(index: int, observation_matrix: np.ndarray) -> bool:
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


def _create_matrix_index_bgp_dict(sorted_query: List[List[Tuple[int, int, int]]]) -> Dict:
    """Function that maps every BGP to an index in the observation matrix and vice versa.

    Parameters
    ----------
    sorted_query: List[List[Tuple[int, int, int]]]
        The sorted query where the occuring BGPs are in.

    Returns
    -------
    Dict
        The Dict containing a mapping between an index in the observation matrix and a BGP
        and vice versa.
    """
    new_dict = {}
    for index, list_of_bgps in enumerate(sorted_query):
        new_dict[index] = list_of_bgps[0]
        new_dict[list_of_bgps[0]] = index
    return new_dict

# TODO: faster, faster! 0,2 ms too much
# TODO: DOC!!
def check_if_done(observation_matrix: np.ndarray) -> bool:
    """Function to check if the episode is finished.

    Takes the observation matrix and checks if all 1s are in one row. This means that all
    bgps have been joined and the episode is finished.

    Parameters
    ----------
    query : list[int]
        list of all bgps that are to join, number represents number of column in observation matrix
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
    # counter = 0
    # for row in done_array:
    #     if row[0] == 0 or row[1] == 0:
    #         counter += 1
    #     else:
    #         return False
    # if counter == len(done_array):
    #     return True


def load_query(query_string: str) -> List[List[Tuple[int, int, int]]]:
    """Function that loads a new query.

    Function gets a serialized query in form of a string. It builds a query object and
    sorts the BGPs on their predicate (2nd ID).

    Parameters
    ----------
    query_string: str
        A serialized query object in form of a string.

    Returns
    -------
    List[List[Tuple[int, int, int]]
        The query is a list of Triples that represent the BGPs and its join candidates.
    """

    # Build query list from string
    query = _query_from_string(query_string)
    # Order triples in query by predicate (2nd ID)
    _order_query_by_predicate(query)
    return query


def _query_from_string(query_string: str) -> List[List[Tuple[int, int, int]]]:
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
    query = []
    join_variables = query_string.split(";")[:-1]
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
    return query


def _order_query_by_predicate(unsorted_query: List[List[Tuple[int, int, int]]]):
    """Method that orders a query by its predicate (2nd ID) in ascending order.

    Parameters
    ----------
    unsorted_query: List[List[Tuple[int, int, int]]]
        An unsorted query, a list of Triples and its join candidates.
    """

    unsorted_query.sort(key=lambda x: x[0][1])



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


def update_join_order(left: int, right: int, join_order: Dict, join_order_h: Dict):
    """Method to update the current join order.

    Parameters
    ---------
    left: int
        Row in observation matrix to join.
    right: int
        Row in observation matrix to join.
    join_order: Dict
        The join order is saved as a graph and encoded in a Dict. It gets updated by this method.
    join_order_h: Dict
        This is a Dict for assistant values to build join_order.
    """

    # invariant joining, join into lower numbered row, important for this to work
    temp_one = min(left, right)
    temp_two = max(left, right)
    left = temp_one
    right = temp_two

    # index has negative values, starting from -1, to avoid collisions with IDs
    index = -(len(join_order)+1)

    if left in join_order_h and right in join_order_h:
        join_order[index] = [join_order_h[left], join_order_h[right]]
        join_order_h[left] = index
    elif left in join_order_h:
        join_order[index] = [join_order_h[left], right]
        join_order_h[left] = index
    elif right in join_order_h:
        join_order[index] = [left, join_order_h[right]]
        join_order_h[right] = index
    else:
        join_order[index] = [left, right]
        join_order_h[left] = index


def calculate_reward(benched_query, join_order):
    # print(benched_query)
    # get number of join order
    join_order_n = _join_order_to_number(join_order)
    # calculate max
    execution_times = []
    execution_times.append(int(float(benched_query[0][2])))
    execution_times.append(int(float(benched_query[1][2])))
    execution_times.append(int(float(benched_query[2][2])))
    best_execution_t = max(execution_times)  # executions/sec

    # calculate % of max for given join order
    reward0 = execution_times[join_order_n]/best_execution_t

    # normalize to -1 .. 0 .. 1
    if reward0 < 0.5:
        reward1 = -1 + 4 * pow(reward0, 2)
    else:
        reward1 = 4 * pow(reward0, 2) - 4 * reward0 + 1


    # if join_order_n == np.argmax(execution_times):
    #     reward1 = 1
    # else:
    #     reward1 = -1
    # print(reward1)

    return reward1

def _join_order_to_number(join_order):
    if join_order[-1] == [0, 1]:
        return 0
    elif join_order[-1] == [0, 2]:
        return 1
    elif join_order[-1] == [1, 2]:
        return 2