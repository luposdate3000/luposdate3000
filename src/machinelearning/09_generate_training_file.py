#!/usr/bin/env python
"""
Generate the training file that is used for training the ML model.

Arg1: path to benchmark file
    The benchmarked queries. Luposdate3000 outputs the benchmark into a file, ending with .bench.

Arg2: output directory
    The directory where the training file is written to.

Output: training file
    The training file that is the input for the ML model, called "train.me".
"""

import sys
import re

if __name__ == '__main__':
    dictionary = {}
    dictionaryInverse = {}
    try:
        input_benchmark_file_name = sys.argv[1]
        output_training_file_name = sys.argv[2] + "train.me"
        output_dictionary_file_name = sys.argv[2] + "train.dict"
    except:
        print("Usage: ")
        print("Param 1: path to benchmark file (.bench)")
        print("Param 2: output directory for training file")
        sys.exit()

    with open(input_benchmark_file_name, "r") as input_benchmark_file:
        with open(output_training_file_name, "w") as output_training_file:
            for line in input_benchmark_file:
                tmp = line.split(" ")
                with open(tmp[0], "r") as query_file:
                    data = query_file.read()
                data = re.sub('\\s+', ' ', data)
                data = data[data.find('{') + 1:data.find('}')]
                data2 = data.split(" ")
                query = []
                triple = []
                variables = {}
                for word in data2:
                    if len(word) > 0:
                        if word == ".":
                            query.extend(triple)
                            triple = []
                        elif word[0] == "?":
                            id = variables.get(word, -2 - len(variables))
                            variables[word] = id
                            triple.append(str(id))
                        else:
                            id = dictionary.get(word, len(dictionary))
                            dictionary[word] = id
                            dictionaryInverse[id] = word
                            triple.append(str(id))
                tmp[0] = ','.join(query)
                output_training_file.write(" ".join(tmp))
    with open(output_dictionary_file_name, "w") as output_dictionary_file:
        for i in range(len(dictionary)):
            output_dictionary_file.write(dictionaryInverse[i] + "\n")
