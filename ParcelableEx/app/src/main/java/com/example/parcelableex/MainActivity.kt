package com.example.parcelableex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val person = Person("Youngdeok", 30)

        button2.setOnClickListener {
            val intent = Intent(this, SubActivity::class.java)
            var bundle = Bundle()
            bundle.putParcelable("selected_person", person)
            intent.putExtra("myBundle", bundle)
            startActivity(intent)
        }
    }
}
