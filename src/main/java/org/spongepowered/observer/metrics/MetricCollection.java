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
package org.spongepowered.observer.metrics;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.observer.metrics.meter.Counter;
import org.spongepowered.observer.metrics.meter.Gauge;
import org.spongepowered.observer.metrics.meter.Histogram;
import org.spongepowered.observer.metrics.meter.Metadata;
import org.spongepowered.observer.metrics.meter.Timer;

public interface MetricCollection {
    Counter newCounter(Metadata metadata);

    default Counter.Builder newCounter() {
        return new Counter.Builder() {
            @Override
            protected @NotNull Counter build(Metadata metadata) {
                return newCounter(metadata);
            }
        };
    }

    Gauge newGauge(Metadata metadata);

    default Gauge.Builder newGauge() {
        return new Gauge.Builder() {
            @Override
            protected @NotNull Gauge build(Metadata metadata) {
                return newGauge(metadata);
            }
        };
    }

    Timer newTimer(Metadata metadata);

    default Timer.Builder newTimer() {
        return new Timer.Builder() {
            @Override
            protected @NotNull Timer build(Metadata metadata) {
                return newTimer(metadata);
            }
        };
    }

    Histogram newHistogram(Metadata metadata, double[] buckets);

    default Histogram.Builder newHistogram() {
        return new Histogram.Builder() {
            @Override
            protected Histogram build(Metadata metadata, double[] buckets) {
                return newHistogram(metadata, buckets);
            }
        };
    }

    void subscribe(MetricSubscriber subscriber);

    void unsubscribe(MetricSubscriber subscriber);

    static String[] name(String... segments) {
        return segments;
    }
}
