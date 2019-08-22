package net.Nexgan.AdvancedReporter.events;

import net.Nexgan.AdvancedReporter.playerdata.PlayerData;
import net.Nexgan.AdvancedReporter.playerdata.PlayerDataManager;
import net.Nexgan.NexganLib.guis.GUI;
import net.Nexgan.NexganLib.guis.GUIItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClick implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        PlayerData pa = PlayerDataManager.getInstance().getPlayerDataByPlayer((Player) event.getWhoClicked());
        if(pa.getCurrentOpenGUI() != null && pa.getCurrentOpenGUI().getInventory() != null) {
            GUI gui = pa.getCurrentOpenGUI();
            event.setCancelled(true);
            if(gui.getName().equalsIgnoreCase("sections")) {
                GUIItem item = gui.getItemByIndex(event.getSlot());
                if(item != null) {

                }
            }

        }
    }


}
