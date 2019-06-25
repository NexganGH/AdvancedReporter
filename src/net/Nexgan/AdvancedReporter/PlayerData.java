package net.Nexgan.AdvancedReporter;

import org.bukkit.entity.Player;

public class PlayerData {

    private String name;
    private Player player;
    private boolean online;
    private boolean hasEverJoinedServer;

    public PlayerData(String name, Player player, boolean online, boolean hasEverJoinedServer) {
        this.name = name;
        this.player = player;
        this.online = online;
        hasEverJoinedServer = hasEverJoinedServer;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isOnline() {
        return online;
    }
}
