package domain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/* converted sql queries to use prepared statements
 * useful website about prepared statements and sql injection:
 * http://www.owasp.org/index.php/Preventing_SQL_Injection_in_Java
 */

/**
 * This class is used to interface with the database. Any function that 
 * interacts directly should be put in this file, so that it is all in one 
 * place and easier to maintain.
 * 
 * @author Corey
 *
 */
public class DBUtil {
	// this is the database file that our program will read/write to
	private static File THE_FILE;
	
	/**
	 * Returns the Connection to our database 
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	protected static Connection getConnection() {
		
		try {
			Connection conn = null;
			
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(
					"jdbc:sqlite:" + THE_FILE.getAbsolutePath());
			
			return conn;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * The createTables() method will run all CREATE TABLE statements on a 
	 * newly created file so that it can be used by the application
	 * @throws SQLException 
	 * @throws IOException 
	 */
	private static void createTables() throws IOException, SQLException {
		Connection conn = getConnection();
		File file = new File("createTables.sql");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		
		SqlScript script = new DBUtil().new SqlScript(br, conn);
		
		script.loadScript();
		script.execute();
		
		conn.close();
	}
	
	public static void openNewFile(String pathname) {
		
		File file = new File(pathname);
		if (file.exists()) {
			// if the file already exists then it cannot be new!
		} else {
			THE_FILE = file;
			try {
				createTables();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DomainUtil.populateItems();
		}
	}
	
	public static void openExistingFile(String pathname) {
		
		File file = new File(pathname);
		if (!file.exists()) {
			// if the file doesn't exist then we cannot open it!
		} else {
			THE_FILE = file;
			try {
				createTables();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DomainUtil.populateItems();
		}
	}
	
	public static void openTemporaryFile() {
		
		try {
			THE_FILE = File.createTempFile("RaptorUnitTests", ".db");
			createTables();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DomainUtil.populateItems();
	}
	
	/**
	 * Returns the absolute path to the current file 
	 * being used by the database
	 * @return
	 */
	public static String getFilename() {
		return THE_FILE.getAbsolutePath();
	}

	/**
	 * Will execute a SQL script file on a connection
	 * <br /><br />
	 * taken from
	 * <a href="http://www.sternsquirt.com/blog/?cat=10">
	 * http://www.sternsquirt.com/blog/?cat=10
	 * </a>
	 *
	 */
	private class SqlScript {

		public final static char QUERY_ENDS = ';';

		private Connection conn;
		private BufferedReader rdr;
		private Statement statement;

		/**
		 * Constructor: takes a bufferedReader and a sql connection to create the SqlScript object.
		 * Note that construction does not automatically read the script.
		 * @param bufRdr BufferedReader to the script data.
		 * @param connection SQL Connection
		 * @throws SQLException
		 */
		public SqlScript(BufferedReader bufRdr, Connection connection) throws SQLException {
			rdr = bufRdr;
			conn = connection;
			statement = conn.createStatement();
		}

		/**
		 * Loads the Sql Script from the BufferedReader and parses it into a statement.
		 * @throws IOException
		 * @throws SQLException
		 */
		public void loadScript() throws IOException, SQLException {
			String line;
			StringBuffer query = new StringBuffer();
			boolean queryEnds = false;

			while ((line = rdr.readLine()) != null) {
				if (isComment(line))
					continue;
				queryEnds = checkStatementEnds(line);
				query.append(line);
				if (queryEnds) {
					statement.addBatch(query.toString());
					query.setLength(0);
				}
			}
		}

		/**
		 * @param line
		 * @return
		 */
		private boolean isComment(String line) {
			if ((line != null) && (line.length() > 0))
				return (line.charAt(0) == '#');
			return false;
		}

		/**
		 * Executes the statement created by loadScript.
		 *
		 * @throws IOException
		 * @throws SQLException
		 */
		public void execute() throws IOException, SQLException {
			statement.executeBatch();
		}

		private boolean checkStatementEnds(String s) {
			return (s.indexOf(QUERY_ENDS) != -1);
		}

		/**
		 * @return the statement
		 */
		public Statement getStatement() {
			return statement;
		}

		/**
		 * @param statement the statement to set
		 */
		public void setStatement(Statement statement) {
			this.statement = statement;
		}
	}
}
