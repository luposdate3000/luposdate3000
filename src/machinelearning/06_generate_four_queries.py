#!/usr/bin/env python
import sys
from itertools import combinations
n_triples = 4

def generate(n,c):
	#Generating placeholders such as ?s0, ?o0
	temp=[]
	for i in range(n):
		temp.append("?"+c+str(i))
	return temp

def generate_queries():
	# current_predicates to store the predicates of one subject
	# all_predicates contains all possible n-element predicate combinations
	sparql_prefixes = []
	current_predicates, all_predicates, unique_predicates_set = set(), set(), set()
	last_subject = " "
	prev_line = ''  #  ASCII value of space is less than all the starting characters in the triples file
	with open(input_file, "r") as file:
		for line in file:
			# Check if input triple file is sorted by checking if the previous line is lexicographically greater than the current line
			print(prev_line, line)
			if prev_line>line:
				print("Error! The input triple file has to be sorted")
				exit(1)
			prev_line = line

			# If the line is a prefix, then convert triple store prefix to SPARQL prefix.
			if line[0] == "@":
				tmp = line.split(" ")
				tmp[-1] = "\n"
				tmp[0] = "PREFIX"
				prefix = " ".join(tmp)
				sparql_prefixes.append(prefix)

			else:
				lis = line.split(' ')
				current_subject, cur_predicate = lis[0], lis[1]
				# To find the unique predicates
				unique_predicates_set.add(cur_predicate)

				if current_subject == last_subject:     # For same subject triples
					current_predicates.add(cur_predicate)
					last_subject = current_subject
				# Subject changes, so if that subject has atleast n predicates generate all possible predicate combinations and append them to all_predicates
				# Then reset the current_predicates set to empty and add the new predicate corresponding to the new subject to current_predicates.
				# Finally change reset last subject to the current subject
				else:
					if len(current_predicates)>=n_triples:
						cur_pred_combinations = combinations(current_predicates,n_triples)
						for pred_combination in cur_pred_combinations:
							all_predicates.add(pred_combination)
					current_predicates.clear()
					current_predicates.add(cur_predicate)
					last_subject = current_subject
			
	# For the last subject, if number of predicates is greater than n, then generate all possible combinations and append them  to all_predicates
	if len(current_predicates)>=n_triples:  
		cur_pred_combinations = combinations(current_predicates,n_triples)
		for pred_combination in cur_pred_combinations:
			all_predicates.add(pred_combination)

	# Eliminate all duplicate predicates -> find all unique predicates
	unique_predicates  = list(unique_predicates_set)
	unique_predicates.sort()
	######################## ID GENERATION #############################
	unique_predicates_with_key = {}#predicate:id
	# Write dictionary for predicates to file. Ids starting at 1.
	with open(output_directory + "dictionary", "w") as dictionary_file:
		for idx, predicate in enumerate(unique_predicates):
			#Storing id of predicate 
			unique_predicates_with_key[predicate] = idx+1
			dictionary_file.write(str(idx+1) + " " + predicate + "\n")
	######################## ID GENERATION #############################

	# Create all join patterns for joins on subjects and objects.
	join_patterns = []
	subject=generate(n_triples,"s")
	object=generate(n_triples,"o")
	# join pattern for subject - subject joins
	if joins == "s" or joins == "a":
		placeholders = ["?x" for i in range(n_triples)]  # Creating an array of n-triple number of "?x" as placeholders
		join_patterns.append(placeholders+object)

	lupos3000_query_params = ""  # holds the paths to the created files
	python_ml_params = ""  # currently not used
	file_index = 0  # number of current file
	join_on = ""

	# Create a query for every join pattern.
	for p in range(len(join_patterns)):
		all_subjects = []   
		all_objects = []
		for q in range(n_triples):
			all_subjects.append(join_patterns[p][q])
			all_objects.append(join_patterns[p][q+n_triples])
		if joins != "o" and p == 0:
			join_on = "S"
		elif joins == "o" and p == 0:
			join_on = "O"
		elif joins == "a" and p == 10:
			join_on = "O"
		else:
			join_on = ""
		
		for predicate_combo in all_predicates:
			#Creating the .sparql files
				with open(output_directory +"q" + join_on + str(file_index) + ".sparql", "w") as query:
					for sparql_prefix in sparql_prefixes:
						query.write(sparql_prefix)
					query.write("\n")
					query.write("SELECT * {\n")
					for i in range(n_triples):
						query.write("    " + all_subjects[i] + " " + predicate_combo[i] + " " + all_objects[i] + " .\n")
					query.write("}\n")

				with open(output_directory + "q" + join_on + str(file_index) + ".mlq", "w") as python_q_file:
					#Bucket sort to find out how many distinct variables there are.
					#Creating the .mlq files
					# Combining all subjects and objects to apply bucket sort
					subject_object = all_subjects+all_objects
					s0 = subject_object.pop(0)
					bucket_list = [[s0]]
					for a in subject_object:
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
					# Accesing the id of each predicate in a tuple of four predicate combinations from the dictionary unique_predicates_with_key
					for i in range(n_triples):
						output_string = str(ids[all_subjects[i]]) + "," + str(unique_predicates_with_key[predicate_combo[i]]) + "," + str(ids[all_objects[i]]) + ";"
						if i == n_triples-1:     # For last line add a new line character at the end
							python_q_file.write(output_string + "\n")
						else:
							python_q_file.write(output_string)

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
		output_directory = sys.argv[2]
		joins = sys.argv[3]
	except:
		print_error()
		sys.exit()

	generate_queries()
