package net.Nexgan.AdvancedReporter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.bukkit.Bukkit;

public class ReportsManager {
	
	private static ReportsManager instance;
	
	private static ArrayList<ReportTicket> reports;
	
	public static ReportsManager getInstance() {
		if(instance == null)
			instance = new ReportsManager();
		return instance;
	}
	
	public void setup() {
		MySQL mysql = MySQL.getMySQL();
		reports.clear();
		
		PlayerDataManager paManager = PlayerDataManager.getInstance();
		
		if(mysql.isEnabled()) {
			Connection connection = mysql.getConnection();
			String table = mysql.getReportTableName();
			try {
				Statement statement = connection.createStatement();
				
				ResultSet rs = statement.executeQuery("SELECT * FROM '" + table + "'");
				
				while(rs.next()) {
					int id = rs.getInt("id");
					PlayerData reporter = paManager.getPlayerDataByName(rs.getString("reporter"));
					PlayerData reported = paManager.getPlayerDataByName(rs.getString("reported"));
					String serverName = rs.getString("serverName");
					
				}
				statement.close();
			} catch (SQLException e) {
				Bukkit.getServer().getLogger().warning("AdvancedReporter ERROR > Error while trying to get reports from MySQL.");
				e.printStackTrace();
			}
			
		} else {
			
		}
	}
}
