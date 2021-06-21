/**
 * Copyright (c) 2017-present, Wonday (@wonday.org)
 * All rights reserved.
 *
 * This source code is licensed under the MIT-style license found in the
 * LICENSE file in the root directory of this source tree.
 */

package org.wonday.aliyun.push;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import java.util.Map;

import com.alibaba.sdk.android.push.AndroidPopupActivity;
import com.facebook.react.bridge.ReactApplicationContext;

public class ThirdPartMessageActivity extends AndroidPopupActivity {
    static String TAG = "ThirdPartMessageActivity";
    public static Class<?> mainClass;
    public static ReactApplicationContext context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 实现通知打开回调方法，获取通知相关信息
     * @param title     标题
     * @param summary   内容
     * @param extMap    额外参数
     */
    @Override
    protected void onSysNoticeOpened(String title, String summary, Map<String, String> extMap) {
        Log.d(TAG, "onSysNoticeOpened title: " + title + " summary: " + summary + " extMap: " + extMap);

        if (AliyunPushMessageReceiver.instance!=null) {
            Log.d(TAG, "AliyunPushMessageReceiver instance is ok");
            AliyunPushMessageReceiver.instance.onNotification(context, title, summary, extMap);

        } else {
            Log.d(TAG, "AliyunPushMessageReceiver instance is null");
        }

        if (ThirdPartMessageActivity.mainClass!=null) {
            Log.d(TAG, "ThirdPartMessageActivity mainClass is ok");
            Intent itent= new Intent();
            itent.setClass(ThirdPartMessageActivity.this, mainClass);
            startActivity(itent);
            ThirdPartMessageActivity.this.finish();
        } else {
            Log.d(TAG, "ThirdPartMessageActivity mainClass is null");
        }
    }
}