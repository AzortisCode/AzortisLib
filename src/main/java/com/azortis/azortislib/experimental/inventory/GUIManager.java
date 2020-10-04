package com.azortis.azortislib.experimental.inventory;

import com.azortis.azortislib.experimental.inventory.item.Item;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.plugin.java.JavaPlugin;

// todo: Make an animator of some sort.
public class GUIManager implements Listener {

    public GUIManager(JavaPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerClick(InventoryClickEvent event) {
        if (event.getClickedInventory() != null &&
                event.getClickedInventory().getHolder() instanceof View) {
            View view = (View) event.getInventory().getHolder();
            if (view.getPage().getItems()[event.getSlot()] != null) {
                Item item = view.getPage().getItems()[event.getSlot()];
                if (item.getEventConsumer() != null) item.getEventConsumer().accept(event, view);
            }
        }
    }

    @EventHandler
    public void onPlayerClose(InventoryCloseEvent event) {
        if (event.getInventory().getHolder() instanceof View) {
            View view = (View) event.getInventory().getHolder();
            if (view.getPage().getCloseAction() != null)
                view.getPage().getCloseAction().accept(event, view);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerCloseMonitor(InventoryCloseEvent event) {
        if (event.getInventory().getHolder() instanceof View) {
            ((View) event.getInventory().getHolder()).getPage()
                    .disposeView((View) event.getInventory().getHolder());
        }
    }
}
