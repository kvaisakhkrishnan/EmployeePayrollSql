import java.sql.Connection;
import java.sql.SQLException;

public class Driver {
    public static void main(String[] args) throws SQLException {
        JDBC connection = new JDBC();
        connection.getConnection();
    }
}
