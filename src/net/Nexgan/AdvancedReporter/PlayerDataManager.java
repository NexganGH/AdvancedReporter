package net.Nexgan.AdvancedReporter;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;


public class PlayerDataManager {
	
	private static PlayerDataManager instance;

	private static ArrayList<PlayerData> playerDatas = new ArrayList<>();
	
	public static PlayerDataManager getInstance() {
		if(instance == null)
			instance = new PlayerDataManager();
		return instance;
	}


	/**
	 * Gets player data if it exists. If not, creates a new one.
	 *
	 * @param name
	 * @return
	 */
	public PlayerData getPlayerDataByName(String name) {
		for(PlayerData pa : playerDatas)
			if(pa.getPlayer().getName().equalsIgnoreCase(name))
				return pa;
		return createNewPlayerData(name);
	}

	public PlayerData createNewPlayerData(String name) {
        if (name == null || name.equalsIgnoreCase("none"))
            return null;
		Player player = Bukkit.getPlayer(name);
		boolean online = false;
		boolean hasEverJoinedServer = false;
		if (player != null) {
			online = true;
			hasEverJoinedServer = true;
		}

		OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(name);
		if (offlinePlayer != null) {
			hasEverJoinedServer = true;
		}

		return new PlayerData(name, player, online, hasEverJoinedServer);
	}


}
