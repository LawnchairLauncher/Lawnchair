/**
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.android.systemui.shared.system;

import android.graphics.Region;

/**
 * Utility class to listen for exclusion rect changes.
 */
public abstract class SystemGestureExclusionListenerCompat {

    private static final String TAG = "SGEListenerCompat";

    public SystemGestureExclusionListenerCompat(int displayId) {

    }

    /**
     * Called when the exclusion region has changed
     */
    public abstract void onExclusionChanged(Region systemGestureExclusion);

    /**
     * Registers the listener for getting exclusion rect changes.
     */
    public void register() {
        // Do nothing
    }

    /**
     * Unregisters the receiver if previously registered
     */
    public void unregister() {
        // Do nothing
    }
}
