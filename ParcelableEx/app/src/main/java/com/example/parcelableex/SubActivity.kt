package com.example.parcelableex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_sub.*

class SubActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        val bundle = intent.getBundleExtra("myBundle")
        var person = bundle.getParcelable<Person>("selected_person")

        tv_result.text = "${person?.name}, ${person?.age}"
    }
}
