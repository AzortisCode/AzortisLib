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

package com.azortis.azortislib.experimental.inventory.item;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * A class which represents an item that has an action attached to it.
 */
@SuppressWarnings("unused")
public class Button {
    private List<Consumer<ItemClickEvent>> clickAction;
    private Item item;

    /**
     * @param clickAction {@link Consumer<ItemClickEvent>} The action performed when the button is clicked.
     * @param item        {@link Item} the item that is displayed to the user.
     */
    public Button(List<Consumer<ItemClickEvent>> clickAction, Item item) {
        this.clickAction = new ArrayList<>(clickAction);
        this.item = item;
    }

    /**
     * @param item {@link Item} the item that is displayed to the user.
     */
    public Button(Item item) {
        this.clickAction = new ArrayList<>();
        this.item = item;
    }

    /**
     * @return The actions performed when the button is clicked.
     */
    public List<Consumer<ItemClickEvent>> getClickAction() {
        return clickAction;
    }

    /**
     * @param clickAction the actions performed when the button is clicked.
     */
    public void setClickAction(List<Consumer<ItemClickEvent>> clickAction) {
        this.clickAction = clickAction;
    }

    /**
     * Adds a singular click action to the list of actions performed by this button.
     *
     * @param clickAction The action performed when a button is clicked.
     */
    public void addClickAction(Consumer<ItemClickEvent> clickAction) {
        this.clickAction.add(clickAction);
    }

    /**
     * @return {@link Item} The Item associated with this Button.
     */
    public Item getItem() {
        return item;
    }

    /**
     * Sets the associated Item to the one specified.
     *
     * @param item {@link Item} the item to set
     */
    public void setItem(Item item) {
        this.item = item;
    }
}
