package net.Nexgan.AdvancedReporter.playerdata;

import net.Nexgan.AdvancedReporter.sections.Section;
import net.Nexgan.AdvancedReporter.sections.SubSection;
import net.Nexgan.NexganLib.guis.GUI;
import org.bukkit.entity.Player;

/**
 * Represents an online or offline players. Contains useful information regarding it.
 */
public class PlayerData {

    private String name;
    private Player player;
    private boolean online;
    private boolean everJoinedServer;
    private PlayerData reportingPlayer;

    private GUI currentOpenGUI;

    private Section currentOpenSection;

    public PlayerData(String name, Player player, boolean online, boolean everJoinedServer) {
        this.name = name;
        this.player = player;
        this.online = online;
        this.everJoinedServer = everJoinedServer;
    }

    public String getName() {
        return name;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isOnline() {
        return online;
    }

    public boolean hasEverJoinedServer() { return everJoinedServer; }

    public PlayerData getReportingPlayer() {
        return reportingPlayer;
    }

    public void setReportingPlayer(PlayerData reportingPlayer) {
        this.reportingPlayer = reportingPlayer;
    }

    public void setCurrentOpenGUI(GUI currentOpenGUI) {
        this.currentOpenGUI = currentOpenGUI;
    }

    public GUI getCurrentOpenGUI() {
        return currentOpenGUI;
    }
}
