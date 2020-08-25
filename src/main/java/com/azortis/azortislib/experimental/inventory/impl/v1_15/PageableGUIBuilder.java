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

import com.azortis.azortislib.experimental.inventory.PageableGUI;
import com.azortis.azortislib.inventory.item.Item;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;

public class PageableGUIBuilder extends GUIBuilder {
    public int pages;
    public int pageSize;
    private Map<String, Pair<Integer, Pair<Integer, Item>>> pairMap;

    @Override
    public GUIBuilder addPlaceholder(int slot) {
        return null; // todo: make this only add to the first page and make paged alternative for adding to multiple pages
    }

    @Override
    public PageableGUI build() {
        return null; // todo make this return actual pageable gui.
    }


    public static class PageableItemBuilder extends ItemBuilder {
        public int page;

        protected PageableItemBuilder(GUIBuilder builder) {
            super(builder);
        }

        @Override
        public GUIBuilder add() {
            return null; // todo make this add to only actual page and slot
        }
    }
}
