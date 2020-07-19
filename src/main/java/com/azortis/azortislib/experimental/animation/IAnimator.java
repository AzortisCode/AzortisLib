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

// todo: document this, make note that people must remove animations once they are finished with them else it could
// todo: create an memory overflow error because objects would never be removed, EVER!!!!
public interface IAnimator {
    <R, V> void addAnimation(AnimationObserver<R> animationObserver, Supplier<V> supplier);

    <R> void removeAnimation(AnimationObserver<R> animationObserver);

    <R, V> void addAnimation(Animation<R> animation, Supplier<V> supplier);

    <R> void removeAnimation(Animation<R> animation);

    void clearAnimations();
}
