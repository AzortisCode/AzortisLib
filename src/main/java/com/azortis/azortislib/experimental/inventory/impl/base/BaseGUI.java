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

package com.azortis.azortislib.experimental.inventory.impl.base;

import com.azortis.azortislib.experimental.inventory.GUI;
import com.azortis.azortislib.experimental.inventory.Page;
import com.google.common.collect.ImmutableSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("unused")
public abstract class BaseGUI implements GUI {
    protected String name;
    protected Map<Integer, Page> pageMap;

    public BaseGUI(String name) {
        this.name = name;
        pageMap = new HashMap<>();
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public final @NotNull Set<Page> getPages() {
        return ImmutableSet.copyOf(pageMap.values());
    }

    @Override
    public int size() {
        return pageMap.size();
    }

    @Override
    public @Nullable Page getPage(int number) {
        return pageMap.get(number);
    }

    @Override
    public void addPage(int number, @NotNull Page page) {
        pageMap.putIfAbsent(number, page);
    }

    @Override
    public void addPage(int number, boolean override, @NotNull Page page) {
        pageMap.put(number, page);
    }

    @Override
    public boolean removePage(int number) {
        return pageMap.remove(number) != null;
    }

    @Override
    public boolean removePage(@NotNull Page page) {
        for (int i : pageMap.keySet()) {
            if (pageMap.get(i) == page) return pageMap.remove(i) != null;
        }
        return false;
    }
}
