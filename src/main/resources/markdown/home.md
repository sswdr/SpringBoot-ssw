# Knife4j-Demo
## 学习 ClickHouse
### ck高性能crud测试demo
##### 参考：https://clickhouse.com/docs/en/engines/table-engines/mergetree-family/versionedcollapsingmergetree
#### add()方法
    {
        "userId": "1",
        "userData": "666",
        "ckSign": "1",
        "ckVersion": "1"
    }
#### get()方法
    {
        "userData": "888"
    }
#### update()方法
    {
        "userId": "1",
        "userData": "999",
        "ckSign": "1",
        "ckVersion" :"1"
    }
#### delete()方法
    {
        "userId": "1"
    }
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
    
