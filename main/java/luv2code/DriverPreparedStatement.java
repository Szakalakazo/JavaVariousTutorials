package luv2code;

import java.sql.*;

public class DriverPreparedStatement {
    public static void main(String[] args) {



        try {

            Connection myConnection = DriverManager.getConnection(Path.getUrl(), Path.getUser(), "");
            PreparedStatement myStatement = myConnection.prepareStatement("SELECT * FROM employees WHERE salary > ? and department=?");

            myStatement.setDouble(1, 80000);
            myStatement.setString(2, "Legal");

            ResultSet myResults = myStatement.executeQuery();
            Displayer.display(myResults);


            /*
            Reuse of prepared statement: salary > 25000, department = HR
             */
            System.out.println("\n\nReuse of prepared statement");

            myStatement.setDouble(1, 25000);
            myStatement.setString(2, "HR");

            myResults = myStatement.executeQuery();

            Displayer.display(myResults);


            System.out.println("\n\nDeleting using prepared statement");

            myStatement = myConnection.prepareStatement("DELETE FROM employees WHERE salary > ? AND department=?");
            myStatement.setDouble(1, 50000);
            myStatement.setString(2, "HR");

            myStatement.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
