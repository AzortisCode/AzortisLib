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
