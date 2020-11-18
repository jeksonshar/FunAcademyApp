package com.jeksonshar.funacademyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nextButton: Button = findViewById(R.id.next_button)

        nextButton.setOnClickListener { moveToNextScreen() }
    }

    private fun moveToNextScreen() {
        val intent = Intent(this, MovieDetailsActivity::class.java)

        startActivity(intent)
    }
}