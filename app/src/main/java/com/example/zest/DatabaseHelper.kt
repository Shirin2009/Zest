package com.example.zest

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context?):SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {

    //create table SQL query
    private val CREATE_TABLE_USER = (" CREATE TABLE " + TABLE_USER + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT" + ")")

    //drop table sql query
    private val DROP_USER_TABLE = " DROP TABLE IF EXISTS $TABLE_USER"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_USER)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //drop table if already exist
        db.execSQL(DROP_USER_TABLE)
        //create table again
        onCreate(db)
    }

    //this method is for insert  data into database table
    fun insertUserData(email: String, password: String) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_EMAIL, email)
        contentValues.put(COLUMN_USER_PASSWORD, password)

        //inserting row into database
        db.insert(TABLE_USER, null, contentValues)
        db.close()
    }

    //checks to see if user already exists
    fun userPresents(email: String, password: String):Boolean{
        val db=writableDatabase
        val query= "select * from users where email = '$email' and password = '$password' "
        val cursor = db.rawQuery(query,null)
        if(cursor.count <= 0){
            cursor.close()
            return false
        }
        cursor.close()
        return true
    }

    companion object {
        //database version
        const val DATABASE_VERSION = 1
        //database name
        const val DATABASE_NAME = "user.db"
        //table name
        const val TABLE_USER = "users"
        //ID column @primary key
        const val COLUMN_ID = "id"

        const val COLUMN_EMAIL = "email"
        const val COLUMN_USER_PASSWORD = "password"
    }
}