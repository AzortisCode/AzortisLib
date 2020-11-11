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

import com.google.common.base.Preconditions;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

@SuppressWarnings("unused")
public class ButtonSlot {
    private final int slot;
    private Button button;

    /**
     * @param button the button to use
     * @param slot   the slot to use
     * @throws IllegalArgumentException if slot < 1
     */
    public ButtonSlot(Button button, int slot) {
        isValidSlot(slot);
        this.button = Objects.requireNonNull(button, "Tried creating a ButtonSlot, but was null!");
        isValidSlot(slot);
        this.slot = slot;
    }

    /**
     * @param slot the slot to use
     * @throws IllegalArgumentException if slot < 1
     */
    public ButtonSlot(int slot) {
        isValidSlot(slot);
        this.slot = slot;
        this.button = null;
    }

    /**
     * Determines if a slot is a valid slot in an inventory.
     * Inventories usually start at slot 0 due to arrays, but for sanity and normal counting it starts at 1 for us.
     *
     * @param slot the slot to check
     */
    private void isValidSlot(int slot) {
        Preconditions.checkArgument(!(slot < 1),
                "The slot %s is invalid, the slot must be >= 1.", slot);
    }

    /**
     * @return {@link Button} linked to this ButtonSlot, null if no button is linked
     */
    @Nullable
    public Button getButton() {
        return button;
    }

    /**
     * Sets the button associated with this ButtonSlot.
     *
     * @param button {@link Button} to set this button slot to.
     */
    public void setButton(@Nullable Button button) {
        this.button = button;
    }

    /**
     *
     * @return the slot associated with this ButtonSlot
     */
    public int getSlot() {
        return slot;
    }
}
