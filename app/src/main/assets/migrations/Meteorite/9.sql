--文件中的数字代表数据库的版本号,文件中的语句结束以分号结尾.
--文件存放规则 assets/migrations/{DATABASE_NAME}/{versionNumber}.sql
ALTER TABLE USER ADD COLUMN status INTEGER;