-- 邮件
CREATE TABLE mail (
  id INTEGER PRIMARY KEY,
  title VARCHAR2(2048) ,
  content long,
  send_id INTEGER,
  send_date date
) ;
--学生
CREATE TABLE student (
  id INTEGER   PRIMARY KEY,
  name VARCHAR2(255) ,
  age INTEGER 
);