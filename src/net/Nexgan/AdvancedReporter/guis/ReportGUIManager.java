package net.Nexgan.AdvancedReporter.guis;

import net.Nexgan.AdvancedReporter.playerdata.PlayerData;
import net.Nexgan.AdvancedReporter.playerdata.PlayerDataManager;
import net.Nexgan.AdvancedReporter.sections.Section;
import net.Nexgan.AdvancedReporter.sections.SectionsManager;
import net.Nexgan.NexganLib.guis.GUI;
import net.Nexgan.NexganLib.guis.GUIItem;
import net.Nexgan.NexganLib.guis.GUIManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

public class ReportGUIManager {

    private static ReportGUIManager instance;

    public static  ReportGUIManager getInstance() {
        if(instance == null)
            instance = new ReportGUIManager();
        return instance;
    }

    /**
     * Opens the sections gui to the player who performed the report command.
     * @param player
     * @param reported
     */
    public void openSectionGUI(Player player, PlayerData reported) {
        SectionsManager sectionsManager = SectionsManager.getInstance();
        PlayerDataManager playerDataManager = PlayerDataManager.getInstance();
        GUIManager guiManager = GUIManager.getInstance();

        PlayerData playerData = playerDataManager.getPlayerDataByPlayer(player);

        GUI gui = guiManager.getGUI("sections");
        Inventory inv = gui.getInventory();

        playerData.setReportingPlayer(reported);
        playerData.setCurrentOpenGUI(gui);
        player.openInventory(inv);
    }

    /**
     * Calculates the required gui size given the amount of items.
     * @param arraySize The amount of items to put in the gui.
     * @return
     */
    private int calculateRequiredSize(int arraySize) {
        int size;
        if(arraySize%9 == 0)
            size = arraySize/9;
        else
            size = arraySize/9+1;
        return size*9;
    }





}
