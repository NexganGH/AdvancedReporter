package net.Nexgan.AdvancedReporter.guis;

import net.Nexgan.AdvancedReporter.sections.Section;
import net.Nexgan.AdvancedReporter.sections.SectionsManager;
import net.Nexgan.NexganLib.guis.GUI;
import net.Nexgan.NexganLib.guis.GUIItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class GUIManager {

    private ArrayList<GUI> guis = new ArrayList<>();
    private static GUIManager instance;

    public static GUIManager getInstance() {
        if (instance == null)
            instance = new GUIManager();
        return instance;
    }

    /**
     * Loads every GUI from guis.yml.
     */
    public void loadGUIs() {
        net.Nexgan.NexganLib.guis.GUIManager guiManager = net.Nexgan.NexganLib.guis.GUIManager.getInstance();
        guiManager.loadGUIs();
        GUI gui = guiManager.getGUI("sections");
        int i;
        for (i = 0; i < SectionsManager.getInstance().getSections().size(); i++) {
            Section section = SectionsManager.getInstance().getSections().get(i);
            GUIItem guiItem = new GUIItem(section.getItemStack().clone(),
                    i,
                    "section",
                    section.getName());
            gui.addItem(guiItem);
            Bukkit.getLogger().warning(section.getName());
        }
        gui.reloadInventory();
    }

    /**
     * Open the given GUI to the player.
     * @param player The player the GUI will be opened to.
     * @param gui The GUI opened to the player.
     */
    public void openGUI(Player player, GUI gui) {
        player.openInventory(gui.getInventory());
    }

    /**
     * Get the gui from NexganLib.
     * @param guiName
     * @return
     */
    public GUI getGUI(String guiName) {
        return net.Nexgan.NexganLib.guis.GUIManager.getInstance().getGUI(guiName);
    }
}
