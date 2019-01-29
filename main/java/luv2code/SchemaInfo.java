package luv2code;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class SchemaInfo {
    public static void main(String[] args) {

        String catalog = null;
        String schemaPattern = null;
        String tableNamePattern = null;
        String columnNamePattern = null;
        String[] types = null;

        try {
            // 1. Get a connection
            Connection myCon = DriverManager.getConnection(Path.getUrl(), Path.getUser(), Path.getPassword());

            // 2. Get metadata
            DatabaseMetaData databaseMetaData = myCon.getMetaData();

            // 3. Get list of tables
            System.out.println("List of Tables");
            System.out.println("--------------");

            ResultSet myRes = databaseMetaData.getTables(catalog, schemaPattern, "employees", types);

            while (myRes.next()) {
                System.out.println(myRes.getString("TABLE_NAME"));
            }

            // 4. Get list of COLUMNS
            System.out.println("\n\nList of Columns");
            System.out.println("--------------");

            myRes = databaseMetaData.getColumns(catalog, schemaPattern, "employees", columnNamePattern);

            while (myRes.next()){
                System.out.println(myRes.getString("COLUMN_NAME"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
