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
