#!/usr/bin/env python
"""
Generate query files and training files for the ML module.

Arg1: path to triple file
	Input is the triple file, where all the triples are stored.

Arg2: output directory
	Output directory where the query files are written to.

Arg3: "s" or "o" or "a"
	Indicate whether triples are joined on subject "s" or object "o" or on both "a".

Output: Query files into the output directory
	For every query a file is created and written into the output directory.
	Every SPARQL query file ends with .sparql.
	For every SPARQL query there is one file created that acts later on as input
	for the ML model. These files end on .mlq.
"""

import sys
from itertools import combinations

def generate(n,c):
    temp=[]
    for i in range(n):
        temp.append("?"+c+str(i))
    return temp


def generate_queries():
	# Read triple file and find all prefixes and predicates.
	predicates = []
	prefixes = []
	with open(input_file, "r") as file:
		for line in file:
			if line[0] == "@":
				prefixes.append(line)
			else:
				#ERROR : this assumes that ech line contains exactly one triple, and each triple is terminted with an '.' which is not the case. Empty lines crash.
				predicates.append(line.split(" ")[1])

	# Eliminate all duplicate predicates -> find all unique predicates
	unique_predicates_tmp = list(set(predicates))
	unique_predicates_tmp.sort()
	unique_predicates = []
	for predicate in unique_predicates_tmp:
		#ERROR : this assumes that every predicate has the form 'prefix:postfix' - this assumption is wrong. Iris yould be written as '<abc...xyz>' too.
		if predicate.split(":")[1] != '':
			unique_predicates.append(predicate)

	######################## ID GENERATION #############################

	# Write dictionary for predicates to file. Ids starting at 1.
	with open(output_directory + "dictionary", "w") as dictionary_file:
		for idx, predicate in enumerate(unique_predicates):
			dictionary_file.write(str(idx+1) + " " + predicate + "\n")

	######################## ID GENERATION #############################

	# Convert triple store prefix to SPARQL prefix.
	sparql_prefixes = []
	for prefix in prefixes:
		tmp = prefix.split(" ")
		tmp[-1] = "\n"
		tmp[0] = "PREFIX"
		prefix = " ".join(tmp)
		sparql_prefixes.append(prefix)

	# Manually create all join patterns for joins on subjects and objects.
	join_patterns = []
	n=4
	subject=generate(n,"s")
	object=generate(n,"o")
	# join pattern for subject - subject joins
	if joins == "s" or joins == "a":
		#join_patterns.append(["?x", "?x", "?x", "?o0", "?o1", "?o2"])
		join_patterns.append(["?x", "?x", "?x","?x", "?o0", "?o1", "?o2", "?o3"])
	# join patterns for subject - object joins
	if joins == "a":
		subject_dict={}
		for i in range(len(subject)):
			subject_dict[subject[i]]=i

		y=[]
		for i in range(1,n):
			a = combinations(subject, i)
			for j in a:
				y.append(j)

		for i in y:
			join_orders=["?x" for i in range(2*n)]
			indexes=[]
			for j in i:
				index=subject_dict[j]
				join_orders[index]=j
				indexes.append(index)
			for k in range(0,n):
				if(k not in indexes):
					join_orders[k+n]=object[k]
			join_patterns.append(join_orders)
	# join pattern for object - object join
	if joins == "o" or joins == "a":
		#join_patterns.append(["?s0", "?s1", "?s2", "?x", "?x", "?x"])
		join_patterns.append(["?s0", "?s1", "?s2","?s3", "?x", "?x", "?x", "?x"])

	lupos3000_query_params = ""  # holds the paths to the created files
	python_ml_params = ""  # currently not used
	file_index = 0  # number of current file
	join_on = ""
	# Create a query for every join pattern.
	for p in range(len(join_patterns)):
		s0 = join_patterns[p][0]
		s1 = join_patterns[p][1]
		s2 = join_patterns[p][2]
		s3 = join_patterns[p][3]
		o0 = join_patterns[p][4]
		o1 = join_patterns[p][5]
		o2 = join_patterns[p][6]
		o3 = join_patterns[p][7]
		if joins != "o" and p == 0:
			join_on = "S"
		elif joins == "o" and p == 0:
			join_on = "O"
		elif joins == "a" and p == 10:
			join_on = "O"
		else:
			join_on = ""
		# Create queries that cover every possible combination of predicates.
		# Example: For 23 predicates and 3 joins, there are 23 nCr 3 = 1771 possible combinations.
		for i in range(len(unique_predicates)):
			for j in range(i + 1, len(unique_predicates)):
				for k in range(j + 1, len(unique_predicates)):
					for l in range(k + 1, len(unique_predicates)):
					# Write SPARQL query into file.
						with open(output_directory +"q" + join_on + str(file_index) + ".sparql", "w") as query:
							for sparql_prefix in sparql_prefixes:
								query.write(sparql_prefix)
							query.write("\n")
							query.write("SELECT * {\n")
							query.write("    " + s0 + " " + unique_predicates[i] + " " + o0 + " .\n")
							query.write("    " + s1 + " " + unique_predicates[j] + " " + o1 + " .\n")
							query.write("    " + s2 + " " + unique_predicates[k] + " " + o2 + " .\n")
							query.write("    " + s3 + " " + unique_predicates[l] + " " + o3 + " .\n")
							query.write("}\n")
						# Convert SPARQL query into input for the machine learning model and write to file.
						with open(output_directory + "q" + join_on + str(file_index) + ".mlq", "w") as python_q_file:
							# Bucket sort to find out how many distinct variables there are.
							bucket_list = [[s0]]
							for a in [s1, s2,s3, o0, o1, o2,o3]:
								for b in bucket_list:
									if a in b:
										b.append(a)
										break
									else:
										bucket_list.append([a])

							ids = {}
							for idx, bucket in enumerate(bucket_list):
								for variable in bucket:
									ids[variable] = -(idx + 1)
								# Write machine learning input to file.
							python_q_file.write(str(ids[s0]) + "," + str(i+1) + "," + str(ids[o0]) + ";")
							python_q_file.write(str(ids[s1]) + "," + str(j+1) + "," + str(ids[o1]) + ";")
							python_q_file.write(str(ids[s2]) + "," + str(k+1) + "," + str(ids[o2]) + ";")
							python_q_file.write(str(ids[s3]) + "," + str(l+1) + "," + str(ids[o3]) + ";" + "\n")
								# Save file names and locations of machine learning input to variable.
							python_ml_params += output_directory + "q" + join_on + str(file_index) + ".mlq" + ";"
							# Save file names and locations of SPARQL queries to variable.
							lupos3000_query_params += output_directory + "q" + join_on + str(file_index) + ".sparql" + ";"

							file_index += 1

		python_ml_params = python_ml_params[:-1]  # cut off last semicolon
		lupos3000_query_params = lupos3000_query_params[:-1]  # cut off last semicolon
		# Write file names and locations of SPARQL queries to file "luposdate3000_query_params".
		# This file acts as input for luposdate3000 to benchmark the query files,
		# that paths are written in this file.
		with open(output_directory + "luposdate3000_query_params", "w") as params_file:
			params_file.write(lupos3000_query_params)
		# Write machine learning input file names and locations to file "python_ml_params".
		# Currently not used.
		with open(output_directory + "python_ml_params", "w") as p_params_file:
			p_params_file.write(python_ml_params)


def print_error():
	print("Usage: ")
	print("Param 1: path to triple file")
	print("Param 2: output directory for files")
	print("Param 3: \"s\", \"o\" or \"a\": join on subject/object/all")


if __name__ == '__main__':
	try:
		input_file = sys.argv[1]
		output_directory = sys.argv[2]+"/"
		joins = sys.argv[3]
	except:
		print_error()
		sys.exit()

	generate_queries()
