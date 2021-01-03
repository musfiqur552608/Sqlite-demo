package com.example.sqlitedemo

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var lists: ArrayList<Subject>
    lateinit var DB:sqlHelper
    lateinit var data:Cursor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lists = ArrayList<Subject>()
        DB = sqlHelper(applicationContext)
        data = DB.data_geter

        val adapter = Adapter(applicationContext,lists)
        val recycler = findViewById<RecyclerView>(R.id.recycler_list)



        val go = findViewById<Button>(R.id.GO_subject)
        val delete = findViewById<Button>(R.id.delete_btn)
        val id_input = findViewById<TextView>(R.id.id_textview)




       /* delete.setOnClickListener {
            val id_text = id_input.text.toString().trim()

            DB.Detele_Data(id_text)
            Toast.makeText(this@MainActivity,"The Subject has been Deleted",Toast.LENGTH_SHORT).show()

            startActivity(Intent(this@MainActivity,MainActivity::class.java))
        }*/


        go.setOnClickListener {
            startActivity(Intent(this@MainActivity,add_subject::class.java))
        }
        ShowData()
        recycler.layoutManager = GridLayoutManager(applicationContext,2)
        recycler.adapter = adapter
    }

    fun ShowData(){
        if(data.count==0)
        {
            Toast.makeText(applicationContext,"There is no books",Toast.LENGTH_SHORT).show()
        }
        while(data.moveToNext()){
            lists.add(Subject(data.getString(0),
            data.getString(1),
            data.getString(2)))
        }
    }

    override fun onStart() {
        super.onStart()
        ShowData()
    }
}