import java.sql.*;

public class Conn {
    public Connection c;
    public Statement s;

    public Conn() {
        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to database (adjust username/password/database)
            c = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/universitymanagementsystem",
                    "root",
                    "Nisha@709133"
            );

            s = c.createStatement();
            System.out.println("Database connected successfully.");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("MySQL Driver not found. Add the MySQL connector JAR to classpath.");
            cnfe.printStackTrace();
        } catch (SQLException se) {
            System.out.println("Database connection failed.");
            se.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Conn();
    }
}
