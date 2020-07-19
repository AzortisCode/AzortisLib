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

// todo: document this, note to self timeout = how long the animation stays in ticks before being cleared.
// todo: A safe method to use and update animations without causing memory overflow problems because animations will timeout
// todo: if not updated.
public class AnimationObserver<R> {
    private final Animation<R> animation;
    private final int timeout;
    private int currentTimeout;

    public AnimationObserver(Animation<R> animation, int timeout) {
        this.animation = animation;
        this.timeout = timeout;
        this.currentTimeout = timeout;
    }

    public Animation<R> getAnimation() {
        currentTimeout = timeout;
        return animation;
    }

    public R updateAnimation(Object... parameters) {
        currentTimeout--;
        return animation.updateAnimation(parameters);
    }

    public int getCurrentTimeout() {
        return currentTimeout;
    }
}
