package net.Nexgan.AdvancedReporter;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class SettingsManager {
	
	private static SettingsManager instance = null;
	
	private File mysqlFile;
	private FileConfiguration mysqlConf;
	
	private File reportsFile;
	private FileConfiguration reportsConf;
	
	private File sectionsFile;
	private FileConfiguration sectionsConf;
	
	private File configFile;
	private FileConfiguration configConf;
	
	private String serverName;
	
	public static SettingsManager getInstance() {
		if(instance == null)
			instance = new SettingsManager();
		return instance;
	}
	
	public void setup() {
		Plugin plugin = Main.getPlugin();
		if(!plugin.getDataFolder().exists())
			plugin.getDataFolder().mkdir();
		
		mysqlFile = getFile("mysql.yml");
		mysqlConf = YamlConfiguration.loadConfiguration(mysqlFile);
		loadMySQLFields();
		
		reportsFile = getFile("reports.yml");
		reportsConf = YamlConfiguration.loadConfiguration(reportsFile);
		
		sectionsFile = getFile("sections.yml");
		sectionsConf = YamlConfiguration.loadConfiguration(sectionsFile);
		
		configFile = getFile("config.yml");
		configConf = YamlConfiguration.loadConfiguration(configFile);
		
		serverName = configConf.getString("server-name");
		
		save();
	}
	
	public File getFile(String fileName) {
		Plugin plugin = Main.getPlugin();
		File file = new File(plugin.getDataFolder(), fileName);
		if(!file.exists()) {
			file.getParentFile().mkdirs();
			plugin.saveResource(fileName, false);
		}
		return file;
	}
	
	public void save() {
		try {
			mysqlConf.save(mysqlFile);
			reportsConf.save(reportsFile);
			sectionsConf.save(sectionsFile);
			configConf.save(configFile);
		} catch(IOException e) {
			Bukkit.getLogger().warning("AdvancedReporter ERROR > Error while saving a file.");
			e.printStackTrace();
		}
	}
	
	public void loadMySQLFields() {
		MySQL mysql = MySQL.getMySQL();
		boolean enabled = mysqlConf.getBoolean("enabled");
		String host = mysqlConf.getString("host");
		int port = mysqlConf.getInt("port");
		String database = mysqlConf.getString("database");
		String username = mysqlConf.getString("username");
		String password = mysqlConf.getString("password");
		String reportTable = mysqlConf.getString("report-table");
		mysql.setMySQL(enabled, host, port, database, username, password, reportTable);
	}
	
	public FileConfiguration getSectionsConf() {
		return sectionsConf;
	}
	
	
	public String getServerName() {
		return serverName;
	}
	
}
