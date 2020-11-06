package com.jeksonshar.funacademyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.lang.StringBuilder

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val textView: TextView = findViewById(R.id.second_activity_text_view)

        val transmittedInt = intent.getIntExtra(TRANSMITTED_INT, 0)
        val transmittedString = intent.getStringExtra(TRANSMITTED_STRING)
        val transmittedBoolean = intent.getBooleanExtra(TRANSMITTED_BOOLEAN, false)

        val string = StringBuilder()
        string.append("${textView.text}\n")
        string.append("\n")
        string.append(resources.getString(R.string.this_values_was_transmitted_from_main_activity))
        string.append(":\n ")
        string.append(resources.getString(R.string.transmitted_string))
        string.append(" $transmittedString\n ")
        string.append(resources.getString(R.string.transmitted_int))
        string.append(" $transmittedInt\n ")
        string.append(resources.getString(R.string.transmitted_boolean))
        string.append(" $transmittedBoolean\n")

        textView.text = string
    }

    companion object {
        const val TRANSMITTED_INT = "Int"
        const val TRANSMITTED_STRING = "String"
        const val TRANSMITTED_BOOLEAN = "Boolean"
    }
}