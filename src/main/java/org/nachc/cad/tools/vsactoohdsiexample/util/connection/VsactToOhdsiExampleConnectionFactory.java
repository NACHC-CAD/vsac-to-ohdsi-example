package org.nachc.cad.tools.vsactoohdsiexample.util.connection;

import java.sql.Connection;

import org.nachc.cad.tools.vsactoohdsi.util.connection.VsacToOhdsiConnectionFactory;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

/* * *
 * 
 * Note: this class can be rewritten to use another method to get a connection.  
 * 
 */
@Slf4j
public class VsactToOhdsiExampleConnectionFactory {

	public static Connection getConnection() {
		Connection conn = VsacToOhdsiConnectionFactory.getConnection();
		return conn;
	}
	
	public static void testConnection(Connection conn) {
		try {
			String sqlString = "select 'test' as test_value";
			String testValue = Database.queryForFirst(sqlString, "testValue", conn);
			log.info("Got testValue: " + testValue);
		} catch(Exception exp) {
			throw new RuntimeException("Could not create connection and execute query", exp);
		}
	}

}