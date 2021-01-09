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
