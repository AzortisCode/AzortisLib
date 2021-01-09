/*
 * MIT License
 *
 * Copyright (c) 2021 Azortis
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
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
    protected int guiInt;

    public BasePage(GUI gui, int inventorySize, int guiInt) {
        isValidSize();
        this.gui = gui;
        this.inventorySize = inventorySize;
        inventory = new ArrayList<>();
        for (int i = 0; i < inventorySize; i++) {
            inventory.add(new ButtonSlot(i));
        }
        viewSet = new HashSet<>();
        this.guiInt = guiInt;
    }

    @Override
    public int getGuiInt() {
        return guiInt;
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
