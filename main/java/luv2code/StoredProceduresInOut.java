package luv2code;

import java.sql.*;

public class StoredProceduresInOut {
    public static void main(String[] args) throws SQLException {

        Connection myConnection = null;
        CallableStatement myStatement = null;

        try {
            String department = "HR";

            myConnection = DriverManager.getConnection(Path.getUrl(), Path.getUser(), Path.getPassword());
            myStatement = myConnection.prepareCall("{call greet_the_department(?)}");


            //Set the parameter
            myStatement.registerOutParameter(1, Types.VARCHAR);
            myStatement.setString(1, department);

            //Call stored procedures
            System.out.println("Calling stored procedure. greet_the_department('" + department + "')");
            myStatement.execute();
            System.out.println("Finished calling stored procedure");

            // Get the value of the INOUT parameter
            String theResult = myStatement.getString(1);
            System.out.println("\nThe result is " + theResult);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
