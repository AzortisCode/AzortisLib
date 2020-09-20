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

import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;

public class PageableTemplate extends Template {
    // todo: fix this, currently this is just a stopgap.
    private Map<String, Pair<Integer, Pair<Integer, Item>>> pairMap;

    /**
     * @param templateConfiguration the gson configuration object if needed
     * @param pairMap               whether to use the map
     * @param isConfigurable        whether to ignore or use the template configuration
     * @param name
     * @param invSize
     * @throws NullPointerException throws if pairMap is null or empty.
     */
    public PageableTemplate(Map<String, Object> templateConfiguration, Map<String, Pair<Integer, Item>> pairMap, boolean isConfigurable, String name, int invSize) throws NullPointerException {
        super(templateConfiguration, pairMap, isConfigurable, name, invSize);
    }

}
