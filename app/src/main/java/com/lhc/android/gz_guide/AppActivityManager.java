package com.lhc.android.gz_guide;

import android.app.Activity;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Stack;

/**
 * Created by Administrator on 2017/4/9.
 */
public class AppActivityManager {

    private volatile static AppActivityManager instance;

    private Stack<WeakReference<Activity>> activityStack = new Stack<>();

    public static AppActivityManager getInstance() {
        if (instance == null) {
            synchronized (AppActivityManager.class) {
                instance = new AppActivityManager();
            }
        }
        return instance;
    }

    public Stack<WeakReference<Activity>> getActivityStack() {
        return activityStack;
    }

    public int getStackEntryCount(){
        return activityStack.size();
    }

    public void push(Activity activity) {
        if(activity != null) {
            WeakReference<Activity> ref = new WeakReference<Activity>(activity);
            activityStack.push(ref);
        }
    }

    public void pop(Activity activity) {
        if(activity != null) {
            WeakReference<Activity> target = findActivity(activity);
            if (target != null) {
                activityStack.remove(target);
            }
        }

    }


    public WeakReference<Activity> findActivity(Activity activity) {
        WeakReference<Activity> target = null;
        for (WeakReference<Activity> ref : activityStack) {
            if (ref.get() == activity) {
                target = ref;
                break;
            }
        }
        return target;
    }


    public void clear() {
        while (!activityStack.empty()) {
            WeakReference<Activity> ref = activityStack.pop();
            if (ref != null && ref.get() != null) {
                ref.get().finish();
            }
        }
    }


}
