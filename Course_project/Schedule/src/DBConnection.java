import java.sql.*;
import java.lang.AutoCloseable;

public class DBConnection implements AutoCloseable{
	private Connection connection;
	private String url;
	private String username;
	private String password;
	
	DBConnection(String url, String username, String password) throws SQLException{
		this.url = url;
		this.username = username;
		this.password = password;
		connection = DriverManager.getConnection(url, username, password);
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	public String getUrl() {
		return url;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}

	@Override
	public void close() throws Exception {
		if(connection!=null) {
			connection.close();
		}
	}
}
