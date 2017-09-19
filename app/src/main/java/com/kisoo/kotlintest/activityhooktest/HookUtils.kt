package com.kisoo.kotlintest.activityhooktest

import android.content.Context
import java.lang.reflect.Method

/**
 * Created by shs1329 on 2017/9/19.
 */

class HookUtils {
    fun hookStartActivity(context: Context, activityName: String) {
        val activityThread:Class<*> = Class.forName("android.app.ActivityThread")
        var currentActivityThreadMethod:Method = activityThread.getDeclaredMethod("currentActivityThread");
        currentActivityThreadMethod.setAccessible(true)
        var curentActivityThread = currentActivityThreadMethod.invoke(null)
    }
}