package net.Nexgan.AdvancedReporter.actions;

import org.bukkit.entity.Player;

public class ActionManager {
    private static ActionManager instance;

    public static ActionManager getInstance() {
        if(instance == null)
            instance = new ActionManager();
        return instance;
    }

    public dispatchAction(Player player, ) {

    }

}
