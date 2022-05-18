total_join_orders = [{-1: [0, 1], -2: [-1, 2], -3: [-2, 3]},
{-1: [0, 1], -2: [-1, 3], -3: [-2, 2]}
,{-1: [0, 1], -2: [2, 3], -3: [-1, -2]}
,{-1: [2, 3], -2: [0, 1], -3: [-2, -1]}
,{-1: [0, 2], -2: [-1, 1], -3: [-2, 3]}
,{-1: [0, 2], -2: [-1, 3], -3: [-2, 1]}
,{-1: [0, 2], -2: [1, 3], -3: [-1, -2]}
,{-1: [1, 3], -2: [0, 2], -3: [-2, -1]}
,{-1: [0, 3], -2: [-1, 1], -3: [-2, 2]}
,{-1: [0, 3], -2: [-1, 2], -3: [-2, 1]}
,{-1: [0, 3], -2: [1, 2], -3: [-1, -2]}
,{-1: [1, 2], -2: [0, 3], -3: [-2, -1]}
,{-1: [1, 2], -2: [-1, 3], -3: [0, -2]}
,{-1: [1, 2], -2: [0, -1], -3: [0, 3]}
,{-1: [1, 3], -2: [-1, 2], -3: [0, -2]}
,{-1: [1, 3], -2: [0, -1], -3: [0, 2]}
,{-1: [2, 3], -2: [0, -1], -3: [0, 1]}
,{-1: [2, 3], -2: [1, -1], -3: [0, 1]}]
check_orderings =[]
join_order_table = {}
for join_order in total_join_orders:
    sorted_lists = []
    for key in join_order.keys():
        sorted_lists.append(sorted(join_order[key]))
        sorted_lists.sort()
    if(sorted_lists not in check_orderings):
        check_orderings.append(sorted_lists)
        print(join_order,len(check_orderings)-1)
    else:
        index_position = check_orderings.index(sorted_lists)
        print(join_order,index_position)

#print(join_order_table)