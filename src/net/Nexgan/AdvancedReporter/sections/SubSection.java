package net.Nexgan.AdvancedReporter.sections;

import org.bukkit.inventory.ItemStack;

public class SubSection {
	
	private String name;
	private ItemStack itemStack;
	
	public SubSection(String name, ItemStack itemStack) {
		this.name = name;
		this.itemStack = itemStack;
	}

	public String getName() {
		return name;
	}
}
