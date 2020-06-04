package com.example.app

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        countTask(tv_count, btn_start, btn_stop)
    }
}

fun countTask(count: TextView, start: Button, stop: Button) {
    // UI 컨텍스트에서 코루틴 실행
    val job = GlobalScope.launch(Dispatchers.Main, start = CoroutineStart.LAZY) {
        for (i in 10 downTo 1) { // 카운트다운
            count.text = "Countdown $i ..." // UI 업데이트
            delay(1000)
        }
        count.text = "Done!"
    }
    start.setOnClickListener { job.start() }
    stop.setOnClickListener { job.cancel() }
}
