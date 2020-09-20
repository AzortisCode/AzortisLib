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
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class PageableGUIBuilder extends GUIBuilder {
    private final Map<String, Pair<Integer, Pair<Integer, Item>>> pairMap;
    public int pages;

    public PageableGUIBuilder() {
        this.pairMap = new HashMap<>();
    }

    @Override
    public PageableGUIBuilder addPlaceholder(int slot) {
        pairMap.values().removeIf((integerItemPair -> integerItemPair.getKey() == slot));
        pairMap.put("placeholder-" + slot + "-" + 1,
                new MutablePair<>(1,
                        new MutablePair<>(slot, new com.azortis.azortislib.experimental.inventory.Item(true))));
        return this;
    }

    @Override
    public PageableGUI build() {
        Map<String, Pair<Integer, Item>> transformedMap = new HashMap<>();
        pairMap.forEach((s, integerPairPair) -> transformedMap.put(s,
                new MutablePair<>((integerPairPair.getKey() * inventorySize) + integerPairPair.getValue().getKey(),
                        integerPairPair.getValue().getValue())));
        PageableTemplate template = new PageableTemplate(null, transformedMap, isConfigurable, inventoryTitle,
                inventorySize);
        PageableGUI gui;
        if (buildCustom != null) {
            try {
                gui = (PageableGUI) buildCustom.getConstructor(Template.class, String.class, boolean.class, boolean.class, Consumer.class, int.class, int.class)
                        .newInstance(template, uniqueName, isGlobal, isConfigurable, update, pages, inventorySize);
            } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
                gui = new PageableGUIImpl(template, uniqueName, isGlobal, isConfigurable, update, pages, inventorySize);
                System.out.println("Warning - Could not find or instantiate constructor for class "
                        + buildCustom + ", using default implementation for pageable gui.");
                e.printStackTrace();
            }
        } else {
            gui = new PageableGUIImpl(template, uniqueName, isGlobal, isConfigurable, update, pages, inventorySize);
        }
        gui.setInventoryName(inventoryTitle);
        return gui;
    }

    @Override
    public ItemBuilder item() {
        return new PageableItemBuilder(this);
    }

    public static class PageableItemBuilder extends ItemBuilder {
        public int page;

        protected PageableItemBuilder(GUIBuilder builder) {
            super(builder);
        }

        @Override
        public PageableGUIBuilder add() {
            if (itemStack == null) return (PageableGUIBuilder) builder;
            builder.with($ -> ((PageableGUIBuilder) $).pairMap.put(itemName,
                    new MutablePair<>(page, new MutablePair<>(slot, new Item(itemStack, action)))));
            return (PageableGUIBuilder) builder;
        }
    }
}
