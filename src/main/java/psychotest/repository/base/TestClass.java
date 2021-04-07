package psychotest.repository.base;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class TestClass {
    public static void main(String[] args) throws Exception {
        //Registering the Driver
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        //Getting the connection
        String mysqlUrl = "jdbc:mysql://localhost:3306/mydatabase?useUnicode=true&serverTimezone=UTC";
        Connection con = DriverManager.getConnection(mysqlUrl, "root", "root");
        System.out.println("Connection established......");
        //Retrieving the meta data object
        DatabaseMetaData metaData = con.getMetaData();
        String[] types = {"TABLE"};
        //Retrieving the columns in the database
        ResultSet tables = metaData.getTables(con.getCatalog(), con.getSchema(), "%", types);
        while (tables.next()) {
            System.out.println(tables.getString("TABLE_NAME"));
        }
    }
}
