package dao;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import exception.DbException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public final class DbManager {

    private static DbManager instance;

    private MysqlDataSource ds;


    public static DbManager getInstance() {
        if (instance == null) {
            instance = new DbManager();
        }
        return instance;
    }

    private DbManager() {
        Properties properties = new Properties();
        try {
            InputStream iS = getClass().getClassLoader().getResourceAsStream("db.properties");
            properties.load(iS);
            ds = new MysqlConnectionPoolDataSource();
            ds.setURL(properties.getProperty("mysql.url"));
            ds.setUser(properties.getProperty("mysql.username"));
            ds.setPassword(properties.getProperty("mysql.password"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Connection getConnection() throws DbException {
        Connection connection = null;
        try {
            connection = ds.getConnection();
        } catch (SQLException ex) {
            throw new DbException("Cannot obtain connection", ex);
        }
        return connection;
    }


}
