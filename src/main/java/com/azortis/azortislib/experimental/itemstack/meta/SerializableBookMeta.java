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

package com.azortis.azortislib.experimental.itemstack.meta;

import org.bukkit.inventory.meta.BookMeta;

import java.util.ArrayList;

public class SerializableBookMeta {

    private String title;
    private String author;
    private BookMeta.Generation generation;
    private ArrayList<String> pages;

    public SerializableBookMeta(BookMeta bookMeta){
        if(bookMeta.hasTitle())title = bookMeta.getTitle();
        if(bookMeta.hasAuthor())author = bookMeta.getAuthor();
        if(bookMeta.hasGeneration())generation = bookMeta.getGeneration();
        if(bookMeta.hasPages())pages = new ArrayList<>(bookMeta.getPages());
    }

    public void applyMeta(BookMeta bookMeta){
        if(title != null)bookMeta.setTitle(title);
        if(author != null)bookMeta.setAuthor(author);
        if(generation != null)bookMeta.setGeneration(generation);
        if(pages != null)bookMeta.setPages(pages);
    }

}
