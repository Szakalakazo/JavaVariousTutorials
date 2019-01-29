package luv2code;

import java.sql.*;
import java.util.Scanner;

public class Transactions {
    public static void main(String[] args) {

        Connection myCon = null;
        Statement myStmt = null;

        try {

            myCon = DriverManager.getConnection(Path.getUrl(), Path.getUser(), Path.getPassword());

            // Turn auto-commit off
            myCon.setAutoCommit(false);

            // Salaries BEFORE
            showSalaries(myCon, "HR");
            showSalaries(myCon, "Engineering");

            // Transaction step 1: Delete all HR employees
            myStmt = myCon.createStatement();
            myStmt.executeUpdate("DELETE FROM employees WHERE department='HR'");

            // Transaction step 2: Set salaries to 30000 for all Engineering
            myStmt.executeUpdate("UPDATE employees SET salary=30000 WHERE department='Engineering'");

            System.out.println("\n\n>> Transaction steps are ready.\n");

            if(askToProceed()){
                myCon.commit();
                System.out.println("\n>> Transaction COMMITTED\n");
            } else {
                myCon.rollback();
                System.out.println("\n>> Transaction ROLLED BACK\n");
            }

            // Salaries AFTER
            showSalaries(myCon, "Engineering");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void showSalaries(Connection myCon, String department) {
        PreparedStatement myStmt = null;
        ResultSet myRes = null;

        try {

            myStmt = myCon.prepareStatement("SELECT * FROM employees WHERE department='" + department + "'");
            myRes = myStmt.executeQuery();

            System.out.println("\n\nDEPARTMENT: " + department);
            while (myRes.next()) {
                String lastName = myRes.getString("last_name");
                String firstName = myRes.getString("first_name");
                double salary = myRes.getDouble("salary");


                System.out.printf("%s, %s, %s, %.2f\n", lastName, firstName, department, salary);
            }

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    private static boolean askToProceed() {
        System.out.println("Do you want to proceed? [ok/no]");
        Scanner in = new Scanner(System.in);
        String answer = in.nextLine();

        while (!(answer.toLowerCase().equals("ok") || answer.toLowerCase().equals("no"))) {
            System.out.println("Incorrect input, please enter ok or no");
            answer = in.nextLine();
        }
        return answer.toLowerCase().equals("ok");
    }
}



