from unittest import TestCase
import numpy as np
from helper_funcs import create_action_list, fill_matrix, perform_join, check_if_done, \
    _create_matrix_index_bgp_dict, _order_query_by_predicate, update_join_order, _query_from_string, is_empty
import copy


class TestHelperFuncs(TestCase):
    def setUp(self):
        self.unsorted_query = [[(1, 5, 6), (1, 4, 1)],
                               [(6, 7, 1), (1, 5, 1)],
                               [(1, 5, 1), (1, 4, 1), (6, 7, 1)],
                               [(1, 4, 1), (1, 5, 6), (1, 5, 1)]]
        self.query = [[(1, 4, 1), (1, 5, 6), (1, 5, 1)],
                      [(1, 5, 6), (1, 4, 1)],
                      [(1, 5, 1), (1, 4, 1), (6, 7, 1)],
                      [(6, 7, 1), (1, 5, 1)]]
        self.index_dict = {0: (1, 4, 1), (1, 4, 1): 0, 1: (1, 5, 6), (1, 5, 6): 1,
                           2: (1, 5, 1), (1, 5, 1): 2, 3: (6, 7, 1), (6, 7, 1): 3}
        self.filled_matrix = np.array([[[1, 4, 1],    [-1, -1, -1], [-1, -1, -1], [0, 0, 0]],
                                       [[-1, -1, -1], [1, 5, 6],    [0, 0, 0],    [0, 0, 0]],
                                       [[-1, -1, -1], [0, 0, 0],    [1, 5, 1],    [-1, -1, -1]],
                                       [[0, 0, 0],    [0, 0, 0],    [-1, -1, -1], [6, 7, 1]]])
        self.finished_matrix = np.array([[[1, 4, 1], [1, 5, 6], [1, 5, 1], [6, 7, 1]],
                                         [[0, 0, 0], [0, 0, 0], [0, 0, 0], [0, 0, 0]],
                                         [[0, 0, 0], [0, 0, 0], [0, 0, 0], [0, 0, 0]],
                                         [[0, 0, 0], [0, 0, 0], [0, 0, 0], [0, 0, 0]]])

    def test_create_action_list(self):
        matrix_size = 4
        result = create_action_list(matrix_size)
        solution = [(0, 1), (0, 2), (0, 3), (1, 2), (1, 3), (2, 3)]
        self.assertEqual(len(result), ((matrix_size*(matrix_size+1))/2-matrix_size))
        self.assertEqual(solution, result)

    def test_fill_matrix(self):
        """
        Test that it outputs correct matrix.
        """
        matrix_size = len(self.query)
        observation_matrix = fill_matrix(self.query, np.zeros((matrix_size, matrix_size, 3), np.int32))
        self.assertIsNone(np.testing.assert_array_equal(self.filled_matrix, observation_matrix))

    def test_perform_join(self):
        # test join of row 0 and 2
        observation_matrix = copy.deepcopy(self.filled_matrix)
        perform_join(0, 2, observation_matrix)
        solution = np.array([[[1, 4, 1], [-1, -1, -1], [1, 5, 1], [-1, -1, -1]],
                             [[-1, -1, -1], [1, 5, 6], [0, 0, 0], [0, 0, 0]],
                             [[0, 0, 0], [0, 0, 0], [0, 0, 0], [0, 0, 0]],
                             [[0, 0, 0], [0, 0, 0], [-1, -1, -1], [6, 7, 1]]])
        self.assertIsNone(np.testing.assert_array_equal(solution, observation_matrix))
        # test join of row 0 and 3
        perform_join(0, 3, observation_matrix)
        solution = np.array([[[1, 4, 1], [-1, -1, -1], [1, 5, 1], [6, 7, 1]],
                             [[-1, -1, -1], [1, 5, 6], [0, 0, 0], [0, 0, 0]],
                             [[0, 0, 0], [0, 0, 0], [0, 0, 0], [0, 0, 0]],
                             [[0, 0, 0], [0, 0, 0], [0, 0, 0], [0, 0, 0]]])
        self.assertIsNone(np.testing.assert_array_equal(solution, observation_matrix))
        # test if join is invariant and allways joins into the row with the lower index
        perform_join(1, 0, observation_matrix)
        solution = copy.deepcopy(self.finished_matrix)
        self.assertIsNone(np.testing.assert_array_equal(solution, observation_matrix))

    def test_check_if_done(self):
        # matrix with join candidates left
        observation_matrix = np.copy(self.filled_matrix)
        self.assertFalse(check_if_done(observation_matrix))
        # matrix without join candidates left
        observation_matrix = np.copy(self.finished_matrix)
        self.assertTrue(check_if_done(observation_matrix))
        # two independent joins
        observation_matrix = np.array([[[1, 4, 9], [1, 5, 6], [0, 0, 0], [0, 0, 0]],
                                       [[0, 0, 0], [0, 0, 0], [0, 0, 0], [0, 0, 0]],
                                       [[0, 0, 0], [0, 0, 0], [1, 5, 9], [6, 7, 1]],
                                       [[0, 0, 0], [0, 0, 0], [0, 0, 0], [0, 0, 0]]])
        self.assertTrue(check_if_done(observation_matrix))


    def test_create_matrix_index_bgp_dict(self):
        test_dict = _create_matrix_index_bgp_dict(self.query)
        self.assertDictEqual(self.index_dict, test_dict)

    def test_order_query_by_predicate(self):
        unsorted_query = copy.deepcopy(self.unsorted_query)
        _order_query_by_predicate(unsorted_query)
        self.assertEqual(self.query, unsorted_query)


    def test_update_join_order(self):
        join_order = {}
        join_order_h = {}
        update_join_order(0, 1, join_order, join_order_h)
        solution_join_order = {-1: [0, 1]}
        solution_join_order_h = {0: -1}
        self.assertDictEqual(join_order, solution_join_order)
        self.assertDictEqual(join_order_h, solution_join_order_h)
        update_join_order(0, 2, join_order, join_order_h)
        solution_join_order = {-1: [0, 1], -2: [-1, 2]}
        solution_join_order_h = {0: -2}
        self.assertDictEqual(join_order, solution_join_order)
        self.assertDictEqual(join_order_h, solution_join_order_h)
        update_join_order(3, 4, join_order, join_order_h)
        solution_join_order = {-1: [0, 1], -2: [-1, 2], -3: [3, 4]}
        solution_join_order_h = {0: -2, 3: -3}
        self.assertDictEqual(join_order, solution_join_order)
        self.assertDictEqual(join_order_h, solution_join_order_h)
        update_join_order(0, 3, join_order, join_order_h)
        solution_join_order = {-1: [0, 1], -2: [-1, 2], -3: [3, 4], -4: [-2, -3]}
        solution_join_order_h = {0: -4, 3: -3}
        self.assertDictEqual(solution_join_order, join_order)
        self.assertDictEqual(solution_join_order_h, join_order_h)

    def test_query_from_string(self):
        query_string = "-1,12,20;-2,10,20;-1,15,-2"
        query = _query_from_string(query_string)
        solution = [[(-1,12,20), (-1,15,-2)], [(-2,10,20), (-1,15,-2)], [(-1,15,-2), (-1,12,20), (-2,10,20)]]
        self.assertEqual(solution, query)

    def test_is_empty(self):
        self.assertFalse(is_empty(0, self.finished_matrix))
        self.assertTrue(is_empty(1, self.finished_matrix))

if __name__ == "__main__":
    unittest.main()
    print("Everything passed")
