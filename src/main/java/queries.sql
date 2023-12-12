--UC1
mysql> create database payroll_service;
Query OK, 1 row affected (0.01 sec)

mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| AddressBook        |
| information_schema |
| mysql              |
| payroll_service    |
| performance_schema |
| sys                |
+--------------------+

mysql> use payroll_service;
Database changed

--UC2
mysql> create table employee_payroll (id int primary key, name varchar(25), salary int, startDate date);
Query OK, 0 rows affected (0.02 sec)





