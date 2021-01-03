package com.example.sqlitedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class add_subject : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_subject)

        val DB = sqlHelper(applicationContext)
        val title_input = findViewById<EditText>(R.id.title_edit_text)
        val desc_input = findViewById<EditText>(R.id.desc_edit_text)
        val add_btn = findViewById<Button>(R.id.add_btn)

        add_btn.setOnClickListener {
            val title_text = title_input.text.toString().trim()
            val desc_text  = desc_input.text.toString().trim()

            DB.ADD_DATA(title_text,desc_text)
            Toast.makeText(this@add_subject,"The Subject has been Added",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@add_subject,MainActivity::class.java))
        }
    }
}