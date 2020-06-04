package com.acaroom.edu.runonuithreadex


import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private var isRunning = false

    var width = 800
    var height = 800

    private val paint = Paint()
    private val bitmap = Bitmap.createBitmap(800, 800, Bitmap.Config.ARGB_8888)
    private val canvas = Canvas(bitmap)

    var x = 463f
    var y = 743f
    var vx = 1f
    var vy = 1f
    var r = 30f


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        isRunning = true

        canvas.drawColor(Color.YELLOW)
        paint.isAntiAlias = false
        paint.style = Paint.Style.FILL
        iv_canvas.setImageBitmap(bitmap)
        val timer = Timer()
        timer.schedule(
            object : TimerTask() {
                override fun run() {
                    runOnUiThread { updateCanvas() }
                }
            }, 0, 10
        )

        button_start.setOnClickListener {

            // start some dummy thread that is different from UI thread
            Thread(Runnable {
                while (isRunning) {
                    //SystemClock.sleep(100)
                    Thread.sleep(100)
                    val time = System.currentTimeMillis()

                    // try to touch View of UI thread
                    this@MainActivity.runOnUiThread {
                        this.tv_message.text = "Updated : $time"
                    }
                }
            }).start()
        }
    }

    private fun updateCanvas() {
        paint.color = Color.GREEN
        canvas.drawCircle(x, y, r, paint)
        x += vx
        y += vy
        if (x + r >= width) vx = -vx
        if (x - r <= 0) vx = -vx
        if (y + r >= height) vy = -vy
        if (y - r <= 0) vy = -vy
        paint.color = Color.RED
        canvas.drawCircle(x, y, r, paint)
        iv_canvas.invalidate()
    }

    override fun onDestroy() {
        super.onDestroy()
        isRunning = false
    }
}
