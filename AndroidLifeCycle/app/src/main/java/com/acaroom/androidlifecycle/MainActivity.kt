package com.acaroom.androidlifecycle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //private lateinit var mWelcomeTextView: TextView
    //private lateinit var mStartService: Button
    //private lateinit var mStopService: Button
    private lateinit var mHandler: Handler
    private var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       // mWelcomeTextView = findViewById(R.id.msgView)
       // mStartService = findViewById(R.id.butt_start_service)
       // mStopService = findViewById(R.id.butt_stop_service)

        mHandler = object:  Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                Log.i("LIFECYCLE", "HANDLER")
                msgView.text = "Random Number!"
            }
        }

        val userService = UserService::class.java
        val intent = Intent(applicationContext, userService)

        println("println - onCreate()")
        Log.i("LIFECYCLE", "onCreate()")
        Toast.makeText(this, "onCreate()", Toast.LENGTH_SHORT).show()

        butt_start_service.setOnClickListener {

            startService(intent)
        }

        butt_stop_service.setOnClickListener {

            stopService(intent)
        }

    }

    override fun onStart() {
        super.onStart()
        println("println - onStart()")
        Log.i("LIFECYCLE", "onStart()")
        Toast.makeText(this, "onStart()", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        msgView.text = "Hello Kotlin!: $count"
        count++

        println("println - onResume()")
        Log.i("LIFECYCLE", "onResume()")
        Toast.makeText(this, "onResume()", Toast.LENGTH_SHORT).show()
    }

    override fun onRestart() {
        super.onRestart()
        println("println - onRestart()")
        Log.i("LIFECYCLE", "onRestart()")
        Toast.makeText(this, "onRestart()", Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        println("println - onPause()")
        Log.i("LIFECYCLE", "onPause()")
        Toast.makeText(this, "onPause()", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        println("println - onStop()")
        Log.i("LIFECYCLE", "onStop()")
        Toast.makeText(this, "onStop()", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        println("println - onDestroy()")
        Log.i("LIFECYCLE", "onDestroy()")
        Toast.makeText(this, "onDestroy()", Toast.LENGTH_SHORT).show()
    }
}
