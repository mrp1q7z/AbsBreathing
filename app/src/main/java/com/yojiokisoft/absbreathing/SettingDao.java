/*
 * Copyright (C) 2014 4jiokiSoft
 * 
 * This program is free software; you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 3 of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this
 * program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.yojiokisoft.absbreathing;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * 設定情報のDAO
 */
public class SettingDao {
    private static SettingDao mInstance = null;
    private static SharedPreferences mSharedPref = null;
    private static Context mContext;

    /**
     * コンストラクタは公開しない
     * インスタンスを取得する場合は、getInstanceを使用する.
     */
    private SettingDao() {
    }

    /**
     * インスタンスの取得.
     *
     * @return SettingDao
     */
    public static SettingDao getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SettingDao();
            mContext = context;
            mSharedPref = PreferenceManager.getDefaultSharedPreferences(mContext);
        }
        return mInstance;
    }

    /**
     * @return 自動で発音するのON/OFF状態
     */
    public boolean getVibrate() {
        return mSharedPref.getBoolean("vibrate", true);
    }

    /**
     * @return 自動で発音するのON/OFF状態を文字列で
     */
    public String getBreathingCount() {
        return mSharedPref.getString("breathing_count", "10");
    }
}
