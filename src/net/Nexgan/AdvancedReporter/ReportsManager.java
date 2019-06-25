package net.Nexgan.AdvancedReporter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

public class ReportsManager {

    private static ReportsManager instance;

    private static ArrayList<ReportTicket> reports;

    public static ReportsManager getInstance() {
        if (instance == null)
            instance = new ReportsManager();
        return instance;
    }

    /**
     * Loads every report from MySQL database of flat files (reports.yml).
     */
    public void setup() {
        MySQL mysql = MySQL.getMySQL();
        reports.clear();

        PlayerDataManager paManager = PlayerDataManager.getInstance();
        SettingsManager settingsManager = SettingsManager.getInstance();
        FileConfiguration config = settingsManager.getSectionsConf();
        SectionsManager sectionsManager = SectionsManager.getInstance();

        if (mysql.isEnabled()) {
            Connection connection = mysql.getConnection();
            String table = mysql.getReportTableName();

            try {
                Statement statement = connection.createStatement();

                ResultSet rs = statement.executeQuery("SELECT * FROM '" + table + "'");

                while (rs.next()) {
                    int id = rs.getInt("id");
                    PlayerData reporter = paManager.getPlayerDataByName(rs.getString("reporter"));
                    PlayerData reported = paManager.getPlayerDataByName(rs.getString("reported"));
                    String serverName = rs.getString("serverName");

                    String sectionName = rs.getString("section");
                    //TODO Check existence of section name and give proper warning
                    Section section = sectionsManager.getSectionByName(sectionName);


                    String subSectionName = rs.getString("subSection");
                    //TODO Check existence of subSection name and give proper warning
                    SubSection subSection = sectionsManager.getSubSectionByName(subSectionName);

                    String reason = rs.getString("reason");

                    String managerName = rs.getString("manager");
                    PlayerData manager = paManager.getPlayerDataByName(managerName);

                    boolean pending = rs.getBoolean("pending");
                    boolean accepted = rs.getBoolean("accepted");
                    String howResolved = rs.getString("howResolved");

                    ReportTicket reportTicket = new ReportTicket(id, reporter, reported, serverName, section, subSection, reason, manager, pending, accepted, howResolved);
                    reports.add(reportTicket);
                }
                statement.close();
            } catch (SQLException e) {
                Bukkit.getServer().getLogger().warning("AdvancedReporter ERROR > Error while trying to get reports from MySQL.");
                e.printStackTrace();
            }

        } else {
            for (String idString : config.getConfigurationSection("reports-list").getKeys(false)) {
                //TODO Check if it is an Integer
                int id = Integer.parseInt(idString);
                String path = "reports-list." + idString;
                PlayerData reporter = paManager.getPlayerDataByName(config.getString(path + ".reporter"));
                PlayerData reported = paManager.getPlayerDataByName(config.getString(path + ".reported"));
                String serverName = config.getString(path + ".server-name");

                String sectionName = config.getString(path + ".section");
                Section section = sectionsManager.getSectionByName(sectionName);

                String subSectionName = config.getString(path + ".sub-section");
                SubSection subSection = sectionsManager.getSubSectionByName(subSectionName);

                String reason = config.getString(path + ".reason");

                PlayerData manager = paManager.getPlayerDataByName(config.getString(path + ".manager"));

                boolean pending = config.getBoolean(path + ".pending");
                boolean accepted = config.getBoolean(path + ".accepted");
                boolean howREsolved = config.getBoolean(path + ".howResolved");
            }
        }

    }
}
