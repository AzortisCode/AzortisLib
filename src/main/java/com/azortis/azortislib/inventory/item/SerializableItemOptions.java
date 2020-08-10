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

package com.azortis.azortislib.inventory.item;


public enum SerializableItemOptions {
    /**
     * An indicator that this item is a filler item and will be filled in any of the empty slots.
     * To prevent spam the slots list won't have every slot a filler is actually in.
     */
    FILLER,
    /**
     * This is a dummy item, which is automatically replaced by the guis with the actual items. i.e. If this were an
     * automatically filling up paginated gui, every item that is marked as a dummy item will be replaced with the actual
     * input after filling up.
     */
    DUMMY_ITEM
}
