package luv2code;

import java.sql.*;

public class StoredProceduresReturningSets {
    public static void main(String[] args) {


        try {
            Connection myConn = DriverManager.getConnection(Path.getUrl(), Path.getUser(), Path.getPassword());
            String theDepartment = "Engineering";

            CallableStatement myStmt = myConn.prepareCall("{call get_employees_for_department(?)}");
            myStmt.setString(1, theDepartment);

            System.out.println("Calling stored procedure. get_employees_for_department('" + theDepartment + "', ?");
            myStmt.execute();
            System.out.println("Finished calling stored parameters.");

            ResultSet myRes = myStmt.getResultSet();





        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
