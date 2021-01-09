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
