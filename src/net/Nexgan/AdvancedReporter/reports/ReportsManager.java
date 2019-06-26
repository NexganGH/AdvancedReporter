package net.Nexgan.AdvancedReporter.reports;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import net.Nexgan.AdvancedReporter.*;
import net.Nexgan.AdvancedReporter.playerdata.PlayerData;
import net.Nexgan.AdvancedReporter.playerdata.PlayerDataManager;
import net.Nexgan.AdvancedReporter.sections.Section;
import net.Nexgan.AdvancedReporter.sections.SectionsManager;
import net.Nexgan.AdvancedReporter.sections.SubSection;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

public class ReportsManager {

    private static ReportsManager instance;

    private static ArrayList<ReportTicket> reports = new ArrayList<>();

    public static ReportsManager getInstance() {
        if (instance == null)
            instance = new ReportsManager();
        return instance;
    }

    /**
     * Loads every report from MySQL database or flat files (reports.yml).
     */
    public void setup() {
        MySQL mysql = MySQL.getInstance();
        reports.clear();

        PlayerDataManager paManager = PlayerDataManager.getInstance();
        SettingsManager settingsManager = SettingsManager.getInstance();
        FileConfiguration sectionsConf = settingsManager.getSectionsConf();
        FileConfiguration reportsConf = settingsManager.getReportsConf();
        SectionsManager sectionsManager = SectionsManager.getInstance();

        if (mysql.isEnabled()) {
            Connection connection = mysql.getConnection();
            String table = mysql.getReportTableName();

            try {
                Statement statement = connection.createStatement();

                ResultSet rs = statement.executeQuery("SELECT * FROM `" + table + "`");

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
                    SubSection subSection = sectionsManager.getSubSectionByName(section, subSectionName);

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
            } catch (SQLException SQLException) {
                Bukkit.getServer().getLogger().warning("AdvancedReporter ERROR > Error while trying to get reports from MySQL.");
                SQLException.printStackTrace();
            }

        } else {
            for (String idString : reportsConf.getConfigurationSection("reports-list").getKeys(false)) {
                //TODO Check if it is an Integer
                int id = Integer.parseInt(idString);
                String path = "reports-list." + idString;
                PlayerData reporter = paManager.getPlayerDataByName(reportsConf.getString(path + ".reporter"));
                PlayerData reported = paManager.getPlayerDataByName(reportsConf.getString(path + ".reported"));
                String serverName = reportsConf.getString(path + ".server-name");

                String sectionName = reportsConf.getString(path + ".section");
                Section section = sectionsManager.getSectionByName(sectionName);

                String subSectionName = reportsConf.getString(path + ".sub-section");
                SubSection subSection = sectionsManager.getSubSectionByName(section, subSectionName);

                String reason = reportsConf.getString(path + ".reason");

                PlayerData manager = paManager.getPlayerDataByName(sectionsConf.getString(path + ".manager"));

                boolean pending = reportsConf.getBoolean(path + ".pending");
                boolean accepted = reportsConf.getBoolean(path + ".accepted");
                String howResolved = reportsConf.getString(path + ".howResolved");
                ReportTicket reportTicket = new ReportTicket(id, reporter, reported, serverName, section, subSection, reason, manager, pending, accepted, howResolved);
                reports.add(reportTicket);
            }
        }

    }

    /**
     * Saves a report in MySQL database or in flat file (reports.yml).
     */
    public void saveReport(ReportTicket reportTicket) {
        SettingsManager settingsManager = SettingsManager.getInstance();
        MySQL mysql = MySQL.getInstance();
        String subSection = null;
        String manager = null;
        if (reportTicket.getSubSection() != null)
            subSection = reportTicket.getSubSection().getName();
        if (reportTicket.getManager() != null)
            manager = reportTicket.getManager().getName();
        if (mysql.isEnabled()) {
            try {
                Statement statement = mysql.getConnection().createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM `" + mysql.getReportTableName() + "` WHERE `id` = " + reportTicket
                        .getId() + ";");
                if (rs.next())
                    statement.executeUpdate("DELETE FROM `" + mysql.getReportTableName() + "` WHERE `id` = " + reportTicket.getId() + ";");
                statement.executeUpdate("INSERT INTO `" + mysql.getReportTableName() + "` VALUES (" +
                        reportTicket.getId() + ", '" +
                        reportTicket.getReporter().getName() + "', '" +
                        reportTicket.getReported().getName() + "', '" +
                        reportTicket.getServerName() + "', '" +
                        reportTicket.getSection().getName() + "', '" +
                        subSection + "', '" +
                        reportTicket.getReason() + "', '" +
                        manager + "', " +
                        reportTicket.isPending() + ", " +
                        reportTicket.isAccepted() + ", '" +
                        reportTicket.getHowResolved() + "')");
                statement.close();
            } catch (SQLException e) {
                Bukkit.getLogger().warning("AdvancedReporter ERROR > Error while trying to save/update a record in MySQL database.");
                e.printStackTrace();
            }

        } else {
            FileConfiguration reportsConf = settingsManager.getReportsConf();
            String path = "reports-list." + reportTicket.getId();
            reportsConf.set(path + ".reporter", reportTicket.getReporter().getName());
            reportsConf.set(path + ".reported", reportTicket.getReported().getName());
            reportsConf.set(path + ".server-name", reportTicket.getServerName());
            reportsConf.set(path + ".section", reportTicket.getSection().getName());
            reportsConf.set(path + ".sub-section", subSection);
            reportsConf.set(path + ".reason", reportTicket.getReason());
            reportsConf.set(path + ".manager", manager);
            reportsConf.set(path + ".pending", reportTicket.isPending());
            reportsConf.set(path + ".accepted", reportTicket.isAccepted());
            reportsConf.set(path + ".how-resolved", reportTicket.getHowResolved());
            settingsManager.save();
        }
        setup();
    }

}
