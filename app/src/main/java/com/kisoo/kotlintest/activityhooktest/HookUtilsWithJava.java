package com.kisoo.kotlintest.activityhooktest;

import android.content.Context;
import android.content.Intent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by shs1329 on 2017/9/19.
 */

public class HookUtilsWithJava {
    public static void startActivity(Context context, Intent intent){
        try {
            Class<?> activityThreadClazz = Class.forName("android.app.ActivityThread");
            Method currentActivityThreadMethod = activityThreadClazz.getDeclaredMethod("currentActivityThread");
            currentActivityThreadMethod.setAccessible(true);
            Object currentActivityThread = currentActivityThreadMethod.invoke(null);

        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
