package database;

/**
 * Created by Gulzar Safar on 9/25/2020
 */

public class DatabaseConnection {


    private final String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
    private final String username = "c##gulzarsafar";
    private final String password = "helloOracle";
    private final String jdbcUrl = "jdbc:oracle:thin:@//localhost:1521/orcl.docker.internal";

    public DatabaseConnection() {

    }

    public String getJdbcDriver() {
        return jdbcDriver;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }
}
