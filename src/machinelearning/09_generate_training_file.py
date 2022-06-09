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


def generate_train_file():
    """
    Read every line from benchmark file, the first string is the path to the query file.
    Use the path to the query file to find the corresponding .mlq files and read them.
    Take their content (the SPARQL query transformed into numbers) and write the training file.
    A line in the training file looks like this:
    QUERY JOIN_ORDER EXECUTION_TIME(in executions/sec)
    -1,1,-2;-1,2,-3;-1,3,-4 0 14034.0072
    """

    with open(input_file, "r") as benchmark_file:
        with open(output_directory + "train.me", "w") as train_file:
            # Read every line from benchmark file
            for line in benchmark_file:
                # Take path to query file and replace the .sparql ending with .mlq.
                tmp = line.split(" ")
                ml_query_file_string = tmp[0].split("/")
                tmp2 = ml_query_file_string[-1].split(".")[0]
                tmp3 = tmp2 + ".mlq"
                ml_query_file_string = ml_query_file_string[:-1]
                ml_query_file_string.append(tmp3)
                ml_query_file_string = "/".join(ml_query_file_string)
                # Use the path to the .mlq file to open it and read out the content.
                with open(ml_query_file_string, "r") as ml_query_file:
                    for line2 in ml_query_file:
                        ml_query = line2[:-1]
                tmp[0] = ml_query
                # Merge the content of the .mlq file with the benchmark results and write to file.
                tmp3 = " ".join(tmp)
                train_file.write(tmp3)


def print_error():
    print("Usage: ")
    print("Param 1: path to benchmark file (.bench)")
    print("Param 2: output directory for training file")


if __name__ == '__main__':
    try:
        input_file = sys.argv[1]
        output_directory = sys.argv[2]
    except:
        print_error()
        sys.exit()

    generate_train_file()
