#execute all the *.sql files first

echo ",intermediate on traffic,traffic on traffic,intermediate on intermediate,traffic on intermediate" > ranking_on_3.csv
echo ",intermediate on traffic,traffic on traffic,intermediate on intermediate,traffic on intermediate" > ranking_on_4.csv
echo ",intermediate on traffic,traffic on traffic,intermediate on intermediate,traffic on intermediate" > ranking_on_5.csv
echo ",intermediate on traffic,traffic on traffic,intermediate on intermediate,traffic on intermediate" > ranking_on_6.csv
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

cat ranking_on_3.csv | sed "s/_20_0.7_1048576.model//g" | sed "s/_/-/g" | sed "s/model-/model trained on /g" |csvtool transpose - > ranking_on_3_t.csv
cat ranking_on_4.csv | sed "s/_20_0.7_1048576.model//g" | sed "s/_/-/g" | sed "s/model-/model trained on /g" |csvtool transpose - > ranking_on_4_t.csv
cat ranking_on_5.csv | sed "s/_20_0.7_1048576.model//g" | sed "s/_/-/g" | sed "s/model-/model trained on /g" |csvtool transpose - > ranking_on_5_t.csv
cat ranking_on_6.csv | sed "s/_20_0.7_1048576.model//g" | sed "s/_/-/g" | sed "s/model-/model trained on /g" |csvtool transpose - > ranking_on_6_t.csv
