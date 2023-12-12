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


--UC7
mysql> select sum(salary), avg(salary), min(salary), max(salary), count(*) from employee_payroll group by gender;
+-------------+-------------+-------------+-------------+----------+
| sum(salary) | avg(salary) | min(salary) | max(salary) | count(*) |
+-------------+-------------+-------------+-------------+----------+
|       10000 |  10000.0000 |       10000 |       10000 |        1 |
+-------------+-------------+-------------+-------------+----------+
1 row in set (0.01 sec)


--UC8
mysql> alter table employee_payroll
    -> add phone varchar(10),
    -> add address varchar(25) default "DEFAULT ADDRESS",
    -> add department varchar(25) not null;
Query OK, 0 rows affected (0.01 sec)
Records: 0  Duplicates: 0  Warnings: 0

--UC9
 alter table employee_payroll
    -> add basicPay int,
    -> add deductions int,
    -> add taxablePay int,
    -> add incomeTax int,
    -> add netPay int;

mysql> describe employee_payroll;
+------------+-------------+------+-----+-----------------+-------+
| Field      | Type        | Null | Key | Default         | Extra |
+------------+-------------+------+-----+-----------------+-------+
| id         | int         | NO   | PRI | NULL            |       |
| name       | varchar(25) | YES  |     | NULL            |       |
| salary     | int         | YES  |     | NULL            |       |
| startDate  | date        | YES  |     | NULL            |       |
| gender     | char(1)     | YES  |     | NULL            |       |
| phone      | varchar(10) | YES  |     | NULL            |       |
| address    | varchar(25) | YES  |     | DEFAULT ADDRESS |       |
| department | varchar(25) | NO   |     | NULL            |       |
| basicPay   | int         | YES  |     | NULL            |       |
| deductions | int         | YES  |     | NULL            |       |
| taxablePay | int         | YES  |     | NULL            |       |
| incomeTax  | int         | YES  |     | NULL            |       |
| netPay     | int         | YES  |     | NULL            |       |
+------------+-------------+------+-----+-----------------+-------+
13 rows in set (0.01 sec)

--UC10
mysql> insert into employee_payroll values (2, 'Terissa', 10000, '2020-10-10', 'F', '1234567890', 'Address','Sales', 7000, 200, 1000, 200, 9000);
Query OK, 1 row affected (0.01 sec)
mysql> insert into employee_payroll values (3, 'Terissa', 10000, '2020-10-10', 'F', '1234567890', 'Address','Marketing', 7000, 200, 1000, 200, 9000);
Query OK, 1 row affected (0.01 sec)

mysql> select * from employee_payroll;
+----+-------------------+--------+------------+--------+------------+-----------------+------------+----------+------------+------------+-----------+--------+
| id | name              | salary | startDate  | gender | phone      | address         | department | basicPay | deductions | taxablePay | incomeTax | netPay |
+----+-------------------+--------+------------+--------+------------+-----------------+------------+----------+------------+------------+-----------+--------+
|  1 | Vaisakhkrishnan K |  10000 | 2024-01-08 | M      | NULL       | DEFAULT ADDRESS |            |     NULL |       NULL |       NULL |      NULL |   NULL |
|  2 | Terissa           |  10000 | 2020-10-10 | F      | 1234567890 | Address         | Sales      |     7000 |        200 |       1000 |       200 |   9000 |
|  3 | Terissa           |  10000 | 2020-10-10 | F      | 1234567890 | Address         | Marketing  |     7000 |        200 |       1000 |       200 |   9000 |
+----+-------------------+--------+------------+--------+------------+-----------------+------------+----------+------------+------------+-----------+--------+
3 rows in set (0.01 sec)












