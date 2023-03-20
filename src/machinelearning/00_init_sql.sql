

create database IF NOT EXISTS machinelearningbenchmarks;
create user IF NOT EXISTS machinelearningbenchmarks IDENTIFIED BY "machinelearningbenchmarks";
grant all privileges on machinelearningbenchmarks.* TO "machinelearningbenchmarks"@"%";
flush privileges;

use machinelearningbenchmarks;
CREATE TABLE IF NOT EXISTS mapping_dataset (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(512) NOT NULL,
    PRIMARY KEY(id),
    UNIQUE(name)
);
CREATE TABLE IF NOT EXISTS mapping_query (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(512) NOT NULL,
    triplepatterns int NOT NULL,
    rng float,
    dataset_id int NOT NULL,
    PRIMARY KEY(id),
    UNIQUE(name)
);
CREATE TABLE IF NOT EXISTS mapping_join (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(512) NOT NULL,
    triplecount int,
    PRIMARY KEY(id,triplecount),
    UNIQUE(triplecount,name)
)
PARTITION BY LIST (triplecount) (
    PARTITION p0 VALUES IN (0),
    PARTITION p3 VALUES IN (3),
    PARTITION p4 VALUES IN (4),
    PARTITION p5 VALUES IN (5),
    PARTITION p6 VALUES IN (6),
    PARTITION p7 VALUES IN (7),
    PARTITION p8 VALUES IN (8),
    PARTITION p9 VALUES IN (9),
    PARTITION p10 VALUES IN (10),
    PARTITION p11 VALUES IN (11),
    PARTITION p12 VALUES IN (12),
    PARTITION p13 VALUES IN (13),
    PARTITION p14 VALUES IN (14),
    PARTITION p15 VALUES IN (15),
    PARTITION p16 VALUES IN (16),
    PARTITION p17 VALUES IN (17),
    PARTITION p18 VALUES IN (18),
    PARTITION p19 VALUES IN (19),
    PARTITION p20 VALUES IN (20)
);
CREATE TABLE IF NOT EXISTS mapping_optimizer (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(512) NOT NULL,
    PRIMARY KEY(id),
    UNIQUE(name)
);
CREATE TABLE IF NOT EXISTS mapping_dictionary (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(512) NOT NULL,
    PRIMARY KEY(id),
    UNIQUE(name)
);
CREATE TABLE IF NOT EXISTS benchmark_values (
    dataset_id int NOT NULL,
    query_id int NOT NULL,
    join_id int NOT NULL,
    value bigint,
    PRIMARY KEY (dataset_id,query_id,join_id),
    CONSTRAINT dataset_id_const FOREIGN KEY (dataset_id) REFERENCES mapping_dataset (id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT query_id_const FOREIGN KEY (query_id) REFERENCES mapping_query (id) ON DELETE RESTRICT ON UPDATE RESTRICT
);
CREATE TABLE IF NOT EXISTS optimizer_choice (
    dataset_id int NOT NULL,
    query_id int NOT NULL,
    optimizer_id int NOT NULL,
    join_id int,
    PRIMARY KEY (dataset_id,query_id,optimizer_id),
    CONSTRAINT dataset_id_const2 FOREIGN KEY (dataset_id) REFERENCES mapping_dataset (id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT query_id_const2 FOREIGN KEY (query_id) REFERENCES mapping_query (id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT optimizer_id_const2 FOREIGN KEY (optimizer_id) REFERENCES mapping_optimizer (id) ON DELETE RESTRICT ON UPDATE RESTRICT
);
alter table optimizer_choice add foreign key (dataset_id,query_id,join_id) references benchmark_values (dataset_id,query_id,join_id);
INSERT INTO mapping_dataset (name) VALUES ("/mnt/luposdate-testdata/sp2b/1024/complete.n3"), ("/mnt/luposdate-testdata/sp2b/16384/complete.n3"), ("/mnt/luposdate-testdata/sp2b/131072/complete.n3"), ("/mnt/luposdate-testdata/sp2b/1048576/complete.n3"), ("/mnt/luposdate-testdata/sp2b/16777216/complete.n3");

UPDATE mapping_query SET rng=RAND() WHERE rng IS NULL;
update mapping_join set triplecount= ((length(name)-length(replace(name,",",""))+1)/2+1);
create index mapping_join_triple_count on mapping_join(triplecount);
