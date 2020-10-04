package com.azortis.azortislib.experimental.inventory.item.anim;

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
