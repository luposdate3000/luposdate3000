use machinelearningbenchmarks;
CREATE TABLE IF NOT EXISTS mapping_dataset (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(512) NOT NULL,
    PRIMARY KEY(id),
    UNIQUE(name)
);
CREATE TABLE IF NOT EXISTS mapping_query (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(2048) NOT NULL,
    triplepatterns int NOT NULL,
    rng float,
    PRIMARY KEY(id),
    UNIQUE(name)
);
CREATE TABLE IF NOT EXISTS mapping_join (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(1024) NOT NULL,
    PRIMARY KEY(id),
    UNIQUE(name)
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
    CONSTRAINT query_id_const FOREIGN KEY (query_id) REFERENCES mapping_query (id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT join_id_const FOREIGN KEY (join_id) REFERENCES mapping_join (id) ON DELETE RESTRICT ON UPDATE RESTRICT
);
CREATE TABLE IF NOT EXISTS optimizer_choice (
    dataset_id int NOT NULL,
    query_id int NOT NULL,
    optimizer_id int NOT NULL,
    join_id int NOT NULL,
    PRIMARY KEY (dataset_id,query_id,optimizer_id),
    CONSTRAINT dataset_id_const2 FOREIGN KEY (dataset_id) REFERENCES mapping_dataset (id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT query_id_const2 FOREIGN KEY (query_id) REFERENCES mapping_query (id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT optimizer_id_const2 FOREIGN KEY (optimizer_id) REFERENCES mapping_optimizer (id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT join_id_const2 FOREIGN KEY (join_id) REFERENCES mapping_join (id) ON DELETE RESTRICT ON UPDATE RESTRICT
);
INSERT INTO mapping_dataset (name) VALUES ("/mnt/luposdate-testdata/sp2b/1024/complete.n3"), ("/mnt/luposdate-testdata/sp2b/16384/complete.n3"), ("/mnt/luposdate-testdata/sp2b/131072/complete.n3"), ("/mnt/luposdate-testdata/sp2b/1048576/complete.n3"), ("/mnt/luposdate-testdata/sp2b/16777216/complete.n3");

UPDATE mapping_query SET rng=RAND() WHERE rng IS NULL;
