/*
 * Copyright (c) 2015 The CyanogenMod Project
 * Copyright (c) 2017 The LineageOS Project
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

package org.lineageos.settings.device.actions;

import android.content.Context;
import android.os.Vibrator;
import android.util.Log;

import org.lineageos.settings.device.SensorAction;

public class TorchAction implements SensorAction {
    private static final String TAG = "LineageActions";

    private static final int TURN_SCREEN_ON_WAKE_LOCK_MS = 500;

    private FlashDevice mFlashDevice;
    private final Vibrator mVibrator;
    private String mRearCameraId;
    private static boolean mTorchEnabled;

    public TorchAction(Context mContext) {
        mFlashDevice = FlashDevice.getInstance(mContext);
        mTorchEnabled = mFlashDevice.getFlashMode() > 0 ? true : false;
        mVibrator = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
    }

    @Override
    public void action() {
        mVibrator.vibrate(250);
        mFlashDevice.setFlashMode(!mTorchEnabled ? 1 : 0, true);
        mTorchEnabled = !mTorchEnabled;
    }
}
