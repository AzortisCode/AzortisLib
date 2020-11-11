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

package com.azortis.azortislib.experimental.inventory;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

/**
 * An interface in which the main user object is stored. Used to bind together and interact with multiple related pages.
 */
@SuppressWarnings("unused")
public interface GUI {
    /**
     * Gets the name of the GUI used to identify it.
     *
     * @return the name of the GUI
     */
    @NotNull
    String getName();

    /**
     * All pages in the GUI
     *
     * @return An immutable set of {@link Page} pages in the GUI.
     */
    @NotNull
    Set<Page> getPages();

    /**
     * Gets the number of pages the gui has
     *
     * @return the number of pages the GUI has
     */
    int size();

    /**
     * Gets a page with the associated number.
     *
     * @param number the number of the page, greater than or equal to 1.
     * @return the associated {@link Page} page if one exists.
     * @throws IllegalArgumentException if number < 1
     */
    @Nullable
    Page getPage(int number);

    /**
     * Adds a page to the gui with the given number.
     * Does not override pre-existing pages with the given number.
     *
     * @param number the number of the page, greater than or equal to 1.
     * @param page   A page which is to be added
     * @throws IllegalArgumentException if number < 1
     */
    void addPage(int number, @NotNull Page page);

    /**
     * Adds a page to the given gui with the given number.
     * Optionally override pre-existing pages with the given number.
     *
     * @param number   the number of the page, greater than or equal to 1.
     * @param override whether it should replace an already existing page if one exists with the given number
     * @param page     the page to replace
     * @throws IllegalArgumentException if number < 1
     */
    void addPage(int number, boolean override, @NotNull Page page);

    /**
     * Removes a page with the given number.
     *
     * @param number the number of the page, greater than or equal to 1.
     * @return whether the page was successfully removed.
     * @throws IllegalArgumentException if number < 1
     */
    boolean removePage(int number);

    /**
     * Removes a page with the given number.
     *
     * @param page the page to remove
     * @return whether the page was successfully removed.
     */
    boolean removePage(@NotNull Page page);

}
