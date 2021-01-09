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
