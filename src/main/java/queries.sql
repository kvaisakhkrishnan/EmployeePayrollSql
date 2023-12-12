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

--UC3
mysql> insert into employee_payroll values (1, 'Vaisakhkrishnan K', 10000, '2024-01-08');
Query OK, 1 row affected (0.02 sec)

--UC4
mysql> select * from employee_payroll;
+----+-------------------+--------+------------+
| id | name              | salary | startDate  |
+----+-------------------+--------+------------+
|  1 | Vaisakhkrishnan K |  10000 | 2024-01-08 |
+----+-------------------+--------+------------+
1 row in set (0.01 sec)

--UC5
mysql> select salary from employee_payroll where name = 'Vaisakhkrishnan K';
+--------+
| salary |
+--------+
|  10000 |
+--------+

mysql> select salary from employee_payroll where name = 'Vaisakh';
Empty set (0.00 sec)

 select salary from employee_payroll where name = 'Vaisakhkrishnan K' and startDate BETWEEN CAST('2018-01-01' as date) and CAST('2024-12-30' as date);
+--------+
| salary |
+--------+
|  10000 |
+--------+



--UC6
mysql> alter table employee_payroll add gender char(1);
Query OK, 0 rows affected (0.03 sec)
Records: 0  Duplicates: 0  Warnings: 0


mysql> update employee_payroll set gender = 'M';
Query OK, 1 row affected (0.00 sec)
Rows matched: 1  Changed: 1  Warnings: 0









