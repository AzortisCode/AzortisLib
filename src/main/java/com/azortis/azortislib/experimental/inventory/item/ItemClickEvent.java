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

import com.azortis.azortislib.experimental.inventory.Page;
import com.azortis.azortislib.experimental.inventory.View;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * An interface which represents an event which is passed whenever a user clicks on an button.
 * and contains multiple methods used to interact with the view and click event.
 */
@SuppressWarnings("unused")
public class ItemClickEvent {
    private final InventoryClickEvent event;
    private final View view;

    public ItemClickEvent(InventoryClickEvent event, View view) {
        this.event = event;
        this.view = view;
    }

    /**
     * Gets the view the button was clicked in.
     *
     * @return {@link View} the view which the button was clicked in.
     */
    public View getView() {
        return view;
    }

    /**
     * Whether the view is a global or per-player based view.
     *
     * @return whether the view is global or unique.
     */
    public boolean isGlobal() {
        return getView().getPage().isGlobal();
    }

    /**
     * @return {@link InventoryClickEvent} the event called when the button was clicked.
     */
    public InventoryClickEvent getEvent() {
        return event;
    }

    /**
     * A method to shortcut getView#getPage()
     *
     * @return {@link Page} the page of the view the button is in.
     */
    public Page getPage() {
        return getView().getPage();
    }

    /**
     * A shortcut used for ClickAction#from(getEvent()#getClick)
     *
     * @return {@link ClickAction} the action the player used when clicking the button.
     */
    public ClickAction getClick() {
        return ClickAction.from(getEvent().getClick());
    }

    /**
     * A shortcut used to get the button clicked
     *
     * @return {@link Button} the button clicked.
     */
    public Button getClicked() {
        return getView().getPage().getButton(getEvent().getSlot());
    }

    /**
     * A shortcut to get the player who clicked the button.
     *
     * @return {@link Player} get the player who clicked
     */
    public Player whoClicked() {
        return (Player) getEvent().getWhoClicked();
    }
}
