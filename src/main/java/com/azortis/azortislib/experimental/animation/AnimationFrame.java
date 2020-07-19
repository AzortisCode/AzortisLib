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

// todo : document this
public class AnimationFrame<R> {
    private final IAction<R> action;
    private final int stayTime;

    /**
     * @param action   the action to use when the frame of animation is needed.
     * @param stayTime how long should this frame last for.
     */
    public AnimationFrame(IAction<R> action, int stayTime) {
        this.action = action;
        this.stayTime = stayTime;
    }

    public int getStayTime() {
        return stayTime;
    }

    /**
     * The method to get the result of an animation.
     *
     * @param parameters other data that the animation requires.
     * @return the result of the animation.
     */
    public R invoke(Object... parameters) {
        return action.invoke(parameters);
    }
}
