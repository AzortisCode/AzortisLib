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

import com.azortis.azortislib.experimental.inventory.GUI;
import com.azortis.azortislib.experimental.inventory.Item;
import com.azortis.azortislib.experimental.inventory.Page;
import com.azortis.azortislib.experimental.inventory.Template;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class GUIBuilder {
    public final Map<String, Pair<Integer, Item>> pairMap;
    public boolean isGlobal;
    public boolean isConfigurable;
    public String uniqueName;
    public String inventoryTitle;
    public Consumer<Page> update;
    public int inventorySize;
    private Class<? extends GUI> buildCustom;

    public GUIBuilder() {
        pairMap = new HashMap<>();
    }

    public GUIBuilder with(Consumer<GUIBuilder> function) {
        function.accept(this);
        return this;
    }

    public ItemBuilder item() {
        return new ItemBuilder(this);
    }

    public GUI build() {
        Template template = new Template(null, pairMap, isConfigurable, uniqueName, inventorySize);
        GUI gui;
        if (buildCustom != null) {
            try {
                gui = buildCustom.getConstructor(Template.class, String.class, boolean.class, boolean.class, Consumer.class)
                        .newInstance(template, uniqueName, isGlobal, isConfigurable, update);
            } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
                gui = new GUIImpl(template, uniqueName, isGlobal, isConfigurable, update);
                System.out.println("Warning - Could not find or instantiate constructor for class "
                        + buildCustom + ", using default implementation for gui.");
                e.printStackTrace();
            }
        } else {
            gui = new GUIImpl(template, uniqueName, isGlobal, isConfigurable, update);
        }
        gui.setInventoryName(inventoryTitle);
        return gui;
    }

    public GUIBuilder buildCustom(Class<? extends GUI> buildCustom) {
        this.buildCustom = buildCustom;
        return this;
    }

    public GUIBuilder addPlaceholder(int slot) {
        pairMap.values().removeIf((integerItemPair -> integerItemPair.getKey() == slot));
        pairMap.put("placeholder-" + slot, new MutablePair<>(slot, new Item(true)));
        return this;
    }

    public static class ItemBuilder {
        private final GUIBuilder builder;
        public ItemStack itemStack;
        public Consumer<InventoryClickEvent> action;
        public String itemName;
        public int slot;

        protected ItemBuilder(GUIBuilder builder) {
            this.builder = builder;

        }

        public ItemBuilder with(Consumer<ItemBuilder> function) {
            function.accept(this);
            return this;
        }

        public GUIBuilder add() {
            if (itemStack == null) return builder;
            builder.with($ -> {
                $.pairMap.values().removeIf((integerItemPair -> integerItemPair.getKey() == slot));
                $.pairMap.put(itemName, new MutablePair<>(slot, new Item(itemStack, action)));
            });
            return builder;
        }
    }
}
