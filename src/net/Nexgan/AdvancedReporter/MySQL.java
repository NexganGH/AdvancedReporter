package net.Nexgan.AdvancedReporter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;

/**
 * Manages MySQL connections.
 */
public class MySQL {

	private static MySQL instance = null;

	private boolean enabled;
	private String host;
	private int port;
	private String database;
	private String username;
	private String password;
	private String reportTable;

	Connection connection = null;

	/** Tries to estabilish a connection with MySQL, given all the necessary fields. Checks the table, given the name, exists. If not, creates it.
	 */
	public void setup() {
		if (!enabled)
			return;
		String url = "jdbc:mysql://" + host + ":" + port + "/" + database;
		try {
			connection = DriverManager.getConnection(url, username, password);

			Statement statement = connection.createStatement();

			ResultSet tableRs = statement.executeQuery("SHOW TABLES LIKE '" + reportTable + "'");

			if (!tableRs.next()) {
				Bukkit.getLogger().info("AdvancedReporter MySQL > Table '" + reportTable
						+ "' doesn't exist. A new one will be created.");

				statement.executeUpdate("CREATE TABLE " + reportTable + "  ("
						+ "id INT AUTO_INCREMENT, "
						+ "reporter VARCHAR(25) NOT NULL, "
						+ "reported VARCHAR(25) NOT NULL, "
						+ "serverName VARCHAR(25) NOT NULL, "
						+ "section VARCHAR(25) NOT NULL, "
						+ "subsection VARCHAR(25), "
						+ "reason VARCHAR(50), "
						+ "manager VARCHAR(25), "
						+ "pending BOOLEAN NOT NULL, "
						+ "accepted BOOLEAN NOT NULL, "
						+ "howResolved VARCHAR(50), "
						+ "PRIMARY KEY (id)"
						+ ") ENGINE=INNODB;");
			}

			statement.close();
		} catch (SQLException e) {
			Bukkit.getLogger().warning("AdvancedReporter MySQL > Error while trying to connect to MySQL database.");
			e.printStackTrace();
		}

	}

	public static MySQL getMySQL() {
		if (instance == null)
			instance = new MySQL();
		return instance;
	}

	/** Sets MySQL fields.
	 *
	 * @param enabled If MySQL storage system is enabled or not.
	 * @param host Host address.
	 * @param port Port.
	 * @param database Database where everything will be stored name.
	 * @param username Username the plugin will use to perform MySQL actions.
	 * @param password Password of the above user.
	 * @param reportTable Where reports will be stored.
	 */
	public void setMySQL(boolean enabled, String host, int port, String database, String username, String password,
						 String reportTable) {
		this.enabled = enabled;
		this.host = host;
		this.port = port;
		this.database = database;
		this.username = username;
		this.password = password;
		this.reportTable = reportTable;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public Connection getConnection() {
		return connection;
	}

	public String getReportTableName() {
		return reportTable;
	}

}
