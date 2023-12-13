import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
    String url;
    String user;
    String password;
    Connection connection;
    JDBC(){
        this.url = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
        this.user = "root";
        this.password = "password";
    }
    void getConnection() throws SQLException {
        this.connection = DriverManager.getConnection(this.url, this.user, this.password);
    }
}
