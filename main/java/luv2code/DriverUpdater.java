package luv2code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DriverUpdater {
    public static void main(String[] args) {


        String url = "jdbc:mysql://localhost:3306/demo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "root";
        String password = "";

        try {

            Connection myCon = DriverManager.getConnection(url, user, password);
            Statement myStmt = myCon.createStatement();

            String sql1 = "INSERT INTO employees " + " (last_name, first_name, email)" + " values('Brown','David', 'david.brown@foo.com')";
            String sql2 = "UPDATE employees SET email='yyyyy@gmail.com' WHERE last_name='Brown'";

            //myStmt.executeUpdate(sql1);
            myStmt.executeUpdate(sql2);

            System.out.println("Insert completed");


        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
