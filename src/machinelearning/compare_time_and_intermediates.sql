use machinelearningbenchmarks;

select SUM(ctr) as sum_dist_from_avg from(
select count(*) as ctr from benchmark_values bv2 join
(select bv1.dataset_id,bv1.query_id,AVG(bv1.value/bv1.valuetime) AS a,COUNT(*) as c from benchmark_values bv1 where bv1.value is not null and bv1.valuetime is not null group by bv1.dataset_id,bv1.query_id) as bv3
on bv3.dataset_id=bv2.dataset_id and bv3.query_id = bv2.query_id where (((bv2.value/bv2.valuetime)/a>1.2) OR (a/(bv2.value/bv2.valuetime))>1.2) and c >1 group by bv3.dataset_id,bv3.query_id
)as bv4;

select SUM(ctr) as sum_all_for_avg from(
select count(*) as ctr from benchmark_values bv2 join
(select bv1.dataset_id,bv1.query_id,COUNT(*) as c from benchmark_values bv1 where bv1.value is not null and bv1.valuetime is not null group by bv1.dataset_id,bv1.query_id) as bv3
on bv3.dataset_id=bv2.dataset_id and bv3.query_id = bv2.query_id where c >1 group by bv3.dataset_id,bv3.query_id
)as bv4;


select SUM(ctr) as sum_dist_from_avg_1000 from(
select count(*) as ctr from benchmark_values bv2 join
(select bv1.dataset_id,bv1.query_id,AVG(bv1.value/bv1.valuetime) AS a,COUNT(*) as c from benchmark_values bv1 where bv1.value is not null and bv1.valuetime is not null and bv1.valuetime>=1000 group by bv1.dataset_id,bv1.query_id) as bv3
on bv3.dataset_id=bv2.dataset_id and bv3.query_id = bv2.query_id where (((bv2.value/bv2.valuetime)/a>1.2) OR (a/(bv2.value/bv2.valuetime))>1.2) and c >1 group by bv3.dataset_id,bv3.query_id
)as bv4;

select SUM(ctr) as sum_all_for_avg_1000 from(
select count(*) as ctr from benchmark_values bv2 join
(select bv1.dataset_id,bv1.query_id,COUNT(*) as c from benchmark_values bv1 where bv1.value is not null and bv1.valuetime is not null and bv1.valuetime>=1000 group by bv1.dataset_id,bv1.query_id) as bv3
on bv3.dataset_id=bv2.dataset_id and bv3.query_id = bv2.query_id where c >1 group by bv3.dataset_id,bv3.query_id
)as bv4;


select SUM(ctr) as sum_dist_from_avg_2000 from(
select count(*) as ctr from benchmark_values bv2 join
(select bv1.dataset_id,bv1.query_id,AVG(bv1.value/bv1.valuetime) AS a,COUNT(*) as c from benchmark_values bv1 where bv1.value is not null and bv1.valuetime is not null and bv1.valuetime>=2000 group by bv1.dataset_id,bv1.query_id) as bv3
on bv3.dataset_id=bv2.dataset_id and bv3.query_id = bv2.query_id where (((bv2.value/bv2.valuetime)/a>1.2) OR (a/(bv2.value/bv2.valuetime))>1.2) and c >1 group by bv3.dataset_id,bv3.query_id
)as bv4;

select SUM(ctr) as sum_all_for_avg_2000 from(
select count(*) as ctr from benchmark_values bv2 join
(select bv1.dataset_id,bv1.query_id,COUNT(*) as c from benchmark_values bv1 where bv1.value is not null and bv1.valuetime is not null and bv1.valuetime>=2000 group by bv1.dataset_id,bv1.query_id) as bv3
on bv3.dataset_id=bv2.dataset_id and bv3.query_id = bv2.query_id where c >1 group by bv3.dataset_id,bv3.query_id
)as bv4;


select SUM(ctr) as sum_dist_from_avg_10000 from(
select count(*) as ctr from benchmark_values bv2 join
(select bv1.dataset_id,bv1.query_id,AVG(bv1.value/bv1.valuetime) AS a,COUNT(*) as c from benchmark_values bv1 where bv1.value is not null and bv1.valuetime is not null and bv1.valuetime>=10000 group by bv1.dataset_id,bv1.query_id) as bv3
on bv3.dataset_id=bv2.dataset_id and bv3.query_id = bv2.query_id where (((bv2.value/bv2.valuetime)/a>1.2) OR (a/(bv2.value/bv2.valuetime))>1.2) and c >1 group by bv3.dataset_id,bv3.query_id
)as bv4;

select SUM(ctr) as sum_all_for_avg_10000 from(
select count(*) as ctr from benchmark_values bv2 join
(select bv1.dataset_id,bv1.query_id,COUNT(*) as c from benchmark_values bv1 where bv1.value is not null and bv1.valuetime is not null and bv1.valuetime>=10000 group by bv1.dataset_id,bv1.query_id) as bv3
on bv3.dataset_id=bv2.dataset_id and bv3.query_id = bv2.query_id where c >1 group by bv3.dataset_id,bv3.query_id
)as bv4;
