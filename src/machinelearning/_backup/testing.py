import pickle

file = open("15j_100_3:7_4_triples.pkl",'rb')
best_percentages = pickle.load(file)
worst_percentages = pickle.load(file)
print(best_percentages)
print(worst_percentages)