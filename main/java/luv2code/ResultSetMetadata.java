package luv2code;

import java.sql.*;

public class ResultSetMetadata {
    public static void main(String[] args) {

        Connection myCon = null;
        Statement myStmt = null;
        ResultSet myRes = null;


        try {

            myCon = DriverManager.getConnection(Path.getUrl(), Path.getUser(), Path.getPassword());
            myStmt = myCon.createStatement();

//            myRes = myStmt.executeQuery("SELECT id, last_name, first_name, salary FROM employees");
            myRes = myStmt.executeQuery("SELECT * FROM employees");

            ResultSetMetaData reSetMetaData = myRes.getMetaData();

            int columnCount = reSetMetaData.getColumnCount();
            System.out.println("Column count: " + columnCount + "\n");


            for (int i = 1; i <= columnCount; i++) {
                System.out.println("\nColumn number  \t\t" + i);
                System.out.println("Column name: \t\t" + reSetMetaData.getColumnName(i));
                System.out.println("Column type name: \t" + reSetMetaData.getColumnTypeName(i));
                System.out.println("Is Nullable: \t\t" + reSetMetaData.isNullable(i));
                System.out.println("Is Auto Increment: \t" + reSetMetaData.isAutoIncrement(i));
            }


        } catch (Exception exc) {
            exc.printStackTrace();
        }

    }
}
