package net.Nexgan.AdvancedReporter;

import java.util.ArrayList;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;


public class SectionsManager {
	
	private static SectionsManager instance;
	
	private ArrayList<Section> sections = new ArrayList<Section>();
	
	public static SectionsManager getInstance() {
		if(instance == null)
			instance = new SectionsManager();
		return instance;
	}
	
	/*
	 *  Load every section and its subsection from file.
	 */
	public void setup() {
		FileConfiguration sectionsConf = SettingsManager.getInstance().getSectionsConf();
		/*for(String string : sectionsConf.getConfigurationSection("").getKeys(true)) {
			System.out.println(string);
		}*/
		
		for(String name : sectionsConf.getConfigurationSection("sections-list").getKeys(false)) {
			String path = "sections-list." + name;
			
			/*String materialName = sectionsConf.getString(path + ".item.type");
			materialName = sectionsConf.getString("sections-list.hacks.item.type");
			System.out.println(materialName);
			materialName = materialName.toUpperCase();
			try {
				Material.matchMaterial(materialName);
			} catch(Exception e) {
				Bukkit.getLogger().warning("AdvancedReporter ERROR > Material '" + materialName + "' hasn't been found. Make sure it exists: https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Material.html");			
			}*/
			
			ItemStack item = sectionsConf.getItemStack(path + ".item");
			ArrayList<SubSection> subSections = new ArrayList<SubSection>();
			
			for(String subName : sectionsConf.getConfigurationSection(path + ".sub-sections").getKeys(false)) {
				ItemStack subItem = sectionsConf.getItemStack(path + ".sub-sections." + subName + ".item");
				subSections.add(new SubSection(subName, subItem));
			}
			
			sections.add(new Section(name, item, subSections));
		}
	}
	
	public Section getSectionByName(String name) {
		for(Section section : sections)
			if(section.getName().equalsIgnoreCase(name))
				return section;
		return null;
	}

}
