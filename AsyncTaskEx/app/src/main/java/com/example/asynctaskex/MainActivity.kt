package com.example.asynctaskex

import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 이미지 다운로드를 위한 비동기 태스크 초기화
        val task = AsyncImageDownload(this, root_layout, progress_bar, tv_message)

        button_start.setOnClickListener {

            // 스레드풀을 사용해 다수의 스레드를 사용할 때
            // task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            task.execute(
//                "https://images.freeimages.com/images/large-previews/310/spring-1-1405906.jpg",
//                "https://images.freeimages.com/images/large-previews/8f3/white-flower-power-1403046.jpg",
//                "https://images.freeimages.com/images/large-previews/81c/flower-1393311.jpg",
//                "https://images.freeimages.com/images/large-previews/7f7/statice-1406388.jpg",
//                "https://images.freeimages.com/images/large-previews/760/wedding-flower-1188350.jpg"
                "https://picsum.photos/300/200",
                "https://picsum.photos/300/200",
                "https://picsum.photos/300/200",
                "https://picsum.photos/300/200",
                "https://picsum.photos/300/200"
            )
            it.isEnabled = false
            button_stop.isEnabled = true
        }

        // Button to cancel the async task
        button_stop.setOnClickListener {
            // 작업의 취소
            task.cancel(true)
            it.isEnabled = false
        }

        // Set a click listener for root layout
        root_layout.setOnClickListener {
            // Get the async task status and show it using toast message
            when(task.status){
                AsyncTask.Status.RUNNING -> toast("Task running")
                AsyncTask.Status.PENDING -> toast("Task pending")
                AsyncTask.Status.FINISHED -> toast("Task finished")
                else -> toast("Task status Unknown")
            }
        }
    }

    // Extension function to show toast message
    fun Context.toast(message:String){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }
}
