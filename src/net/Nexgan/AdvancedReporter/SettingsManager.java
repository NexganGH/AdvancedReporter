package net.Nexgan.AdvancedReporter;

import java.io.File;
import java.io.IOException;

import net.Nexgan.NexganLib.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class SettingsManager {
	
	private static SettingsManager instance = null;

	private String serverName;

	public static SettingsManager getInstance() {
		if(instance == null)
			instance = new SettingsManager();
		return instance;
	}
	
	public void setup() {
		net.Nexgan.NexganLib.files.SettingsManager settingsManager = net.Nexgan.NexganLib.files.SettingsManager.getInstance();
		settingsManager.setup(
				"mysql.yml",
				"reports.yml",
				"sections.yml",
				"config.yml",
				"guis.yml"
		);
		String serverName = settingsManager.getConf("config.yml").getString("server-name");


		/*(Plugin plugin = Main.getPlugin();
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

		guiFile = getFile("guis.yml");
		guiConf = YamlConfiguration.loadConfiguration(guiFile);
		
		serverName = configConf.getString("server-name");
		
		save();*/
	}

	/**
	 * Get the file from plugin's folder or create it.
	 *
	 * @param fileName
	 * @return File whose name was given.
	 */
	public File getFile(String fileName) {
		Plugin plugin = Main.getPlugin();
		File file = new File(plugin.getDataFolder(), fileName);
		if(!file.exists()) {
			file.getParentFile().mkdirs();
			plugin.saveResource(fileName, false);
		}
		return file;
	}

	/** Save every configuration in its proper file.
	 */
	public void save() {
		net.Nexgan.NexganLib.files.SettingsManager.getInstance().save();
		/*try {
			mysqlConf.save(mysqlFile);
			reportsConf.save(reportsFile);
			sectionsConf.save(sectionsFile);
			configConf.save(configFile);
			guiConf.save(guiFile);
		} catch(IOException e) {
			Bukkit.getLogger().warning("AdvancedReporter ERROR > Error while saving a file.");
			e.printStackTrace();
		}*/
	}

	/** Loads MySQL fields from mysql.yml file and sets them in MySQL class.
	 */
	public void loadMySQLFields() {
        MySQL mysql = MySQL.getInstance();
        FileConfiguration mysqlConf = getConf("mysql.yml");
		boolean enabled = mysqlConf.getBoolean("enabled");
		String host = mysqlConf.getString("host");
		int port = mysqlConf.getInt("port");
		String database = mysqlConf.getString("database");
		String username = mysqlConf.getString("username");
		String password = mysqlConf.getString("password");
		String reportTable = mysqlConf.getString("report-table");
		mysql.setMySQL(enabled, host, port, database, username, password, reportTable);
	}

	/**
	 * Returns the corresponding message
	 * @param message
	 * @return
	 */
	public String getMessage(String message) {
		// TODO Implement message.yml
		return message;
	}

	public FileConfiguration getConf(String conf) {
		return net.Nexgan.NexganLib.files.SettingsManager.getInstance().getConf(conf);
	}


//	public FileConfiguration getSectionsConf() {
//		return sectionsConf;
//	}
//
//
	public String getServerName() {
		return serverName;
	}
//
//	public FileConfiguration getReportsConf() {
//        return reportsConf;
//    }
//
//	public FileConfiguration getGuiConf() {
//		return guiConf;
//	}
}
