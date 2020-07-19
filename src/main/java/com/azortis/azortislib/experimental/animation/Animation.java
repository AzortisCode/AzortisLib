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

package com.azortis.azortislib.experimental.animation;

import java.util.function.Predicate;

// todo: document this, note updateResponse must be async friendly
public class Animation<R> {
    private final AnimationFrame<R>[] animationFrames;
    private final Predicate<R> updateResponse;
    private final ResponseFlag flag;
    private int currentFrame;
    private int counter;
    private R lastValue;

    public Animation(AnimationFrame<R>[] animationFrames, Predicate<R> updateResponse, ResponseFlag flag) {
        this.animationFrames = animationFrames;
        this.updateResponse = updateResponse;
        this.flag = flag;
        currentFrame = 0;
        counter = 0;
    }

    public R updateAnimation(Object... parameters) {
        int frameCounter;
        if (currentFrame + 1 >= animationFrames.length) frameCounter = animationFrames[0].getStayTime();
        else frameCounter = animationFrames[currentFrame + 1].getStayTime();
        if (frameCounter <= counter) {
            if (currentFrame + 1 < animationFrames.length)
                currentFrame++;
            else
                currentFrame = 0;
            counter = 0;
        } else
            counter++;
        lastValue = animationFrames[currentFrame].invoke(parameters);
        if (counter == 0 && flag == ResponseFlag.AFTER_FRAME_CHANGE) updateResponse.test(lastValue);
        else if (flag == ResponseFlag.ON_UPDATE) updateResponse.test(lastValue);
        return lastValue;
    }

    public R getLastValue() {
        return lastValue;
    }
}
