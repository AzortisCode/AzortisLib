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

package com.azortis.azortislib.command;

import com.azortis.azortislib.reflection.Reflections;
import com.azortis.azortislib.utils.FormatUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.SimpleCommandMap;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class CommandInjector {
    private static final String VERSION = Bukkit.getServer().getClass().getName()
            .replaceAll("[^0-9_]", "");
    private static final String V1_16 = "1_16_1";

    @SuppressWarnings("unchecked")
    public static void injectCommand(String fallBackPrefix, Command command, boolean override) {
        try {
            Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
            SimpleCommandMap commandMap = (SimpleCommandMap) commandMapField.get(Bukkit.getServer());
            if (override && commandMap.getCommand(command.getName()) != null) {
                org.bukkit.command.Command conflictCommand = commandMap.getCommand(command.getName());
                List<String> conflictCommandAliases = new ArrayList<>();
                assert conflictCommand != null;
                if (!conflictCommand.getAliases().isEmpty())
                    conflictCommandAliases.addAll(conflictCommand.getAliases());
                Map<String, org.bukkit.command.Command> knownCommands;
                Field knownCommandsField = null;
                if (isVersionLaterThanCurrent()) {
                    // Version is 1.16 or later - use new method
                    knownCommands = (Map<String, org.bukkit.command.Command>) Reflections
                            .getMethod(commandMap.getClass(), "getKnownCommands").invoke(commandMap);
                } else {
                    knownCommandsField = commandMap.getClass().getDeclaredField("knownCommands");
                    knownCommandsField.setAccessible(true);
                    knownCommands = (Map<String, org.bukkit.command.Command>) knownCommandsField.get(commandMap);
                }
                knownCommands.remove(conflictCommand.getName());
                if (!conflictCommandAliases.isEmpty()) {
                    for (String alias : conflictCommandAliases) {
                        knownCommands.remove(alias);
                    }
                }
                if (knownCommandsField != null)
                    knownCommandsField.setAccessible(false);

            }
            commandMap.register(fallBackPrefix, command.getBukkitCommand());
            commandMapField.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void removeCommand(String command) {
        try {
            Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
            SimpleCommandMap commandMap = (SimpleCommandMap) commandMapField.get(Bukkit.getServer());

            Map<String, org.bukkit.command.Command> knownCommands;
            Field knownCommandsField = null;
            if (isVersionLaterThanCurrent()) {
                // Version is 1.16 or later - use new method
                knownCommands = (Map<String, org.bukkit.command.Command>) Reflections
                        .getMethod(commandMap.getClass(), "getKnownCommands").invoke(commandMap);
            } else {
                knownCommandsField = commandMap.getClass().getDeclaredField("knownCommands");
                knownCommandsField.setAccessible(true);
                knownCommands = (Map<String, org.bukkit.command.Command>) knownCommandsField.get(commandMap);
            }
            knownCommands.remove(command);
            if (knownCommandsField != null)
                knownCommandsField.setAccessible(false);

            commandMapField.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            ex.printStackTrace();
        }
    }

    private static boolean isVersionLaterThanCurrent() {
        String[] checked = CommandInjector.V1_16.split("_");
        String[] current = CommandInjector.VERSION.split("_");
        FormatUtil.equalizeStringArray(checked, current);
        String sChecked = checked[0] + checked[1] + checked[2];
        String sCurrent = current[0] + current[1] + current[2];
        return sCurrent.compareTo(sChecked) < 0;
        // If returns -1, return true, if anything else false


    }

}
