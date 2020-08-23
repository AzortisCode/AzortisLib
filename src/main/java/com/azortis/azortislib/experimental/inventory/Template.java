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

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.Map;

public class Template {
    private final Map<String, Pair<Integer, Item>> pairMap = new HashMap<>();
    private final String name;

    /**
     * @param templateConfiguration the gson configuration object if needed
     * @param pairMap               whether to use the map
     * @param isConfigurable        whether to ignore or use the template configuration
     * @throws NullPointerException throws if pairMap is null or empty.
     */
    public Template(Map<String, Object> templateConfiguration, Map<String, Pair<Integer, Item>> pairMap, boolean isConfigurable, String name)
            throws NullPointerException {
        this.name = name;
        CONFIGURE:
        {
            if (isConfigurable) {
                // check template configuration and pair map
                if (templateConfiguration == null || pairMap == null || pairMap.isEmpty()) {
                    // obviously something is wrong so we can't use the configured version and try to use defaults
                    break CONFIGURE;
                }
                for (String s : pairMap.keySet()) {
                    Map<String, Object> values = (Map<String, Object>) templateConfiguration.get(s);
                    if (values == null || values.isEmpty() || !values.containsKey("slot") || !values.containsKey("item"))
                        continue;
                    Item item = loadItem((Map<String, Object>) values.get("item"));
                    int slot = (int) values.get("slot");
                    pairMap.put(s, new MutablePair<>(slot, item));
                }
                return;
            }
        }
        // check pair map
        if (pairMap == null || pairMap.isEmpty()) {
            // We can't use defaults because defaults don't exist or are broken.
            throw new NullPointerException("Default Map(PairMap) is null and/or empty!");
        }
        // we copy the pair map given into existence.
        this.pairMap.putAll(pairMap);

    }

    private static Item loadItem(Map<String, Object> jsonItem) {
        // todo: complete this - nbt data support
        return null;
    }

    private static Map<String, Object> unloadItem(Item item) {
        // todo: complete this - nbt data support
        return null;
    }

    public String getName() {
        return name;
    }

    /**
     * Returns the items of the template in array form - and immutable from the internal array of the template.
     *
     * @return immutable array of items - not including actions (actions are mutable)
     */
    public Item[] getItems() {
        Item[] arr = new Item[pairMap.size()];
        pairMap.values().forEach(integerItemPair -> arr[integerItemPair.getKey()] = integerItemPair.getValue());
        return arr;
    }

    public Map<String, Pair<Integer, Item>> getPairMap() {
        return pairMap;
    }
}
