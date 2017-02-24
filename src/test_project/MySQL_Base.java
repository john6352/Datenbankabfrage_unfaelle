package test_project;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class MySQL_Base{
	
	/** Datenbankname */
	private String database;
	/** Datenbankadresse*/
	private String hostname;
	/** Datenbankport*/
	private int port;
	/** Datenbankbenutzer*/
	private String userid;
	/** Datenbankbenutzerpasswort*/
	private String password;
	/** Datenbank JDBC-link*/
    private String connectionString = "";
	
    
	public MySQL_Base(){
		setDatabase("unfall_test");
		setHostname("localhost");
		setPort(3306); 
		setUserid("admin");
		setPassword("admin");
		start();
	    }
	

	private void start(){
	    	setConnectionString("jdbc:mysql://" + getHostname() + ":" + getPort() + "/" + getDatabase());
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	    }

	protected Connection getConnection() throws SQLException {
			Connection c = null;
			c = DriverManager.getConnection(getConnectionString(), getUserid(), getPassword());
			return c;
		}

	protected static void doUpdate(Connection c, String sql) throws SQLException {
		    Statement stmt = c.createStatement();
		    stmt.executeUpdate(sql);
		    stmt.close();
		}
		
		protected void releaseConnection(Connection c){
			try {
				c.close();
			} catch (SQLException x) {
				System.out.println(x.getMessage());
			}
		}
		
		protected void update(String s){
			Connection c = null;
			try {
				c = getConnection();
				doUpdate(c, s);
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				releaseConnection(c);
			}
		}
		
		/*private String readanddecrypt(String Datei){
			InputStream is = Main.class.getResourceAsStream(Datei);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader bffr = new BufferedReader(isr);
	    	try {
	    		return Encrypter.decrypt(bffr.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return Datei;
		}*/

		
		public String getDatabase() {
			return database;
		}

		
		public void setDatabase(String database) {
			this.database = database;
		}

		
		public String getHostname() {
			return hostname;
		}

		
		public void setHostname(String hostname) {
			this.hostname = hostname;
		}

		
		public int getPort() {
			return port;
		}

		
		public void setPort(int port) {
			this.port = port;
		}

		
		public String getUserid() {
			return userid;
		}

		
		public void setUserid(String userid) {
			this.userid = userid;
		}

		
		public String getPassword() {
			return password;
		}

		
		public void setPassword(String password) {
			this.password = password;
		}

		
		public String getConnectionString() {
			return connectionString;
		}

		
		public void setConnectionString(String connectionString) {
			this.connectionString = connectionString;
		}
}