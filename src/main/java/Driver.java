import java.sql.*;

public class Driver {
    public static void main(String[] args){
        try{
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
}
