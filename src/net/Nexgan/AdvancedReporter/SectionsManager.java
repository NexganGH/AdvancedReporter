package net.Nexgan.AdvancedReporter;

import java.util.ArrayList;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

/**
 * Singletons that manages sections.
 */
public class SectionsManager {
	
	private static SectionsManager instance;
	
	private ArrayList<Section> sections = new ArrayList<Section>();
	
	public static SectionsManager getInstance() {
		if(instance == null)
			instance = new SectionsManager();
		return instance;
	}

	/** Loads every section from the section.yml file and adds them to "sections" arraylist.
	 */
	public void setup() {
		FileConfiguration sectionsConf = SettingsManager.getInstance().getSectionsConf();

		
		for(String name : sectionsConf.getConfigurationSection("sections-list").getKeys(false)) {
			String path = "sections-list." + name;
			
			ItemStack item = sectionsConf.getItemStack(path + ".item");
			ArrayList<SubSection> subSections = new ArrayList<>();
			
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

	public SubSection getSubSectionByName(String name) {
		for (Section section : sections)
			for (SubSection subSection : section.getSubSections())
				if (subSection.getName().equalsIgnoreCase(name))
					return subSection;
		return null;
	}


}
