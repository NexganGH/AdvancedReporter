package net.Nexgan.AdvancedReporter;

import java.util.ArrayList;

import org.bukkit.inventory.ItemStack;

public class Section {
	
	private String name;
	private ItemStack itemStack;
	private ArrayList<SubSection> subSections = new ArrayList<SubSection>();
	
	public Section(String name, ItemStack itemStack, ArrayList<SubSection> subSections) {
		this.name = name;
		this.itemStack = itemStack;
		this.subSections = subSections;
	}
	
	public String getName() {
		return name;
	}

}
