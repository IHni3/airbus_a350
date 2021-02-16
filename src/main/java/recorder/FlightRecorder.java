package recorder;

import configuration.Configuration;
import logging.LogEngine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public enum FlightRecorder {
    instance;
    private Connection connection;
    private String driverName = "jdbc:hsqldb:";
    private String username = "sa";
    private String password = "";

    public void startup() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            String databaseURL = driverName + Configuration.instance.databaseFile;
            connection = DriverManager.getConnection(databaseURL, username, password);
            LogEngine.instance.write("Database Connection (isClosed): " + connection.isClosed());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void init() {
        dropTable();
        createTable();
    }

    public synchronized void update(String sqlStatement) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlStatement);
            statement.close();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }

    public void dropTable() {
        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("DROP TABLE data");
        LogEngine.instance.write("sqlStringBuilder = " + sqlStringBuilder.toString());
        update(sqlStringBuilder.toString());
        System.out.println();
    }

    public void createTable() {
        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("CREATE TABLE data ").append(" ( ");
        sqlStringBuilder.append("id BIGINT NOT NULL").append(",");
        sqlStringBuilder.append("className VARCHAR(20) NOT NULL").append(",");
        sqlStringBuilder.append("message VARCHAR(50) NOT NULL").append(",");
        sqlStringBuilder.append("PRIMARY KEY (id)");
        sqlStringBuilder.append(" )");
        update(sqlStringBuilder.toString());
    }

    public String buildSQLStatement(long id, String className, String message) {
        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("INSERT INTO data (id,className,message) VALUES (");
        sqlStringBuilder.append(id).append(",");
        sqlStringBuilder.append("'").append(className).append("'").append(",");
        sqlStringBuilder.append("'").append(message).append("'");
        sqlStringBuilder.append(")");
        LogEngine.instance.write("sqlStringBuilder = " + sqlStringBuilder.toString());
        return sqlStringBuilder.toString();
    }

    public void insert(String className, String message) {
        update(buildSQLStatement(System.nanoTime(), className, message));
    }

    public void shutdown() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("SHUTDOWN");
            connection.close();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }
}