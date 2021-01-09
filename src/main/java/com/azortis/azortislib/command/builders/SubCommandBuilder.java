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
import com.azortis.azortislib.command.SubCommand;
import com.azortis.azortislib.command.executors.ISubCommandExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubCommandBuilder {

    private String name;
    private List<String> aliases;
    private Command parent;
    private ISubCommandExecutor executor;

    public SubCommandBuilder setName(String name){
        this.name = name;
        return this;
    }

    public SubCommandBuilder addAlias(String alias){
        if(this.aliases == null)this.aliases = new ArrayList<>();
        aliases.add(alias);
        return this;
    }

    public SubCommandBuilder addAliases(String... aliases){
        if(this.aliases == null)this.aliases = new ArrayList<>();
        this.aliases.addAll(Arrays.asList(aliases));
        return this;
    }

    public SubCommandBuilder addAliases(List<String> aliases){
        if(this.aliases == null)this.aliases = new ArrayList<>();
        this.aliases.addAll(aliases);
        return this;
    }

    public SubCommandBuilder setParent(Command parent){
        this.parent = parent;
        return this;
    }

    public SubCommandBuilder setExecutor(ISubCommandExecutor executor){
        this.executor = executor;
        return this;
    }

    public SubCommand build(){
        return new SubCommand(name, aliases, parent, executor);
    }

}
