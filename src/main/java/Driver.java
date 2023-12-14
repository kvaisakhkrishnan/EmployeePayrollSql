import java.sql.*;
import java.util.Scanner;
import java.util.Stack;

public class Driver {
    public static void main(String[] args){
        try{
            //getUserData();
            //updateSalary();
            //System.out.println("After Update");
            //getUserData();
            //payrollByName();
            //getDataByDate();
            //analysis();
            //addUserData();
            //getUserData();
            deleteUserData();
            getUserData();
        }
        catch(SQLException err){
            err.printStackTrace();
        }
    }

    public static void getUserData() throws SQLException{
        JDBC jdbc = new JDBC();
        jdbc.getConnection();
        Statement statement = jdbc.connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select id, name, salary from employee_payroll");
        while(resultSet.next()){
            System.out.println("=============================");
            System.out.println("ID: [" + resultSet.getString("id") + "]");
            System.out.println("Name: [" + resultSet.getString("name") + "]");
            System.out.println("Salary: [" + resultSet.getString("salary") + "]");
            System.out.println("=============================");
        }

    }

    public static void updateSalary() throws SQLException {
        JDBC jdbc = new JDBC();
        jdbc.getConnection();
        PreparedStatement statement = jdbc.connection.prepareStatement("update payroll_detail set salary = ? where id = 2 or id = 3;");
        statement.setInt(1, 3000000);
        statement.executeUpdate();
    }

    public static void payrollByName() throws SQLException{
        System.out.println("Enter Name: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();
        JDBC jdbc = new JDBC();
        jdbc.getConnection();
        PreparedStatement statement = jdbc.connection.prepareStatement("select salary as payroll, name from employee_payroll where name = ?");
        statement.setString(1, name);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            System.out.println("Name: [" + resultSet.getString("name") + "], Payroll: [" + resultSet.getString("payroll") + "]");
        }
    }

    public static void getDataByDate() throws SQLException {
        System.out.println("Enter Starting Date: ");
        Scanner scanner = new Scanner(System.in);
        String start = scanner.next();
        System.out.println("Enter Ending Date: ");
        String end = scanner.next();
        JDBC jdbc = new JDBC();
        jdbc.getConnection();
        PreparedStatement statement = jdbc.connection.prepareStatement("select * from employee_payroll where startDate > ? and startDate <= ?");
        statement.setString(1, start);
        statement.setString(2, end);
        ResultSet result = statement.executeQuery();
        while (result.next()){
            System.out.println("Employee Id: " + result.getString("id") + ", Name: " + result.getString("name"));
        }
    }

    public static void analysis() throws SQLException {
        JDBC jdbc = new JDBC();
        jdbc.getConnection();
        Statement statement = jdbc.connection.createStatement();
        ResultSet result = statement.executeQuery("select sum(salary) as sum, avg(salary) as avg, max(salary) as max, min(salary)  as min, gender from employee_payroll group by gender");
        while (result.next()){
            System.out.println("Gender: " + result.getString("gender") + ", sum: " + result.getString("sum") + ", avg: " + result.getString("avg") + ", max: " + result.getString("max") + ", min: " + result.getString("min"));
        }
    }

    public static void addUserData(){
        System.out.println("Enter Employee Id: ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        System.out.println("Enter Name: ");
        String name = scanner.next();
        System.out.println("Enter Salary: ");
        int salary = scanner.nextInt();
        System.out.println("Enter Start Date: ");
        String startDate = scanner.next();
        System.out.println("Enter Gender: ");
        String gender = scanner.next();
        System.out.println("Enter Phonenumber: ");
        String phone = scanner.next();
        System.out.println("Enter Address: ");
        String address = scanner.next();
        System.out.println("Enter Department: ");
        String dept = scanner.next();
        try{
            JDBC jdbc = new JDBC();
            jdbc.getConnection();
            jdbc.connection.setAutoCommit(false);
            PreparedStatement statement = jdbc.connection.prepareStatement("insert into employee_payroll (id, name, salary, startDate, gender, phone, address, department) values (?,?,?,?,?,?,?,?)");
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setInt(3, salary);
            statement.setString(4, startDate);
            statement.setString(5, gender);
            statement.setString(6, phone);
            statement.setString(7, address);
            statement.setString(8, dept);
            statement.executeUpdate();
            int deductions = (int) (0.2 * salary);
            int taxablePay = salary - deductions;
            int tax = (int) (0.1 * taxablePay);
            int netpay = salary - tax;
            PreparedStatement statement2 = jdbc.connection.prepareStatement("insert into payroll_detail values (?,?,?,?,?,?)");
            statement2.setInt(1, id);
            statement2.setInt(2, salary);
            statement2.setInt(3, deductions);
            statement2.setInt(4, taxablePay);
            statement2.setInt(5, tax);
            statement2.setInt(6, netpay);
            statement2.executeUpdate();
            jdbc.connection.commit();
            jdbc.connection.setAutoCommit(true);
        }catch(SQLException err){
            err.printStackTrace();
        }
    }

    public static void deleteUserData(){
        System.out.println("Enter Employee Id: ");
        int id = new Scanner(System.in).nextInt();
        try{
            JDBC jdbc = new JDBC();
            jdbc.getConnection();
            jdbc.connection.setAutoCommit(false);
            PreparedStatement statement = jdbc.connection.prepareStatement("delete from employee_payroll where id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
            PreparedStatement statement1 = jdbc.connection.prepareStatement("delete from payroll_detail where id = ?");
            statement1.setInt(1, id);
            statement1.executeUpdate();
            jdbc.connection.commit();
            jdbc.connection.setAutoCommit(true);
        }
        catch(SQLException err){
            err.printStackTrace();
        }

    }



}
