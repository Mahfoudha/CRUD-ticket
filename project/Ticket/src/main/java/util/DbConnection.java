package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbConnection {

    private static DbConnection instance;
    private Connection connection;
    private String url = "jdbc:postgresql://localhost:5432/";
    private String bdd="tickets";
    private String username = "postgres";
    private String password = "Fouda";

    private DbConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(url+bdd, username, password);
            System.out.println("Connection à la base de donnnée '"+bdd+"' est etablie.");
        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC Absent : " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DbConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DbConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DbConnection();
        }
        return instance;
    }

    public static void main(String[] args) {
        try {
            DbConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}