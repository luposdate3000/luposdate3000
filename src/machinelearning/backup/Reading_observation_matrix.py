import pickle

f = open('Observation_matrix','rb')
while 1:
    try:
        print(pickle.load(f))
    except EOFError:
        break