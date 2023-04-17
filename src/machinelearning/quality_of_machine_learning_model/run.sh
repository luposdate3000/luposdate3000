#execute all the *.sql files first

cat /var/lib/mysql-files/ranking_on_3.csv | grep -v model > ranking_on_3.csv
cat /var/lib/mysql-files/ranking_on_3.csv | grep model_3 >> ranking_on_3.csv

cat /var/lib/mysql-files/ranking_on_4.csv | grep -v model > ranking_on_4.csv
cat /var/lib/mysql-files/ranking_on_3.csv | grep model_4 >> ranking_on_4.csv
cat /var/lib/mysql-files/ranking_on_3.csv | grep model_3 | grep -v model_._3 >> ranking_on_4.csv

cat /var/lib/mysql-files/ranking_on_5.csv | grep -v model > ranking_on_5.csv
cat /var/lib/mysql-files/ranking_on_3.csv | grep model_._5 >> ranking_on_5.csv
cat /var/lib/mysql-files/ranking_on_3.csv | grep model_._6 | grep -v model_6 >> ranking_on_5.csv

cat /var/lib/mysql-files/ranking_on_6.csv | grep -v model > ranking_on_6.csv
cat /var/lib/mysql-files/ranking_on_3.csv | grep model_._6 >> ranking_on_6.csv
