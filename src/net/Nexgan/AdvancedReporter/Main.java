package net.Nexgan.AdvancedReporter;

import net.Nexgan.AdvancedReporter.playerdata.PlayerData;
import net.Nexgan.AdvancedReporter.playerdata.PlayerDataManager;
import net.Nexgan.AdvancedReporter.reports.ReportTicket;
import net.Nexgan.AdvancedReporter.reports.ReportsManager;
import net.Nexgan.AdvancedReporter.sections.SectionsManager;
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
		reportsManager.saveReport(reportTicket);
	}
	
	public static Main getPlugin() {
		return plugin;
	}

}
