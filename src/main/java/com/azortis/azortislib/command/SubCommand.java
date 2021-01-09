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

import com.azortis.azortislib.command.executors.ISubCommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.*;

public class SubCommand {

    private final String name;
    private List<String> aliases;

    private Map<String, Alias> aliasesMap;
    private final Command parent;
    private final ISubCommandExecutor executor;

    public SubCommand(String name, List<String> aliases, Command parent, ISubCommandExecutor executor){
        this.name = name.toLowerCase();
        if(aliases != null){
            List<String> processedAliases = new ArrayList<>();
            for (String alias : aliases){
                if(!alias.contains("-f")){
                    processedAliases.add(alias.toLowerCase());
                    break;
                }
                if(this.aliasesMap == null)this.aliasesMap = new HashMap<>();
                Alias aliasFunction = new Alias(alias);
                processedAliases.add(aliasFunction.getAlias().toLowerCase());
                this.aliasesMap.put(aliasFunction.getAlias(), aliasFunction);
            }
            this.aliases = processedAliases;
        }
        this.parent = parent;
        this.executor = executor;
    }

    public boolean execute(CommandSender commandSender, String label, String[] args){
        return executor.onSubCommand(commandSender, this, label, args);
    }

    public String getName() {
        return name;
    }

    public boolean hasAliases(){
        return aliases != null;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public Map<String, Alias> getAliasesMap() {
        return aliasesMap;
    }

    public Alias getAlias(String alias){
        return aliasesMap.get(alias);
    }

    public Command getParent() {
        return parent;
    }

    public ISubCommandExecutor getExecutor() {
        return executor;
    }

}
