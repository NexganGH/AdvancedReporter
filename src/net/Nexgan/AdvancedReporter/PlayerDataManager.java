package net.Nexgan.AdvancedReporter;

import java.util.ArrayList;


public class PlayerDataManager {
	
	private static PlayerDataManager instance;

	private static ArrayList<PlayerData> playerDatas = new ArrayList<>();
	
	public static PlayerDataManager getInstance() {
		if(instance == null)
			instance = new PlayerDataManager();
		return instance;
	}
	
	
	
	public PlayerData getPlayerDataByName(String name) {
		for(PlayerData pa : playerDatas)
			if(pa.getPlayer().getName().equalsIgnoreCase(name))
				return pa;
		return null;
	}

}
