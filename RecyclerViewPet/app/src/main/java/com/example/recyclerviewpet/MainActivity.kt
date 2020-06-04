package com.example.recyclerviewpet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // private val dataArray: ArrayList<String> = ArrayList()
    private val petList = arrayListOf<Pet>(
        Pet("British Shorthair", "Male", "4", "british_shorthair"),
        Pet("Persian Cat", "Male", "8", "persian_cat"),
        Pet("Siamese Cat", "Female", "12", "siamese_cat"),
        Pet("Maine Coon", "Male", "9", "maine_coon"),
        Pet("Ragdoll", "Male", "3", "ragdoll"),
        Pet("Sphynx Cat", "Male", "1", "sphynx_cat"),
        Pet("Abyssinian", "Female", "9", "abyssinian")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //addDataArray()
        rv_data_list.layoutManager = LinearLayoutManager(this)
        rv_data_list.setHasFixedSize(true)
        rv_data_list.adapter = DataAdapter(petList, this) {
            Toast.makeText(
                this, "Breed: ${it.breed}, Age: ${it.age}",
                Toast.LENGTH_LONG
            )
        }
    }

}
