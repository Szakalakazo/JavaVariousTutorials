package luv2code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Driver {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/demo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "root";
        String password = "";

        try {
            // 1. Get a connection to database.
            Connection myConnection = DriverManager.getConnection(url, user, password);

            // 2. Create a statement
            Statement myStatement = myConnection.createStatement();

            // 3. Execute SQL query
            ResultSet resultSet = myStatement.executeQuery("select * from employees");

            // 4. Process the results
            while (resultSet.next()) {
                System.out.println(resultSet.getString("last_name") + ", " + resultSet.getString("first_name"));
            }

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
