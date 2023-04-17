drop table evaluate_benchmark_values_minmax;
drop table evaluate_benchmark_values_network_traffic_minmax;
drop table evaluate_cross_score_based_on_network_traffic;
drop table evaluate_cross_score_based_on_intermediate;
drop table evaluate_ranking_based_on_network_traffic;
drop table evaluate_ranking_based_on_intermediate;

create table evaluate_benchmark_values_minmax as select query_id, min(value) as mmin, max(value) as mmax from benchmark_values where value>0 group by query_id;
create table evaluate_benchmark_values_network_traffic_minmax as select query_id, min(value) as mmin, max(value) as mmax from benchmark_values_network_traffic where value>0 group by query_id;

create table evaluate_cross_score_based_on_network_traffic as select oc.optimizer_id, oc.query_id, (case when bv.value<0 then bvmm.mmax*2 else bv.value end) / bvmm.mmin as intermediate_based, (case when bvn.value<0 then bvnmm.mmax*2 else bvn.value end) / bvnmm.mmin as traffic_based from optimizer_choice_network_traffic oc, benchmark_values bv, benchmark_values_network_traffic bvn, evaluate_benchmark_values_minmax bvmm, evaluate_benchmark_values_network_traffic_minmax bvnmm, mapping_query mq where oc.query_id=bv.query_id and oc.join_id =bv.join_id and oc.query_id=bvn.query_id and oc.join_id=bvn.join_id and oc.query_id=bvmm.query_id and oc.query_id=bvnmm.query_id and mq.id=oc.query_id and mq.rng>0.7 and mq.triplepatterns=5;
create table evaluate_ranking_based_on_network_traffic as select optimizer_id, sum(intermediate_based) / count(intermediate_based) as intermediate_on_traffic, sum(traffic_based) / count(traffic_based) as traffic_on_traffic from evaluate_cross_score_based_on_network_traffic group by optimizer_id;

create table evaluate_cross_score_based_on_intermediate as select oc.optimizer_id, oc.query_id, (case when bv.value<0 then bvmm.mmax*2 else bv.value end) / bvmm.mmin as intermediate_based, (case when bvn.value<0 then bvnmm.mmax*2 else bvn.value end) / bvnmm.mmin as traffic_based from optimizer_choice oc, benchmark_values bv, benchmark_values_network_traffic bvn, evaluate_benchmark_values_minmax bvmm, evaluate_benchmark_values_network_traffic_minmax bvnmm, mapping_query mq where oc.query_id=bv.query_id and oc.join_id =bv.join_id and oc.query_id=bvn.query_id and oc.join_id=bvn.join_id and oc.query_id=bvmm.query_id and oc.query_id=bvnmm.query_id and mq.id=oc.query_id and mq.rng>0.7 and mq.triplepatterns=5;
create table evaluate_ranking_based_on_intermediate as select optimizer_id, sum(intermediate_based) / count(intermediate_based) as intermediate_on_intermediate, sum(traffic_based) / count(traffic_based) as traffic_on_intermediate from evaluate_cross_score_based_on_intermediate group by optimizer_id;

select name,intermediate_on_traffic,traffic_on_traffic,intermediate_on_intermediate,traffic_on_intermediate from evaluate_ranking_based_on_network_traffic n, evaluate_ranking_based_on_intermediate i, mapping_optimizer o where o.id=n.optimizer_id and n.optimizer_id=i.optimizer_id and name != "all" and (name not like "%model" or name like "%_1048576.model") INTO OUTFILE '/var/lib/mysql-files/ranking_on_5.csv' FIELDS TERMINATED BY ',' ENCLOSED BY '"' LINES TERMINATED BY '\n';
