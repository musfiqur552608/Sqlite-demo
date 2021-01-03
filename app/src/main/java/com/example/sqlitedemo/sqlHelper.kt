package com.example.sqlitedemo

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class sqlHelper(context: Context):SQLiteOpenHelper(context,DB_name,null,1){


    companion object{
        val DB_name = "subject.db "
        val TB_name = "Subject "
        val id = "ID"
        val title ="S_title"
        val desc = "S_desc"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table $TB_name(ID INTEGER PRIMARY KEY AUTOINCREMENT, S_title TEXT , S_desc TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TB_name")
    }


    fun ADD_DATA(title_text:String, desc_text:String)
    {
        val DB =this.writableDatabase
        val values = ContentValues()
        values.put(title,title_text)
        values.put(desc,desc_text)


        DB.insert(TB_name,null,values)
    }

    fun Detele_Data(id:String) : Int{
        val DB = this.writableDatabase
        val item = DB.delete(TB_name,"id = ?", arrayOf(id))

        return item
    }


    val data_geter:Cursor get(){
        val DB = this.writableDatabase
        var data = DB.rawQuery("select * from "+ TB_name, null)

        return  data
    }

}