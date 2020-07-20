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

package com.azortis.azortislib.experimental.inventory.template;

import com.azortis.azortislib.experimental.inventory.item.Item;

import java.io.Serializable;


public interface Template extends Serializable {
    /**
     * This returns the size that the template can be used for.
     *
     * @return the size of the template
     */
    int getSize();

    /**
     * Returns true if the template can work with any size.
     *
     * @return whether the template can work with any size.
     */
    boolean isAdjustable();

    /**
     * Returns true if the template can fit more than one size, but is not able to do every size.
     *
     * @return whether the template has multiple different sizes.
     */
    boolean isMultiTemplate();

    /**
     * Returns the multi-sizes available to the template if any.
     *
     * @return an integer array of the sizes available to the template if multi sized.
     */
    int[] getMultiSizing();

    /**
     * Returns true if this template is multi paged.
     *
     * @return whether this template has multiple pages.
     */
    boolean multiPaged();

    /**
     * Returns the number of pages available in the template to use if the template is multi paged.
     *
     * @return the pages
     */
    int getPages();

    /**
     * Returns an array of all items in the template. Includes null values.
     *
     * @return an array of all items.
     */
    Item[] getItems();

    /**
     * Returns an array of items based on the size.
     *
     * @param size the size of the inventory to reference
     * @return an array of items based on the size
     */
    Item[] getItems(int size);

    /**
     * Returns an array of items based on the page and size.
     *
     * @param size the size to reference
     * @param page the page to reference
     * @return an array of items based on size and page
     */
    Item[] getItems(int size, int page);
}
