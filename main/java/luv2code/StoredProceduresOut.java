package luv2code;

import java.sql.*;

public class StoredProceduresOut {
    public static void main(String[] args) {

        try {
            Connection myConn = DriverManager.getConnection(Path.getUrl(), Path.getUser(), Path.getPassword());
            String theDepartment = "Engineering";

            CallableStatement myStmt = myConn.prepareCall("{call get_count_for_department(?,?)}");

            myStmt.setString(1, theDepartment);
            myStmt.setInt(2, Types.INTEGER);

            System.out.println("Calling stored procedure. get_count_for_department('" + theDepartment + "', ?");
            myStmt.execute();
            System.out.println("Finished calling stored parameters.");

            int theCount = myStmt.getInt(2);

            System.out.println("\nThe output is " + theCount);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
