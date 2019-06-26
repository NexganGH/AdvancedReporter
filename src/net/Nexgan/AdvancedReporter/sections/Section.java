package net.Nexgan.AdvancedReporter.sections;

import java.util.ArrayList;

import org.bukkit.inventory.ItemStack;

/**
 * Section to which every report belongs.
 */
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

	public ArrayList<SubSection> getSubSections() {
		return subSections;
	}
}
