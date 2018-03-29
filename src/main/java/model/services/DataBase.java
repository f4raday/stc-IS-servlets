package model.helpers;


import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

public class DataBase {
    private static DataSource dataSource = new DataSource();
    private static final Logger log = Logger.getLogger(DataBase.class.getName());

    static {
        PoolProperties p = new PoolProperties();
        p.setUrl("jdbc:postgresql://localhost:5432/IS-products");
        p.setDriverClassName("org.postgresql.Driver");
        p.setUsername("postgres");
        p.setPassword("postgres");
        p.setJmxEnabled(true);
        p.setTestWhileIdle(false);
        p.setTestOnBorrow(true);
        p.setValidationQuery("SELECT 1");
        p.setTestOnReturn(false);
        p.setValidationInterval(30000);
        p.setTimeBetweenEvictionRunsMillis(30000);
        p.setMaxActive(100);
        p.setInitialSize(10);
        p.setMaxWait(10000);
        p.setRemoveAbandonedTimeout(60);
        p.setMinEvictableIdleTimeMillis(30000);
        p.setMinIdle(10);
        p.setLogAbandoned(true);
        p.setRemoveAbandoned(true);
        p.setJdbcInterceptors(
                "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"+
                        "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");

        dataSource.setPoolProperties(p);
    }

    public static DataSource getDataSource(){ return dataSource;}

    public static Connection getConnection() {
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/IS-products",
                            "postgres", "postgres");
            log.info("Connected to database succesfuly");
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
        }

        return c;
    }
}
