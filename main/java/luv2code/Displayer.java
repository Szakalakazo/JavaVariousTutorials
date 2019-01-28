package luv2code;

import java.sql.ResultSet;

public class Displayer {

    public static void display(ResultSet resultSet) {

        try {

            while (resultSet.next()) {
                System.out.println(resultSet.getString("last_name") + ", "
                        + resultSet.getString("first_name")
                        + ", "
                        + resultSet.getString("email")
                        + ", "
                        + resultSet.getString("department")
                        + ", "
                        + resultSet.getString("salary"));                ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
