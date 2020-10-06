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

package com.azortis.azortislib.inventory.item.anim;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.function.BiConsumer;

public class Animation<T> implements Serializable {
    @SerializedName("Frames")
    private T[] frames;
    @SerializedName("Interval")
    private int tickInterval;
    private BiConsumer<Animation<T>, Integer> action;
    private transient int currentFrame;
    private transient int currentTick;

    public Animation(T[] frames, int tickInterval, BiConsumer<Animation<T>, Integer> action) {
        this.frames = frames;
        this.tickInterval = tickInterval;
        this.action = action;
        currentFrame = 0;
        currentTick = 0;
    }

    public void update() {
        currentTick++;
        if (currentTick >= tickInterval && tickInterval > 0) {
            currentTick = 0;
            currentFrame++;
            action.accept(this, currentFrame);
        }
    }

    public T getCurrentValue() {
        return frames[currentFrame];
    }

    public void setAction(BiConsumer<Animation<T>, Integer> action) {
        this.action = action;
    }

    public T[] getFrames() {
        return frames;
    }

    public void setFrames(T[] frames) {
        this.frames = frames;
    }

    public int getTickInterval() {
        return tickInterval;
    }

    public void setTickInterval(int tickInterval) {
        this.tickInterval = tickInterval;
    }
}
