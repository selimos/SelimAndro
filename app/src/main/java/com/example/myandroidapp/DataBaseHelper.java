package com.example.myandroidapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_Name="register.db";
    public static final String TABLE_NAME="registeruser";
    public static final String TABLE_2="post";
    public static final String TABLE_3="profi";

    public static final String COL_1="ID";
    public static final String COL_2="username";
    public static final String COL_3="password";

    public DataBaseHelper( Context context) {
        super(context, DATABASE_Name, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE registeruser (ID INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public long addUser(String user, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",user);
        contentValues.put("password",password);
        long res = db.insert("registeruser",null, contentValues);
        db.close();
        return res;
    }
    public boolean chechUser(String username, String password){
        String[] columns = { COL_1 };
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_2 + "=?" + " and " + COL_3 + "=?";
        String[] selectionArgs = { username, password };
        Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        db.close();

        if(count>0)
            return true;
        else
            return false;
    }

}

