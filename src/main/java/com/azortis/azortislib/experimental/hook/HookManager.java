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

package com.azortis.azortislib.experimental.hook;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class HookManager {
    private final Map<String, Hook> hookMap;
    private final JavaPlugin plugin;

    public HookManager(JavaPlugin plugin) {
        hookMap = new HashMap<>();
        this.plugin = plugin;
    }

    public HookManager addHook(Hook hook) {
        hookMap.put(hook.getName(), hook);
        return this;
    }

    public HookManager removeHook(Hook hook) {
        hookMap.remove(hook.getName());
        return this;
    }

    public HookManager removeHook(String name) {
        hookMap.remove(name);
        return this;
    }

    public HookManager addHook(Hook... hooks) {
        for (Hook hook : hooks) {
            hookMap.put(hook.getName(), hook);
        }
        return this;
    }

    public HookManager removeHook(Hook... hooks) {
        for (Hook hook : hooks) {
            hookMap.remove(hook.getName());
        }
        return this;
    }

    public HookManager removeHook(String... names) {
        for (String name : names) {
            hookMap.remove(name);
        }
        return this;
    }

    public HookManager addHook(Collection<Hook> hooks) {
        for (Hook hook : hooks) {
            hookMap.put(hook.getName(), hook);
        }
        return this;
    }

    public HookManager removeHooks(Collection<Hook> hooks) {
        for (Hook hook : hooks) {
            hookMap.remove(hook.getName());
        }
        return this;
    }

    public HookManager removeHooksString(Collection<String> names) {
        for (String name : names) {
            hookMap.remove(name);
        }
        return this;
    }

    public Hook getHook(String name) {
        return hookMap.get(name);
    }

    public Collection<Hook> matchHook(String name) {
        return hookMap.values().stream().filter(s -> s.getName().contains(name)).collect(Collectors.toCollection(ArrayList::new));
    }

    public Collection<Hook> evalHooks() {
        return hookMap.values().stream().filter(this::evalHook).collect(Collectors.toCollection(ArrayList::new));
    }

    public boolean evalHook(String name) {
        Hook hook = hookMap.get(name);
        return hook != null && hook.hook(plugin);
    }

    public boolean evalHook(Hook hook) {
        hookMap.putIfAbsent(hook.getName(), hook);
        return hook.hook(plugin);
    }

}
