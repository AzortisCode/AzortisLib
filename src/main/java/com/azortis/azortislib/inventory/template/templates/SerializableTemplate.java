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

package com.azortis.azortislib.inventory.template.templates;

import com.azortis.azortislib.inventory.item.Item;
import com.azortis.azortislib.inventory.item.SerializableItem;
import com.azortis.azortislib.inventory.template.Template;
import com.google.gson.JsonObject;

public class SerializableTemplate implements Template {
    private SerializableItem[] serializableItems;
    private boolean isAdjustable;
    private boolean isMultiTemplate;
    private int[] supportedSizes;
    private boolean isPaginated;
    private int pages;
    private int size;

    public SerializableTemplate(JsonObject templateObject) {
        // in the constructor you build the actual template from the json object
    }

    public SerializableTemplate(SerializableItem[] serializableItems, boolean isAdjustable, boolean isMultiTemplate, int[] supportedSizes, boolean isPaginated, int pages, int size) {
        this.serializableItems = serializableItems;
        this.isAdjustable = isAdjustable;
        this.isMultiTemplate = isMultiTemplate;
        this.supportedSizes = supportedSizes;
        this.isPaginated = isPaginated;
        this.pages = pages;
        this.size = size;
    }

    /**
     * This returns the size that the template can be used for.
     *
     * @return the size of the template
     */
    @Override
    public int getSize() {
        return -1;
    }

    /**
     * Returns true if the template can work with any size.
     *
     * @return whether the template can work with any size.
     */
    @Override
    public boolean isAdjustable() {
        return true;
    }

    /**
     * Returns true if the template can fit more than one size, but is not able to do every size.
     *
     * @return whether the template has multiple different sizes.
     */
    @Override
    public boolean isMultiTemplate() {
        return false;
    }

    /**
     * Returns the multi-sizes available to the template if any.
     *
     * @return an integer array of the sizes available to the template if multi sized.
     */
    @Override
    public int[] getMultiSizing() {
        return new int[0];
    }

    /**
     * Returns true if this template is multi paged.
     *
     * @return whether this template has multiple pages.
     */
    @Override
    public boolean multiPaged() {
        return false;
    }

    /**
     * Returns the number of pages available in the template to use if the template is multi paged.
     *
     * @return the pages
     */
    @Override
    public int getPages() {
        return 0;
    }

    /**
     * Returns an array of all items in the template. Includes null values.
     *
     * @return an array of all items.
     */
    @Override
    public Item[] getItems() {
        return serializableItems;
    }

    /**
     * Returns an array of items based on the size.
     *
     * @param size the size of the inventory to reference
     * @return an array of items based on the size
     */
    @Override
    public Item[] getItems(int size) {
        // todo: size logic
        return new Item[0];
    }

    /**
     * Returns an array of items based on the page and size.
     *
     * @param size the size to reference
     * @param page the page to reference
     * @return an array of items based on size and page
     */
    @Override
    public Item[] getItems(int size, int page) {
        // todo: size logic
        return new Item[0];
    }
}
