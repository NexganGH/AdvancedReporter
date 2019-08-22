package net.Nexgan.AdvancedReporter.events;

import net.Nexgan.AdvancedReporter.guis.GUIManager;
import net.Nexgan.AdvancedReporter.guis.ReportGUIManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
    @EventHandler
    public void playerJoin(PlayerJoinEvent playerJoinEvent) {
        // For testing purpose
        ReportGUIManager reportGUIManager = ReportGUIManager.getInstance();
        GUIManager guiManager = GUIManager.getInstance();
        //reportGUIManager.openSectionGUI(playerJoinEvent.getPlayer(), null);
        guiManager.openGUI(playerJoinEvent.getPlayer(), net.Nexgan.NexganLib.guis.GUIManager.getInstance().getGUI("manager"));
    }

}
