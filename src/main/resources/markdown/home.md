### MyBatisPlus demo
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