use machinelearningbenchmarks;
drop table cache1;
create table cache1 as select dataset_id,query_id,min(value)as min,max(value)as max from benchmark_values group by dataset_id,query_id;

delete ignore bv1 from benchmark_values bv1 INNER JOIN ( select bv.dataset_id,bv.query_id,bv.join_id from benchmark_values bv,cache1 c where bv.dataset_id=c.dataset_id and bv.query_id=c.query_id and bv.value >c.min and bv.value <c.max) bv2 on bv1.dataset_id=bv2.dataset_id and bv1.query_id=bv2.query_id and bv1.join_id=bv2.join_id;
