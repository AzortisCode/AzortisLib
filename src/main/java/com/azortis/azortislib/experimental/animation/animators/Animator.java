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

package com.azortis.azortislib.experimental.animation.animators;


import com.azortis.azortislib.experimental.animation.Animation;
import com.azortis.azortislib.experimental.animation.AnimationObserver;
import com.azortis.azortislib.experimental.animation.IAnimator;
import com.azortis.azortislib.experimental.animation.Supplier;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// todo: document this, this is a singleton manager, only one animator should exist at a time.
public class Animator implements IAnimator, Runnable {
    private static Animator instance;
    private final Map<AnimationObserver<?>, Supplier<?>> observers;
    private final Map<Animation<?>, Supplier<?>> animations;
    private final int updateDelay;

    private Animator(int updateDelay) {
        this.updateDelay = Math.max(updateDelay, 100);
        observers = new ConcurrentHashMap<>();
        animations = new ConcurrentHashMap<>();

    }

    public static Animator getInstance(int updateDelay) {
        if (instance == null) instance = new Animator(updateDelay);
        return instance;
    }

    @Override
    public void clearAnimations() {
        animations.clear();
        observers.clear();
    }

    @Override
    public void run() {
        observers.keySet().removeIf(animationObserver -> animationObserver.getCurrentTimeout() <= 0);
        observers.keySet().forEach(animationObserver ->
                animationObserver.updateAnimation((Object[]) observers.get(animationObserver).supplyArguments()));
        animations.forEach((animation, supplier) -> animation.updateAnimation((Object[]) supplier.supplyArguments()));
        try {
            Thread.sleep(updateDelay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <R, V> void addAnimation(AnimationObserver<R> animationObserver, Supplier<V> supplier) {
        observers.put(animationObserver, supplier);
    }

    @Override
    public <R> void removeAnimation(AnimationObserver<R> animationObserver) {
        observers.remove(animationObserver);
    }

    @Override
    public <R, V> void addAnimation(Animation<R> animation, Supplier<V> supplier) {
        animations.put(animation, supplier);
    }

    @Override
    public <R> void removeAnimation(Animation<R> animation) {
        animations.remove(animation);
    }


}
