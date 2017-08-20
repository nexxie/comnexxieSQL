package Bot.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector implements java.io.Serializable {

    private static final String URL ="jdbc:mysql://localhost:3306/orders";
    private static final String USERNAME ="root";
    private static final String PASSWORD ="Z57105930";

    private Connection connection;

    public DBConnector(){
        try{
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}

