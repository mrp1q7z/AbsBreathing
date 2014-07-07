// Copyright 2012 Google Inc. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.yojiokisoft.absbreathing;

import android.content.Context;
import android.content.res.Configuration;

import com.google.ads.AdRequest;

/**
 * Utilities class for some common tasks within Ad Catalog.
 *
 * @author api.eleichtenschl@gmail.com (Eric Leichtenschlag)
 */
public class AdCatalogUtils {
    /**
     * Prevent instantiation.
     */
    private AdCatalogUtils() {
        // Empty.
    }

    /**
     * Determines whether or not the device has an extra large screen.
     *
     * @param context The Android context.
     * @return boolean value indicating if the screen size is extra large.
     */
    public static boolean isExtraLargeScreen(Context context) {
        int screenSizeMask = context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK;
        if (screenSizeMask == Configuration.SCREENLAYOUT_SIZE_XLARGE) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Creates an ad request. It will be a test request if test mode is enabled.
     *
     * @return An AdRequest to use when loading an ad.
     */
    public static AdRequest createAdRequest() {
        AdRequest adRequest = new AdRequest();
        if (BuildConfig.DEBUG) {
            // This call will add the emulator as a test device. To add a
            // physical
            // device for testing, pass in your hashed device ID, which can be
            // found
            // in the LogCat output when loading an ad on your device.
            adRequest.addTestDevice(AdRequest.TEST_EMULATOR);
        }
        return adRequest;
    }
}
