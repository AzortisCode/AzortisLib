/*
 * An open source utilities library used for Azortis plugins.
 *     Copyright (C) 2019  Azortis
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.azortis.azortislib.experimental.inventory;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.tuple.Pair;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

public interface GUIEngine extends Listener {
    /**
     * Creates a page using the given GUI while also doing the necessary internal processes to register it.
     *
     * @param gui Creates a page with the given GUI
     * @return the page created
     */
    @NotNull
    Page createPage(@NotNull GUI gui);

    /**
     * Returns an immutable map of the page registry. Pages are mutable however.
     *
     * @return the page registry
     */
    @NotNull
    ImmutableMap<UUID, Page> getPageRegistry();

    /**
     * Removes a page with the given uuid from the page registry.
     *
     * @param uuid uuid of the page
     * @return page that was removed
     */
    @Nullable
    Page removePage(@NotNull UUID uuid);

    /**
     * Creates a new GUI with the given guibuilder.
     *
     * @param builder the builder to use to build the gui
     * @return the gui
     */
    @NotNull
    GUI createGUI(@NotNull GUIBuilder builder);

    /**
     * Returns an immutable String GUI map. GUI's are mutable.
     *
     * @return an immutable map of gui and unique name
     */
    @NotNull
    ImmutableMap<String, GUI> getGUIRegistry();

    /**
     * Removes a gui by the given name.
     *
     * @param guiName the name of the gui
     * @return gui that was removed
     */
    @Nullable
    GUI removeGUI(String guiName);

    /**
     * Saves a template with the suggested unique name.
     *
     * @param items        items to be saved
     * @param templateName the unique name of the template.
     * @return a template that was saved. Null if items map was empty
     */
    @Nullable
    Template saveTemplate(@NotNull Map<String, Pair<Integer, Item>> items, String templateName);

    /**
     * Reloads pages that are open and are being handled by this GUIEngine.
     */
    void reloadPages();

    /**
     * A default method which is used when the GUIEngine is first created. Used to register runnables and event listeners.
     *
     * @param plugin the plugin to use to initialize the engine.
     */
    default void initialize(JavaPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this::update, 1, 5);
    }

    /**
     * A default method used to listen to inventory clicks.
     *
     * @param event the event to use
     */
    // todo check what happens when clicked outside of the inventory
    @EventHandler
    default void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getHolder() instanceof Page) {
            Page page = (Page) event.getInventory().getHolder();
            if (getPageRegistry().containsKey(page.getUUID())) {
                Consumer<InventoryClickEvent> consumer = page.getGUI().getItems()[event.getSlot()].getAction();
                if (consumer != null) consumer.accept(event);
            }
        }
    }

    /**
     * A default method used to listen to an inventory close and remove a page from the registry if it is not a global page.
     *
     * @param event event fired when inventory is closed
     */
    @EventHandler
    default void onInventoryClose(InventoryCloseEvent event) {
        if (event.getInventory().getHolder() instanceof Page) {
            Page page = (Page) event.getInventory().getHolder();
            if (getPageRegistry().containsKey(page.getUUID()))
                if (!page.isGlobal()) removePage(page.getUUID());
        }
    }

    /**
     * A method which is used to update every page every 5 ticks.
     */
    default void update() {
        getPageRegistry().values().forEach(Page::update);
    }

}
