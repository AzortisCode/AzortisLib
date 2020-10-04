package com.azortis.azortislib.experimental.inventory;

import java.util.ArrayList;
import java.util.List;

public class GUI {
    private final List<Page> pages;
    private final String name;

    public GUI(String name) {
        this.name = name;
        pages = new ArrayList<>();
    }

    public List<Page> getPages() {
        return pages;
    }

    public String getName() {
        return name;
    }
}
