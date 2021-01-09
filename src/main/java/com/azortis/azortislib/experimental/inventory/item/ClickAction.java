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

import org.bukkit.event.inventory.ClickType;

import java.util.Arrays;

/**
 * Represents an action when a player clicks in an inventory.
 */
public enum ClickAction {
    /**
     * An unknown click type.
     */
    UNKNOWN(null),
    /**
     * A right click
     */
    RIGHT(ClickType.RIGHT),
    /**
     * A left click
     */
    LEFT(ClickType.LEFT),
    /**
     * A middle click
     */
    MIDDLE(ClickType.MIDDLE),
    /**
     * The Q or drop button
     */
    Q(ClickType.DROP),
    /**
     * The Shift Right click
     */
    SHIFT_RIGHT(ClickType.SHIFT_RIGHT),
    /**
     * Shift Left click
     */
    SHIFT_LEFT(ClickType.SHIFT_LEFT),
    /**
     * Ctrl + Q click/drop entire stack.
     */
    CTRL_Q(ClickType.CONTROL_DROP);


    private final ClickType type;

    ClickAction(ClickType type) {
        this.type = type;
    }

    /**
     * Changes from spigot's {@link ClickType} to {@link ClickAction}
     *
     * @param type the original {@link ClickType}
     * @return a {@link ClickAction} which is associated with the {@link ClickType} given or unknown.
     */
    public static ClickAction from(ClickType type) {
        return Arrays.stream(values()).filter(a -> a.type == type).findFirst().orElse(UNKNOWN);
    }
}
