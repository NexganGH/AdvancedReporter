package net.Nexgan.AdvancedReporter;

import net.Nexgan.AdvancedReporter.commands.Report;
import net.Nexgan.AdvancedReporter.events.InventoryClick;
import net.Nexgan.AdvancedReporter.events.InventoryClose;
import net.Nexgan.AdvancedReporter.events.PlayerJoin;
import net.Nexgan.AdvancedReporter.guis.GUIManager;
import net.Nexgan.AdvancedReporter.playerdata.PlayerData;
import net.Nexgan.AdvancedReporter.playerdata.PlayerDataManager;
import net.Nexgan.AdvancedReporter.reports.ReportTicket;
import net.Nexgan.AdvancedReporter.reports.ReportsManager;
import net.Nexgan.AdvancedReporter.sections.SectionsManager;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	private static Main plugin = null;

    /**
     * Automatically called upon server startup, it setups the plugin.
     */
	public void onEnable() {
		plugin = this;

		net.Nexgan.NexganLib.Main.setup(this);

		net.Nexgan.NexganLib.files.SettingsManager.getInstance().setup("mysql.yml",
				"reports.yml",
				"sections.yml",
				"config.yml",
				"guis.yml");

		SettingsManager settings = SettingsManager.getInstance();
		settings.setup();

		SectionsManager sections = SectionsManager.getInstance();
		sections.setup();


		GUIManager guiManager = GUIManager.getInstance();
		guiManager.loadGUIs();

		this.getCommand("report").setExecutor(new Report());
		

		MySQL mysql = MySQL.getInstance();
		mysql.setup();

        ReportsManager reportsManager = ReportsManager.getInstance();
        reportsManager.setup();

		PlayerDataManager paManager = PlayerDataManager.getInstance();
		PlayerData reporter = paManager.getPlayerDataByName("leoncino");
		PlayerData reported = paManager.getPlayerDataByName("leoncinone");
		PlayerData manager = paManager.getPlayerDataByName("leonardo2397");

		ReportTicket reportTicket = new ReportTicket(5, reporter, reported, settings.getServerName(), sections.getSectionByName("hacks"),
				sections.getSubSectionByName(sections.getSectionByName("hacks"), "killaura"), "super hacker", manager,
				false, false, null);

		registerEvents(new InventoryClick(), new PlayerJoin(), new InventoryClose());

		reportsManager.saveReport(reportTicket);
	}

	public static void registerEvents(Listener... listeners) {
		for(Listener listener : listeners)
			Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
	}
	
	public static Main getPlugin() {
		return plugin;
	}

}
