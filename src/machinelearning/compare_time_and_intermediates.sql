use machinelearningbenchmarks;

select SUM(ctr) as sum_larger_than_avg from(
select count(*) as ctr from benchmark_values bv2 join
(select bv1.dataset_id,bv1.query_id,AVG(bv1.value/bv1.valuetime) AS a,COUNT(*) as c from benchmark_values bv1 where bv1.value is not null and bv1.valuetime is not null group by bv1.dataset_id,bv1.query_id) as bv3
on bv3.dataset_id=bv2.dataset_id and bv3.query_id = bv2.query_id where bv2.value/bv2.valuetime>a and c >1 group by bv3.dataset_id,bv3.query_id
)as bv4;

select SUM(ctr) as sum_smaller_than_avg from(
select count(*) as ctr from benchmark_values bv2 join
(select bv1.dataset_id,bv1.query_id,AVG(bv1.value/bv1.valuetime) AS a,COUNT(*) as c from benchmark_values bv1 where bv1.value is not null and bv1.valuetime is not null group by bv1.dataset_id,bv1.query_id) as bv3
on bv3.dataset_id=bv2.dataset_id and bv3.query_id = bv2.query_id where bv2.value/bv2.valuetime<a and c >1 group by bv3.dataset_id,bv3.query_id
)as bv4;


select SUM(ctr) as sum_all_for_avg from(
select count(*) as ctr from benchmark_values bv2 join
(select bv1.dataset_id,bv1.query_id,COUNT(*) as c from benchmark_values bv1 where bv1.value is not null and bv1.valuetime is not null group by bv1.dataset_id,bv1.query_id) as bv3
on bv3.dataset_id=bv2.dataset_id and bv3.query_id = bv2.query_id where c >1 group by bv3.dataset_id,bv3.query_id
)as bv4;



select SUM(ctr) as sum_larger_than_avg from(
select count(*) as ctr from benchmark_values bv2 join
(select bv1.dataset_id,bv1.query_id,AVG(bv1.value/bv1.valuetime) AS a,COUNT(*) as c from benchmark_values bv1 where bv1.value is not null and bv1.valuetime is not null and bv1.valuetime>=1000 group by bv1.dataset_id,bv1.query_id) as bv3
on bv3.dataset_id=bv2.dataset_id and bv3.query_id = bv2.query_id where bv2.value/bv2.valuetime>a and c >1 group by bv3.dataset_id,bv3.query_id
)as bv4;

select SUM(ctr) as sum_smaller_than_avg from(
select count(*) as ctr from benchmark_values bv2 join
(select bv1.dataset_id,bv1.query_id,AVG(bv1.value/bv1.valuetime) AS a,COUNT(*) as c from benchmark_values bv1 where bv1.value is not null and bv1.valuetime is not null and bv1.valuetime>=1000 group by bv1.dataset_id,bv1.query_id) as bv3
on bv3.dataset_id=bv2.dataset_id and bv3.query_id = bv2.query_id where bv2.value/bv2.valuetime<a and c >1 group by bv3.dataset_id,bv3.query_id
)as bv4;


select SUM(ctr) as sum_all_for_avg from(
select count(*) as ctr from benchmark_values bv2 join
(select bv1.dataset_id,bv1.query_id,COUNT(*) as c from benchmark_values bv1 where bv1.value is not null and bv1.valuetime is not null and bv1.valuetime>=1000 group by bv1.dataset_id,bv1.query_id) as bv3
on bv3.dataset_id=bv2.dataset_id and bv3.query_id = bv2.query_id where c >1 group by bv3.dataset_id,bv3.query_id
)as bv4;

