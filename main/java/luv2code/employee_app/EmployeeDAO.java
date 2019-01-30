package luv2code.employee_app;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EmployeeDAO {

    private Connection myCon;

    public EmployeeDAO() throws Exception {

        //get db connection
        Properties props = new Properties();
        props.load(new FileInputStream("src/main/resources/demo.properties"));
        String user = props.getProperty("user");
        String password = props.getProperty("password");
        String dburl = props.getProperty("dburl");

        // connect to database
        myCon = DriverManager.getConnection(dburl, user, password);
        System.out.println("DB connection successfully to " + dburl);

    }

    public List<Employee> getAllEmployee() throws Exception {

        List<Employee> list = new ArrayList<>();

        Statement myStmt = null;
        ResultSet myRes = null;

        try {
            myStmt = myCon.createStatement();
            String query = "SELECT * FROM employees";

            myRes = myStmt.executeQuery(query);

            while (myRes.next()) {
                Employee tempEmployee = convertRowToEmployee(myRes);
                list.add(tempEmployee);
            }

            return list;

        } finally {
            close(myStmt, myRes);
        }
    }

    public List<Employee> searchForEmployee(String lastName) throws Exception {
        List<Employee> list = new ArrayList<>();
        PreparedStatement myStmt = null;
        ResultSet myRes = null;

        try {
            lastName += "%";
            myStmt = myCon.prepareStatement("SELECT * FROM employees WHERE last_name like ?");
            myStmt.setString(1, lastName);
            myRes = myStmt.executeQuery();

            while (myRes.next()) {
                Employee tempEmployee = convertRowToEmployee(myRes);
                list.add(tempEmployee);
            }

            return list;

        } finally {
            close(myStmt, myRes);
        }
    }


    private Employee convertRowToEmployee(ResultSet myRes) throws SQLException {

        int id = myRes.getInt("id");
        String lastName = myRes.getString("last_name");
        String firstName = myRes.getString("first_name");
        String email = myRes.getString("email");
        BigDecimal salary = myRes.getBigDecimal("salary");

        return new Employee(id, lastName, firstName, email, salary);
    }


    private static void close(Connection myCon, Statement myStmt, ResultSet myRes) throws SQLException {

        if (myRes != null) {
            myRes.close();
        }

        if (myStmt != null) {

        }

        if (myCon != null) {
            myCon.close();
        }
    }

    private void close(Statement myStmt, ResultSet myRes) throws SQLException {
        close(null, myStmt, myRes);
    }


    public static void main(String[] args) throws Exception {
        EmployeeDAO dao = new EmployeeDAO();
        System.out.println(dao.searchForEmployee("thom"));
        System.out.println(dao.getAllEmployee());

    }

}

