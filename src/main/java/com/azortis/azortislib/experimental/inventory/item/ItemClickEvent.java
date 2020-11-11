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
