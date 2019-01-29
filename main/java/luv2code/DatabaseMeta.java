package luv2code;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

public class DatabaseMeta {
    public static void main(String[] args) {


        try {

            Connection myCon = DriverManager.getConnection(Path.getUrl(), Path.getUser(), Path.getPassword());
            DatabaseMetaData databaseMetaData = myCon.getMetaData();

            System.out.println("Product name: " + databaseMetaData.getDatabaseProductName());
            ;
            System.out.println("Product version: " + databaseMetaData.getDatabaseProductVersion());
            System.out.println();

            System.out.println("JDBC Driver name: " + databaseMetaData.getDriverName());
            System.out.println("JDBC Driver version: " + databaseMetaData.getDriverVersion());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
