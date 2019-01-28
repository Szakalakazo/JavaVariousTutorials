package luv2code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DriverRemover {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/demo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "root";
        String password = "";

        try {


            Connection myConnection = DriverManager.getConnection(url, user, password);
            Statement myStatement = myConnection.createStatement();
            String delete = "DELETE FROM employees WHERE last_name='Brown'";

            myStatement.executeUpdate(delete);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
