package com.example.hellowandering

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.os.Bundle

class MyApplication : Application() {

    @SuppressLint("NewApi")
    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(object: ActivityLifecycleCallbacks{
            override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onActivityPaused(activity: Activity?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onActivityResumed(activity: Activity?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onActivityStarted(activity: Activity?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onActivityDestroyed(activity: Activity?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onActivityStopped(activity: Activity?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

}