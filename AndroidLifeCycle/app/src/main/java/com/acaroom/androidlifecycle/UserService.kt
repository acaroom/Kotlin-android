package com.acaroom.androidlifecycle

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast

class UserService : Service() {

    override fun onBind(intent: Intent): IBinder {
        Log.i("USERSERVICE", "onBind()")
        Toast.makeText(this, "service - onBind()", Toast.LENGTH_SHORT).show()
        TODO("Return the communication channel to the service.")
    }

    override fun onCreate() {
        super.onCreate()
        Log.i("USERSERVICE", "onCreate()")
        Toast.makeText(this, "service - onCreate()", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("USERSERVICE", "onDestroy()")
        Toast.makeText(this, "service - onDestroy()", Toast.LENGTH_SHORT).show()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Log.i("USERSERVICE", "onLowMemory()")
        Toast.makeText(this, "service - onLowMemory()", Toast.LENGTH_SHORT).show()
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
        Log.i("USERSERVICE", "onRebind()")
        Toast.makeText(this, "service - onRebind()", Toast.LENGTH_SHORT).show()
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.i("USERSERVICE", "onRebind()")
        Toast.makeText(this, "service - onRebind()", Toast.LENGTH_SHORT).show()
        return super.onUnbind(intent)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("USERSERVICE", "onStartCommand()")
        Toast.makeText(this, "service - onStartCommand()", Toast.LENGTH_SHORT).show()

        
        return super.onStartCommand(intent, flags, startId)
    }
}
