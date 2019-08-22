package net.Nexgan.AdvancedReporter.commands;

import net.Nexgan.AdvancedReporter.SettingsManager;
import net.Nexgan.AdvancedReporter.guis.ReportGUIManager;
import net.Nexgan.AdvancedReporter.playerdata.PlayerData;
import net.Nexgan.AdvancedReporter.playerdata.PlayerDataManager;
import net.Nexgan.AdvancedReporter.reports.ReportsManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Report implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(command.getName().equalsIgnoreCase("report")) {
            SettingsManager settingsManager = SettingsManager.getInstance();
            PlayerDataManager paManager = PlayerDataManager.getInstance();
            ReportGUIManager reportGUIManager = ReportGUIManager.getInstance();

            if (!(commandSender instanceof Player)) {
                commandSender.sendMessage(settingsManager.getMessage("PERFORMER_MUST_BE_PLAYER"));
                return false;
            }

            Player player = (Player) commandSender;

            if (args.length < 1) {
                player.sendMessage(settingsManager.getMessage("COMMAND_USAGE").replace("%replace", "/report <player> [reason]"));
                return false;
            }

            PlayerData reporter = paManager.getPlayerDataByName(player.getName());

            PlayerData reported = paManager.getPlayerDataByName(args[0]);
            if(!reported.hasEverJoinedServer() && (reported.getReportingPlayer() == null || reported.getReportingPlayer() != reporter)) {
                player.sendMessage(settingsManager.getMessage("PLAYER_HAS_NEVER_JOINED").replace("%reported", args[0]));
                reporter.setReportingPlayer(reported);
                return false;
            }

            String reason = "";
            for (int i = 1; i < args.length; i++) {
                reason.concat(args[i]);
            }

            reportGUIManager.openSectionGUI(player, reported);

            return true;
        }
        return false;
    }
}
