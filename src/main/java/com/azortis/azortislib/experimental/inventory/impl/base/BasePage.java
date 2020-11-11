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
import com.azortis.azortislib.experimental.inventory.View;
import com.azortis.azortislib.experimental.inventory.item.Button;
import com.azortis.azortislib.experimental.inventory.item.ButtonSlot;
import com.azortis.azortislib.utils.FormatUtil;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;

@SuppressWarnings("unused")
public class BasePage implements Page {
    protected final GUI gui;
    protected final int inventorySize;
    protected String inventoryName;
    protected boolean isGlobal;
    protected Set<View> viewSet;
    protected BiConsumer<InventoryCloseEvent, View> closeEvent;
    protected List<ButtonSlot> inventory;

    public BasePage(GUI gui, int inventorySize) {
        isValidSize();
        this.gui = gui;
        this.inventorySize = inventorySize;
        inventory = new ArrayList<>();
        for (int i = 0; i < inventorySize; i++) {
            inventory.add(new ButtonSlot(i));
        }
        viewSet = new HashSet<>();
    }

    private void isValidSize() {
        Preconditions.checkArgument(inventorySize % 9 == 0, "Inventory size %s is not valid," +
                " must be divisible by 9", inventorySize);
    }

    @Override
    public String getInventoryName() {
        return inventoryName;
    }

    @Override
    public void setInventoryName(String name) {
        this.inventoryName = name;
    }

    @Override
    public boolean isGlobal() {
        return isGlobal;
    }

    @Override
    public void setGlobal(boolean global) {
        this.isGlobal = global;
    }

    @Override
    public @NotNull Set<View> getViews() {
        return ImmutableSet.copyOf(viewSet);
    }

    @Override
    public int inventorySize() {
        return this.inventorySize;
    }

    @Override
    public @NotNull BiConsumer<InventoryCloseEvent, View> closeAction() {
        return this.closeEvent;
    }

    @Override
    public void setCloseAction(@NotNull BiConsumer<InventoryCloseEvent, View> closeAction) {
        this.closeEvent = closeAction;
    }

    @Override
    public void disposeView(@NotNull View view) {
        viewSet.remove(view);
    }

    @Override
    public @NotNull GUI getGUI() {
        return this.gui;
    }

    @Override
    public @NotNull List<ButtonSlot> items() {
        return this.inventory;
    }

    @Override
    public void addButton(int slot, @NotNull Button button) {
        this.inventory.get(slot - 1).setButton(button);
    }

    @Override
    public void removeButton(int slot) {
        this.inventory.get(slot - 1).setButton(null);
    }

    @Override
    public Button getButton(int slot) {
        return this.inventory.get(slot - 1).getButton();
    }

    @Override
    public Inventory getInventory() {
        View view;
        if (isGlobal) {
            if (viewSet.size() < 1) {
                view = new BaseView(this);
                viewSet.add(view);
            } else {
                return viewSet.iterator().next().getInventory();
            }
        } else {
            view = new BaseView(this);
            viewSet.add(view);
        }
        Inventory inventory = Bukkit.createInventory(view, inventorySize, FormatUtil.color(inventoryName));
        ItemStack[] contents = new ItemStack[inventorySize];
        for (int i = 0; i < items().size(); i++) {
            Button button = items().get(i).getButton();
            if (button != null && button.getItem() != null) {
                contents[i] = button.getItem().getItemStack();
            }
        }
        inventory.setContents(contents);
        view.setInventory(inventory);
        return inventory;
    }
}
