package com.kisoo.kotlintest.activityhooktest;

import android.app.Application;
import android.app.Instrumentation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by shs1329 on 2017/9/19.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        attachContext();
    }

    public static void attachContext(){
        try {
            Class<?> activityThreadClazz = Class.forName("android.app.ActivityThread");
            Method currentActivityThreadMethod = activityThreadClazz.getDeclaredMethod("currentActivityThread");
            currentActivityThreadMethod.setAccessible(true);
            Object currentActivityThread = currentActivityThreadMethod.invoke(null);

            Field mInstrumentationField = activityThreadClazz.getDeclaredField("mInstrumentation");
            mInstrumentationField.setAccessible(true);
            Instrumentation mInstrumentation = (Instrumentation) mInstrumentationField.get(currentActivityThread);
            //构建代理对象
            Instrumentation evilInstrumentation = new InstrumentationProxy(mInstrumentation);
            //通过反射，换掉字段
            mInstrumentationField.set(currentActivityThread,evilInstrumentation);

        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
