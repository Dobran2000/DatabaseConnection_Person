package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static final class SingletonHolder
    {
        public static final ConnectionManager INSTANCE=new ConnectionManager();

    }
    private Connection connection;
    private ConnectionManager()
    {
       String url="jdbc:mysql://localhost/javra";
        try {
            connection= DriverManager.getConnection(url,"root","");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static ConnectionManager getInstance()
    {
        return SingletonHolder.INSTANCE;
    }
}
