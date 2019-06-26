package net.Nexgan.AdvancedReporter;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	private static Main plugin = null;


    /**
     * Automatically called upon server startup
     */
	public void onEnable() {
		plugin = this;
		
		SettingsManager settings = SettingsManager.getInstance();
		settings.setup();
		
		SectionsManager sections = SectionsManager.getInstance();
		sections.setup();
		
		MySQL mysql = MySQL.getMySQL();
		mysql.setup();

        ReportsManager reportsManager = ReportsManager.getInstance();
        reportsManager.setup();

	}
	
	public static Main getPlugin() {
		return plugin;
	}

}
