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
