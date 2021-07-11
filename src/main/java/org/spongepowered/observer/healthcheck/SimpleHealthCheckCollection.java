/*
 * This file is part of observer, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.observer.healthcheck;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SimpleHealthCheckCollection implements HealthCheckCollection {
    private final ConcurrentMap<String, HealthProbe> probes = new ConcurrentHashMap<>();

    @Override
    public void registerProbe(String id, SyncHealthProbe probe) {
        probes.putIfAbsent(id, probe);
    }

    @Override
    public void registerProbe(String id, AsyncHealthProbe probe) {
        probes.putIfAbsent(id, probe);
    }

    @Override
    public void unregisterProbe(String id) {
        probes.remove(id);
    }

    @Override
    public Map<String, HealthProbe> probes() {
        return Collections.unmodifiableMap(probes);
    }
}
