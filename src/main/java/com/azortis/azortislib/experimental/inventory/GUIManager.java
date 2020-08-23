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

import com.azortis.azortislib.experimental.inventory.impl.v1_15.GUIEngineImpl;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

// todo: Implement Placeholder support!
public class GUIManager {
    private static JavaPlugin PLUGIN_REF;
    private final GUIEngine engine;

    private GUIManager(JavaPlugin plugin) {
        if (plugin == null) throw new NullPointerException("Plugin reference is null!");
        engine = new GUIEngineImpl();
        engine.initialize(plugin);
    }


    public static GUIManager getInstance() throws NullPointerException {
        return GUIManagerSingleton.INSTANCE;
    }

    public static GUIManager getInstance(JavaPlugin plugin) {
        PLUGIN_REF = plugin;
        return GUIManagerSingleton.INSTANCE;
    }

    /**
     * Seamlessly open an inventory without moving the cursor.
     *
     * @param player the player to open the inventory for
     * @param gui    the gui to use
     */
    public static void openInventorySeamless(Player player, GUI gui) {
        // todo https://www.spigotmc.org/threads/opening-inventories-without-moving-cursor.42138/

    }

    /**
     * Future-proofed method for getting GUIEngines with multi-version support & async support.
     *
     * @return instance of GUIEngine
     */
    public GUIEngine getEngine() {
        return engine;
    }

    private static class GUIManagerSingleton {
        public static final GUIManager INSTANCE = new GUIManager(PLUGIN_REF);
    }

}
