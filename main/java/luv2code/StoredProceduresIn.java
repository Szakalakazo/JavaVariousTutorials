package luv2code;

import java.sql.*;

public class StoredProceduresIn {
    public static void main(String[] args) throws SQLException {

        Connection myConn = null;
        PreparedStatement myStatement = null;


        try {

            myConn = DriverManager.getConnection(Path.getUrl(), Path.getUser(), Path.getPassword());
            int increaseAmount = 1000;
            String department = "Engineering";

            //Show salaries BEFORE
            System.out.println("Salaries BEFORE\n");
            showSalaries(myConn, department);

            // Preparing statement
            myStatement = myConn.prepareCall("{call increase_salaries_for_department(?, ?)}");

            //Set parameter values
            myStatement.setString(1, department);
            myStatement.setDouble(2, increaseAmount);

            //Call stored procedures
            System.out.println("\n\nCalling stored procedure.  increase_salaries_for_department('" + department + "', " + increaseAmount + ")");
            myStatement.execute();
            System.out.println("Finished calling stored procedure");

            //Show salaries AFTER
            System.out.println("\n\nSalaries AFTER");
            showSalaries(myConn, department);

            myConn.close();


        } catch (Exception exc) {
            exc.printStackTrace();
        }

    }

    public static void showSalaries(Connection myConn, String theDepartment) throws SQLException {
        PreparedStatement myStmt = null;
        ResultSet myRes = null;

        try {

            myStmt = myConn.prepareStatement("select * from employees where department=?");
            myStmt.setString(1, theDepartment);
            myRes = myStmt.executeQuery();

            while (myRes.next()) {
                String lastName = myRes.getString("last_name");
                String firstName = myRes.getString("first_name");
                double salary = myRes.getDouble("salary");
                String department = myRes.getString("department");

                System.out.printf("%s, %s, %s, %.2f\n", lastName, firstName, department, salary);

            }

        } catch (Exception exc) {
            exc.printStackTrace();
//        } finally {
//            close(myStmt, myRes);
        }
    }

    private static void close(Connection myConn, Statement myStmt, ResultSet myRs) throws SQLException {

        if (myRs != null) {
            myRs.close();
        }

        if (myStmt != null) {
            myStmt.close();
        }

        if (myConn != null) {
            myConn.close();
        }
    }

//    private static void close(Statement myStmt, ResultSet myRs) throws SQLException {
//
//        close(null, myStmt, myRs);
//    }
}


