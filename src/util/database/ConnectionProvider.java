package util.database;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

import com.somapait.shoppingonline.core.config.parameter.domain.DatabaseConfig;
import com.somapait.shoppingonline.core.config.parameter.domain.ParameterConfig;

public class ConnectionProvider {

	private Logger log = Logger.getLogger(ConnectionProvider.class);

	public static String NUM_ACTIVE = "";
	public static String NUM_IDLE = "";

	public static String MAX_ACTIVE = "";
	public static String MAX_IDLE = "";

	private Map<String, DatabaseConfig> databases = ParameterConfig.getDatabases();

	private static boolean INIT = false;
	private static Map<String, DataSource> ds = new HashMap<String, DataSource>();

	public ConnectionProvider() throws Exception {
		if (INIT == false) {
			try {
				ds.clear();
				for (String key : databases.keySet()) {
					DatabaseConfig dbp = databases.get(key);

					Context context = new InitialContext();
					if (dbp.isJndi()) {
						context = (Context) context.lookup("java:comp/env");
					}

					DataSource ddss = (DataSource) context.lookup(dbp.getLookup());

					ds.put(dbp.getIndex(), ddss);
				}
				INIT = true;
			} catch (Exception e) {
				throw e;
			}
		}
	}

	public Connection getConnection(Connection conn, String lookup) throws Exception {

		try {
			BasicDataSource bds = (BasicDataSource) ds.get(lookup);
			setStatus(bds);
		} catch (Exception ex) {
			log.error("", ex);
		}

		try {
			if (conn == null) {
				conn = ds.get(lookup).getConnection();
			}
		} catch (Exception e) {
			throw e;
		}

		try {
			BasicDataSource bds = (BasicDataSource) ds.get(lookup);
			log.debug("After NumIdle :- [" + bds.getNumIdle() + "] from [" + bds.getMaxIdle() + "]");
			setStatus(bds);
		} catch (Exception ex) {
			log.error("", ex);
		}
		return conn;
	}

	private void setStatus(BasicDataSource bds) {
		NUM_ACTIVE = String.valueOf(bds.getNumActive());
		NUM_IDLE = String.valueOf(bds.getNumIdle());

		MAX_IDLE = String.valueOf(bds.getMaxIdle());
	}
}
