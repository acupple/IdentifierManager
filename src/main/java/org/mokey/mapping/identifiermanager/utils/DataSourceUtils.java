package org.mokey.mapping.identifiermanager.utils;

import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

public class DataSourceUtils {
	private static DataSource datasource = null;
	private static Properties conf = null;
	static {
		try {
			URL url = Thread.currentThread().getContextClassLoader().getResource("conf.properties");
			InputStream in = url.openStream();
			conf = new Properties();
			conf.load(in);
			PoolProperties p = new PoolProperties();
			p.setUrl(conf.getProperty("url"));
			System.out.println(conf.getProperty("url"));
			p.setDriverClassName("com.mysql.jdbc.Driver");
			p.setUsername(conf.getProperty("user"));
			System.out.println(conf.getProperty("user"));
			p.setPassword(conf.getProperty("password"));
			System.out.println(conf.getProperty("password"));;
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
			p.setRemoveAbandonedTimeout(120);
			p.setMinEvictableIdleTimeMillis(30000);
			p.setMinIdle(10);
			p.setLogAbandoned(true);
			p.setRemoveAbandoned(true);
			p.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"
					+ "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
			datasource = new DataSource();
			datasource.setPoolProperties(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = datasource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
