package net.Nexgan.AdvancedReporter.events;

import net.Nexgan.AdvancedReporter.playerdata.PlayerData;
import net.Nexgan.AdvancedReporter.playerdata.PlayerDataManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryClose implements Listener {

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        PlayerData pa = PlayerDataManager.getInstance().getPlayerDataByPlayer(player);
        pa.setCurrentOpenGUI(null);
    }
}
