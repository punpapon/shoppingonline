package util.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionUtil {

	public static void close(Connection conn){
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
		}
	}

	public static void closeAll(ResultSet rst, Statement stmt) {
		closeResultSet(rst);
		closeStatement(stmt);
	}

	public static void closeResultSet(ResultSet rst) {
		try {
			if (rst != null) {
				rst.close();
			}
		} catch (Exception e) {
		}
	}

	public static void closeStatement(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (Exception e) {
		}
	}
}
