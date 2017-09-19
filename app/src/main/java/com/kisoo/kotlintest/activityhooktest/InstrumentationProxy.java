package com.kisoo.kotlintest.activityhooktest;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by shs1329 on 2017/9/19.
 */

public class InstrumentationProxy extends Instrumentation {
    public Instrumentation oldInstrumentation;
    public static final String TAG = "InstrumentationProxy";


    public InstrumentationProxy(Instrumentation instrumentation) {
        oldInstrumentation = instrumentation;
    }

    public ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token, Activity target, Intent intent, int requestCode, Bundle options) {
        try {
            Log.i(TAG, "------------hook  success------------->");
            Log.i(TAG, "这里可以做你在打开StartActivity方法之前的事情");
            Log.i(TAG, "------------hook  success------------->");

            Method execStartActivity = Instrumentation.class.getDeclaredMethod("execStartActivity", Context.class, IBinder.class, IBinder.class, Activity.class, Intent.class, int.class, Bundle.class);
            Log.i(TAG, "hook one");
            execStartActivity.setAccessible(true);
            Log.i(TAG, "hook two");
            return (ActivityResult) execStartActivity.invoke(oldInstrumentation,who,contextThread,token,target,intent,requestCode,options);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException("if Instrumentation paramerter is mInstrumentation, hook will fail");
        }
    }

}
