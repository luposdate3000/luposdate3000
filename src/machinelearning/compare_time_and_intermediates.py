#!/usr/bin/env -S python3 -OO -u
import mysql.connector
import csv

db = mysql.connector.connect(host="localhost", user="machinelearningbenchmarks", password="machinelearningbenchmarks", database="machinelearningbenchmarks")
cursor = db.cursor()

result = [["x"]]
for percentage in [1.05,1.1,1.2,1.4,2,4,8]: # difference between single value and average for query
    result[0].append(percentage)
    for i in range(1, 18):
        low = 2**(i - 1) + 1
        high = 2**i
        if low == 2:
            low = 0
        if len(result) <= i:
            result.append([high])

        cursor.execute(
            "select SUM(ctr) as sum_dist_from_avg_1000 from( select count(*) as ctr from benchmark_values bv2 join (select bv1.dataset_id,bv1.query_id,AVG(bv1.value/bv1.valuetime) AS a,COUNT(*) as c from benchmark_values bv1 where bv1.value is not null and bv1.valuetime is not null and bv1.valuetime>=%s and bv1.valuetime<=%s group by bv1.dataset_id,bv1.query_id) as bv3 on bv3.dataset_id=bv2.dataset_id and bv3.query_id = bv2.query_id where (((bv2.value/bv2.valuetime)/a>%s) OR (a/(bv2.value/bv2.valuetime))>%s) and c >1 group by bv3.dataset_id,bv3.query_id )as bv4;",
            (low, high, percentage, percentage))
        value_actual = cursor.fetchone()[0]

        cursor.execute(
            "select SUM(ctr) as sum_all_for_avg_1000 from( select count(*) as ctr from benchmark_values bv2 join (select bv1.dataset_id,bv1.query_id,COUNT(*) as c from benchmark_values bv1 where bv1.value is not null and bv1.valuetime is not null and bv1.valuetime>=%s and bv1.valuetime<=%s group by bv1.dataset_id,bv1.query_id) as bv3 on bv3.dataset_id=bv2.dataset_id and bv3.query_id = bv2.query_id where c >1 group by bv3.dataset_id,bv3.query_id)as bv4;",
            (low, high))
        value_max = cursor.fetchone()[0]

        data = None
        if value_actual is not None:
            data = 1-value_actual / value_max
        result[i].append(data)
        print(percentage, low, high, data)
with open("compare_time_and_intermediates.csv", "w+") as my_csv:
    csvWriter = csv.writer(my_csv, delimiter=',')
    csvWriter.writerows(result)
