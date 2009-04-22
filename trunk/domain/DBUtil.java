package domain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 * Helps with interfacing with the database
 * 
 * @author Corey
 *
 */
public class DBUtil {
	/** This is the database file that our program will read/write to */
	private static File THE_FILE;
	
	/** This is the file extion that our application uses */
	public static final String FILE_EXTENSION = ".rdb";
	
	/** This is our application's name */
	public static final String APPLICATION_NAME = "Raptor";
	
	/**
	 * Returns the Connection to our database 
	 * @return
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
	
	/**
	 * Tells the program what database file to use. 
	 * Can be a new file or an existing file. 
	 * If the file is new, it will be initialized.
	 * @param file
	 */
	public static void openFile(File file) {
		if (file.exists()) {
			openExistingFile(file);
		} else  {
			openNewFile(file);
		}
	}
	
	/**
	 * Prepares a new file to be used by the database. 
	 * @param file
	 */
	private static void openNewFile(File file) {
		//Does our new file have our extension?
        if (!file.getName().endsWith(FILE_EXTENSION)){
        	//DOH! it doesn't 
        	file = new File(file.getAbsolutePath() + FILE_EXTENSION);
        }
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
	
	/**
	 * Prepares an existing file to be used by the database.
	 * @param file
	 */
	private static void openExistingFile(File file) {
		THE_FILE = file;
		DomainUtil.populateItems();
	}
	
	/**
	 * Automatically generates a temporary file to use 
	 * for the database. Automatically prepares the database
	 * with all create statements. 
	 */
	public static void openTemporaryFile() {
		
		try {
			THE_FILE = File.createTempFile(APPLICATION_NAME + "Temp", FILE_EXTENSION);
			createTables();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DomainUtil.populateItems();
	}
	
	/**
	 * Gets the current file being used by the database
	 * @return
	 */
	public static File getFile() {
		return THE_FILE.getAbsoluteFile();
	}
	
	/**
	 * Creates a file filter that will filter out files
	 * that have the application's file extension.
	 * @return
	 */
	public static FileFilter getFileFilter() {
		return new DBUtil().new DBFileFilter();
	}

	/**
	 * Creates a JFileChooser that will be used to open 
	 * a database file for our application.
	 * @return
	 */
	public static JFileChooser getFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter((javax.swing.filechooser.FileFilter) getFileFilter());
        fileChooser.setDialogTitle("Open a " + APPLICATION_NAME +" File");
        File homeDir = new File(System.getProperty("user.home"));
        fileChooser.setCurrentDirectory(homeDir);
//        File useMe = new File(
//        		homeDir.getAbsolutePath() + 
////        		File.separator + 
//        		APPLICATION_NAME + 
//        		FILE_EXTENSION);
//        fileChooser.setSelectedFile(homeDir);
        //TODO setSelectedFile isn't working for some reason
		return fileChooser;
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

	/**
	 * A FileFilter that will filter out files that have our 
	 * application's file extension
	 * @author Corey
	 *
	 */
	private class DBFileFilter extends FileFilter {
		@Override
		public boolean accept(File f) {
			return f.getName().toLowerCase().endsWith(FILE_EXTENSION);
		}
		@Override
		public String getDescription() {
			return FILE_EXTENSION + " files";
		}
	}
}