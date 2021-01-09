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

package com.azortis.azortislib.command.builders;

import com.azortis.azortislib.command.Command;
import com.azortis.azortislib.command.executors.ICommandExecutor;
import com.azortis.azortislib.command.executors.ITabCompleter;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CommandBuilder {

    //Command information
    private String name;
    private String description;
    private String usage;
    private List<String> aliases;
    private String permission;
    private Plugin plugin;

    //Execution classes
    private ICommandExecutor executor;
    private ITabCompleter tabCompleter;
    private Collection<SubCommandBuilder> subCommands;

    public CommandBuilder setName(@NotNull String name) {
        this.name = name;
        return this;
    }

    public CommandBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public CommandBuilder setUsage(String usage) {
        this.usage = usage;
        return this;
    }

    public CommandBuilder addAlias(String alias){
        if(alias == null)return this;
        if(this.aliases == null)this.aliases = new ArrayList<>();
        aliases.add(alias);
        return this;
    }

    public CommandBuilder addAliases(String... aliases){
        if(aliases == null)return this;
        if(this.aliases == null)this.aliases = new ArrayList<>();
        this.aliases.addAll(Arrays.asList(aliases));
        return this;
    }

    public CommandBuilder addAliases(List<String> aliases){
        if(aliases == null)return this;
        if(this.aliases == null)this.aliases = new ArrayList<>();
        this.aliases.addAll(aliases);
        return this;
    }

    public CommandBuilder setPermission(String permission) {
        this.permission = permission;
        return this;
    }

    public CommandBuilder setPlugin(Plugin plugin){
        this.plugin = plugin;
        return this;
    }

    public CommandBuilder setExecutor(@NotNull ICommandExecutor executor){
        this.executor = executor;
        return this;
    }

    public CommandBuilder setTabCompleter(@NotNull ITabCompleter tabCompleter){
        this.tabCompleter = tabCompleter;
        return this;
    }

    public CommandBuilder addSubCommand(SubCommandBuilder subCommand){
        if(this.subCommands == null)this.subCommands = new ArrayList<>();
        this.subCommands.add(subCommand);
        return this;
    }

    public CommandBuilder addSubCommands(SubCommandBuilder... subCommands){
        if(this.subCommands == null)this.subCommands = new ArrayList<>();
        this.subCommands.addAll(Arrays.asList(subCommands));
        return this;
    }

    public CommandBuilder addSubCommands(Collection<SubCommandBuilder> subCommands){
        if(this.subCommands == null)this.subCommands = new ArrayList<>();
        this.subCommands.addAll(subCommands);
        return this;
    }

    public Command build(){
        return new Command(this.name, this.description, this.usage, this.aliases, this.permission, this.plugin, this.executor
                , this.tabCompleter, this.subCommands);
    }

}
