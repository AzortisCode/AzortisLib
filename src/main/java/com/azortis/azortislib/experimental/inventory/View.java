package com.azortis.azortislib.experimental.inventory;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class View implements InventoryHolder {
    private Inventory inventory;
    private Page page;

    public View(Page page) {
        this.page = page;
        inventory = Bukkit.createInventory(this, page.getPageSize(),
                ChatColor.translateAlternateColorCodes('&', page.getName()));
        inventory.setContents(page.getItems());
    }

    public Page getPage() {
        return page;
    }

    public void dispose() {
        page = null;
        inventory = null;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
