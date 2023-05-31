#execute all the *.sql files first

echo "\"a\",intermediate on traffic,traffic on traffic,intermediate on intermediate,traffic on intermediate" > ranking_on_3.csv
echo "\"a\",intermediate on traffic,traffic on traffic,intermediate on intermediate,traffic on intermediate" > ranking_on_4.csv
echo "\"a\",intermediate on traffic,traffic on traffic,intermediate on intermediate,traffic on intermediate" > ranking_on_5.csv
echo "\"a\",intermediate on traffic,traffic on traffic,intermediate on intermediate,traffic on intermediate" > ranking_on_6.csv
cat /var/lib/mysql-files/ranking_on_3.csv | grep -v model >> ranking_on_3.csv
cat /var/lib/mysql-files/ranking_on_3.csv | grep model_3 >> ranking_on_3.csv

cat /var/lib/mysql-files/ranking_on_4.csv | grep -v model >> ranking_on_4.csv
cat /var/lib/mysql-files/ranking_on_3.csv | grep model_4 >> ranking_on_4.csv
cat /var/lib/mysql-files/ranking_on_3.csv | grep model_3 | grep -v model_._3 >> ranking_on_4.csv

cat /var/lib/mysql-files/ranking_on_5.csv | grep -v model >> ranking_on_5.csv
cat /var/lib/mysql-files/ranking_on_3.csv | grep model_._5 >> ranking_on_5.csv
cat /var/lib/mysql-files/ranking_on_3.csv | grep model_._6 | grep -v model_6 >> ranking_on_5.csv

cat /var/lib/mysql-files/ranking_on_6.csv | grep -v model >> ranking_on_6.csv
cat /var/lib/mysql-files/ranking_on_3.csv | grep model_._6 >> ranking_on_6.csv



cat ranking_on_3.csv | sed "s/\"luposdate3000\",/luposdate3000 greedy,/g" | sed "s/\"luposdate3000_dynamic_programming\"/luposdate3000 dp/g" | sed "s/\"luposdate3000_dynamic_programming_no_cluster\"/luposdate3000 dp nc/g" | sed "s/\"model/model/g"|sed "s/model\"/model/g" | sort | sed "s/_20_0.7_1048576.model//g" | sed "s/_/-/g" | sed "s/model-/model trained on /g" |csvtool transpose - | sed "s/intermediate on/I on/g" | sed "s/traffic on/T on/g"  | sed "s/traffic,/T,/g" | sed "s/intermediate,/I,/g" > ranking_on_3_t.csv
cat ranking_on_4.csv | sed "s/\"luposdate3000\",/luposdate3000 greedy,/g" | sed "s/\"luposdate3000_dynamic_programming\"/luposdate3000 dp/g" | sed "s/\"luposdate3000_dynamic_programming_no_cluster\"/luposdate3000 dp nc/g" | sed "s/\"model/model/g"|sed "s/model\"/model/g" | sort | sed "s/_20_0.7_1048576.model//g" | sed "s/_/-/g" | sed "s/model-/model trained on /g" |csvtool transpose - | sed "s/intermediate on/I on/g" | sed "s/traffic on/T on/g"  | sed "s/traffic,/T,/g" | sed "s/intermediate,/I,/g" > ranking_on_4_t.csv
cat ranking_on_5.csv | sed "s/\"luposdate3000\",/luposdate3000 greedy,/g" | sed "s/\"luposdate3000_dynamic_programming\"/luposdate3000 dp/g" | sed "s/\"luposdate3000_dynamic_programming_no_cluster\"/luposdate3000 dp nc/g" | sed "s/\"model/model/g"|sed "s/model\"/model/g" | sort | sed "s/_20_0.7_1048576.model//g" | sed "s/_/-/g" | sed "s/model-/model trained on /g" |csvtool transpose - | sed "s/intermediate on/I on/g" | sed "s/traffic on/T on/g"  | sed "s/traffic,/T,/g" | sed "s/intermediate,/I,/g" > ranking_on_5_t.csv
cat ranking_on_6.csv | sed "s/\"luposdate3000\",/luposdate3000 greedy,/g" | sed "s/\"luposdate3000_dynamic_programming\"/luposdate3000 dp/g" | sed "s/\"luposdate3000_dynamic_programming_no_cluster\"/luposdate3000 dp nc/g" | sed "s/\"model/model/g"|sed "s/model\"/model/g" | sort | sed "s/_20_0.7_1048576.model//g" | sed "s/_/-/g" | sed "s/model-/model trained on /g" |csvtool transpose - | sed "s/intermediate on/I on/g" | sed "s/traffic on/T on/g"  | sed "s/traffic,/T,/g" | sed "s/intermediate,/I,/g" > ranking_on_6_t.csv
cat ranking_on_3.csv ranking_on_4.csv ranking_on_5.csv ranking_on_6.csv | sed "s/\"luposdate3000\",/luposdate3000 greedy,/g" | sed "s/\"luposdate3000_dynamic_programming\"/luposdate3000 dp/g" | sed "s/\"luposdate3000_dynamic_programming_no_cluster\"/luposdate3000 dp nc/g" | sed "s/\"model/model/g"|sed "s/model\"/model/g" | sort | sed "s/,.*/,0,0,0,0/g" | uniq | sed "s/_20_0.7_1048576.model//g" | sed "s/_/-/g" | sed "s/model-/model trained on /g" |csvtool transpose - | sed "s/intermediate on/I on/g" | sed "s/traffic on/T on/g"  | sed "s/traffic,/T,/g" | sed "s/intermediate,/I,/g" | sed "s/^0//g" > ranking_on_0_t.csv


for f in *plot; do ./$f ; done


