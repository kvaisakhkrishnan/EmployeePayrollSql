import java.sql.*;
import java.util.Scanner;
import java.util.Stack;

public class Driver {
    public static void main(String[] args){
        try{
            getUserData();
            updateSalary();
            System.out.println("After Update");
            getUserData();
            //payrollByName();
            //getDataByDate();
            analysis();
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
        PreparedStatement statement = jdbc.connection.prepareStatement("update employee_payroll set salary = ? where id = 2 or id = 3;");
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

}
