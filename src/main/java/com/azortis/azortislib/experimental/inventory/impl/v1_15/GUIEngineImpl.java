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

package com.azortis.azortislib.experimental.inventory.impl.v1_15;

import com.azortis.azortislib.experimental.inventory.*;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GUIEngineImpl implements GUIEngine {
    protected Map<UUID, Page> uuidPageMap;
    protected Map<String, GUI> stringGUIMap;

    public GUIEngineImpl() {
        uuidPageMap = new HashMap<>();
        stringGUIMap = new HashMap<>();
    }

    /**
     * Creates a page using the given GUI while also doing the necessary internal processes to register it.
     *
     * @param gui Creates a page with the given GUI
     * @return the page created
     */
    @Override
    public @NotNull Page createPage(@NotNull GUI gui) {
        UUID page = UUID.randomUUID();
        while (uuidPageMap.containsKey(page)) page = UUID.randomUUID();
        Page p = new PageImpl(gui, page);
        uuidPageMap.put(page, p);
        return p;
    }

    /**
     * Returns an immutable map of the page registry. Pages are mutable however.
     *
     * @return the page registry
     */
    @Override
    public @NotNull ImmutableMap<UUID, Page> getPageRegistry() {
        return ImmutableMap.copyOf(uuidPageMap);
    }

    /**
     * Removes a page with the given uuid from the page registry.
     *
     * @param uuid uuid of the page
     * @return page that was removed
     */
    @Override
    public @Nullable Page removePage(@NotNull UUID uuid) {
        return uuidPageMap.remove(uuid);
    }

    /**
     * Creates a new GUI with the given guibuilder.
     *
     * @param builder the builder to use to build the gui
     * @return the gui
     */
    @Override
    public @NotNull GUI createGUI(@NotNull GUIBuilder builder) {
        // todo: make guibuilder
        return null;
    }

    /**
     * Returns an immutable String GUI map. GUI's are mutable.
     *
     * @return an immutable map of gui and unique name
     */
    @Override
    public @NotNull ImmutableMap<String, GUI> getGUIRegistry() {
        return ImmutableMap.copyOf(stringGUIMap);
    }

    /**
     * Removes a gui by the given name.
     *
     * @param guiName the name of the gui
     * @return gui that was removed
     */
    @Override
    public @Nullable GUI removeGUI(String guiName) {
        GUI removed = stringGUIMap.remove(guiName);
        if (removed == null) return null;
        uuidPageMap.entrySet().removeIf(e -> e.getValue().getGUI() == removed);
        return removed;
    }

    /**
     * Saves a template with the suggested unique name.
     *
     * @param items        items to be saved
     * @param templateName the unique name of the template.
     * @return a template that was saved. Null if items map was empty
     */
    @Override
    public @Nullable Template saveTemplate(@NotNull Map<String, Pair<Integer, Item>> items, String templateName) {
        // todo: save template in set folder
        return null;
    }

    /**
     * Reloads pages that are open and are being handled by this GUIEngine.
     */
    @Override
    public void reloadPages() {
        // todo: reload all pages https://www.spigotmc.org/threads/opening-inventories-without-moving-cursor.42138/

    }
}
