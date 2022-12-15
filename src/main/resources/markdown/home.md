### 多数据源demo
#### 初始化ck
    clickhouse-wsl
    default
    account
    
    drop database if exists clickhouse_db;
    
    create database if not exists clickhouse_db;
    
    use clickhouse_db;
    
    CREATE TABLE if not exists account(
    user_id String,
    user_data String,
    ck_sign Int8,
    ck_version UInt64
    )
    ENGINE = VersionedCollapsingMergeTree(ck_sign, ck_version)
    partition by user_id
    primary key user_id
    ORDER BY user_id;
    
    INSERT INTO clickhouse_db.account (user_id,user_data,ck_sign,ck_version) VALUES ('1','666',1,1);
#### 初始化MySQL
    mysql-wsl
    mybatisplus_db
    cat
    
    CREATE TABLE `cat` (
    `id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(100) DEFAULT NULL,
    `account` varchar(100) DEFAULT NULL,
    `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=1945829379 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
    
    INSERT INTO mybatisplus_db.cat (id,name,account,password) VALUES
    (1,'tom','123','666'),
    (2,'luci','789','666');

### ClickHouse-CRUD.sql
    
    ------------------------
    
    drop database if exists clickhouse_db;
    
    create database if not exists clickhouse_db;
    
    use clickhouse_db;
    
    CREATE TABLE if not exists account(
    user_id String,
    user_data String,
    ck_sign Int8,
    ck_version UInt64
    )
    ENGINE = VersionedCollapsingMergeTree(ck_sign, ck_version)
    partition by user_id
    primary key user_id
    ORDER BY user_id;
    
    optimize table account final;
    
    alter table account delete where user_id = 1;
    
    SELECT * from account;
    
    INSERT INTO clickhouse_db.account (user_id,user_data,ck_sign,ck_version) VALUES (1,'abc',1,1);
    INSERT INTO clickhouse_db.account (user_id,user_data,ck_sign,ck_version) VALUES (1,'abc',-1,1);
    
    INSERT INTO clickhouse_db.account (user_id,user_data,ck_sign,ck_version) VALUES (2,'def',1,1);
    INSERT INTO clickhouse_db.account (user_id,user_data,ck_sign,ck_version) VALUES (2,'def',-1,1);
    
    INSERT INTO clickhouse_db.account (user_id,user_data,ck_sign,ck_version) VALUES (1,'abcc',1,2);
    INSERT INTO clickhouse_db.account (user_id,user_data,ck_sign,ck_version) VALUES (1,'abcc',-1,2);
    
    ------------------------
    
    select
    user_id ,
    ck_version
    from
    clickhouse_db.account
    group by
    user_id ,
    ck_version
    
    ------------------------
    
    select
    user_id ,
    ck_version
    from
    clickhouse_db.account
    group by
    user_id ,
    ck_version
    having
    sum(ck_sign)>0
    
    ------------------------
    
    select
    a.*
    from
    clickhouse_db.account a
    join (
    select
    user_id ,
    ck_version
    from
    clickhouse_db.account
    group by
    user_id ,
    ck_version
    having
    sum(ck_sign)>0
    ) b
    on
    a.ck_version = b.ck_version and a.user_id = b.user_id
    
    ------------------------
    
    select
    a.*
    from
    clickhouse_db.account a
    join (
    select
    user_id ,
    ck_version
    from
    clickhouse_db.account
    group by
    user_id ,
    ck_version
    having
    sum(ck_sign)>0
    ) b
    on
    a.ck_version = b.ck_version and a.user_id = b.user_id
    where
    user_id = '1561974447068532737'
    limit 1
    
    ------------------------
