import numpy as np
'''
observation_matrix = [
                        [   
                            [-2,1,-5],
                            [-1,-1,-1],
                            [-1,-1,-1],
                            [-1,-1,-1]
                        ],
                        
                        [
                            [-1,-1,-1],
                            [-2,7,-17],
                            [-1,-1,-1],
                            [-1,-1,-1]
                        ],
                        [
                            [-1,-1,-1],
                            [-1,-1,-1],
                            [-2,-14,-3],
                            [-1,-1,-1]
                        ],
                        [
                            [-1,-1,-1],
                            [-1,-1,-1],
                            [-1,-1,-1],
                            [-2,15,-9]                           
                        ]                        
                    ]
'''
observation_matrix =[[[-2, 1, -5], [-1, -1, -1], [-1, -1, -1], [-2, 15, -9]], 
                    [[-1, -1, -1], [-2, 7, -17], [-1, -1, -1], [-1, -1, -1]], 
                    [[-1, -1, -1], [-1, -1, -1], [-2, -14, -3], [-1, -1, -1]], 
                    [[0, 0, 0], [0, 0, 0], [0, 0, 0], [0, 0, 0]]]
index_a = 0
index_b = 2
#print(observation_matrix)

for i, v in enumerate(observation_matrix[index_b][:]):
    if v[0] != 0:
        if observation_matrix[index_a][i][0] == -1 and \
                observation_matrix[index_a][i][1]== -1 and \
                observation_matrix[index_a][i][2] == -1 or \
                observation_matrix[index_a][i][0]== 0 and \
                observation_matrix[index_a][i][1] == 0 and \
                observation_matrix[index_a][i][2] == 0:
            observation_matrix[index_a][i] = v  # overwrite value with the value from b row
        else:
            None    # dont overwrite triples
    observation_matrix[index_b][i] = [0,0,0]  # set entries in b row to 0

print(observation_matrix)