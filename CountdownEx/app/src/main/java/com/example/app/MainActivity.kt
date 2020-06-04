package com.example.app

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlin.coroutines.CoroutineContext


class MainActivity : AppCompatActivity(), CoroutineScope {

    private val TAG = "COUNTDOWN"
    private lateinit var job: CompletableJob

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!::job.isInitialized) {
            initJob()
        }
        countTask(tv_count, btn_start, btn_stop)
    }

    private fun initJob() {
        job = Job()
        job.invokeOnCompletion {
            it?.message.let {
                var msg = it
                if(msg.isNullOrBlank()) {
                    msg = "Unknown error."
                }
                Log.e(TAG, "$job was cancelled: $msg")
            }
            tv_count.text = "Done!"
        }
        tv_count.text = "Ready"
    }

    private fun countTask(count: TextView, start: Button, stop: Button) {
        // UI 컨텍스트에서 코루틴 실행
        /*val job = GlobalScope.launch(Dispatchers.Main, start = CoroutineStart.LAZY) {
            for (i in 10 downTo 1) { // 카운트다운
                count.text = "Countdown $i ..." // UI 업데이트
                delay(1000)
            }
            count.text = "Done!"
        }*/
        start.setOnClickListener {
            if (count.text.contains("Countdown")) {
                Log.d(TAG, "$job is already active.")
                resetJob()
            } else {
                launch {
                    Log.d(TAG, "$this coroutine is activated with $job")
                    for (i in 10 downTo 1) { // 카운트다운
                        Log.d(TAG, "$this coroutine is activated with $job")
                        count.text = "Countdown $i ..." // UI 업데이트
                        delay(1000)
                    }
                    count.text = "Count Done!"
                }
            }
            //job.start()
        }
        stop.setOnClickListener {
            resetJob()
        }
    }

    private fun resetJob() {
        if(job.isActive || job.isCompleted){
            job.cancel(CancellationException("Resetting job"))
        }
        initJob()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

}

