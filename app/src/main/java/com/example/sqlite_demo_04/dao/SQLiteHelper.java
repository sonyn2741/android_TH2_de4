package com.example.sqlite_demo_04.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.sqlite_demo_04.model.Item;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "test04.db";
    private static int DATABASE_VERSION = 1;
    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE items("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "name TEXT, author TEXT, range TEXT, oop TEXT, rating REAL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
    // getAll
    public List<Item> getAll(){
        List<Item> list= new ArrayList<>();
        SQLiteDatabase st= getReadableDatabase();
        Cursor rs=st.query("items",null,null,null,null,null,null);
        while(rs !=null && rs.moveToNext()){
            int id= rs.getInt(0);
            String na= rs.getString(1);
            String au= rs.getString(2);
            String ra= rs.getString(3);
            String op= rs.getString(4);
            float rt= rs.getFloat(5);
            list.add(new Item(id,na,au,ra,op,rt));
        }
        return list;
    }
    //add item
    public long addItem(Item i){
        ContentValues values = new ContentValues();
        values.put("name", i.getName());
        values.put("author", i.getAuthor());
        values.put("range", i.getRange());
        values.put("oop", i.getOop());
        values.put("rating", i.getRating());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("items",null, values);
    }
    //update
    public int updateItem(Item i) {
        ContentValues values = new ContentValues();
        values.put("name", i.getName());
        values.put("author", i.getAuthor());
        values.put("range", i.getRange());
        values.put("oop", i.getOop());
        values.put("rating", i.getRating());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String whereClause = "id = ?";
        String[] whereArgs = {Integer.toString(i.getId())};
        return sqLiteDatabase.update("items",
                values, whereClause, whereArgs);
    }
    //delete
    public int deleteItem(int id) {
        String whereClause = "id = ?";
        String[] whereArgs = {Integer.toString(id)};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("items",
                whereClause, whereArgs);
    }
    public List<Item> searchByName(String category) {
        List<Item> list = new ArrayList<>();
        String whereClause = "name like ?";
        String[] whereArgs = {"%"+category+"%"};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor rs = sqLiteDatabase.query("items",
                null, whereClause, whereArgs,
                null, null, null);
        while ((rs != null) && (rs.moveToNext())) {
            int id= rs.getInt(0);
            String na= rs.getString(1);
            String au= rs.getString(2);
            String ra= rs.getString(3);
            String op= rs.getString(4);
            float rt= rs.getFloat(5);
            list.add(new Item(id,na,au,ra,op,rt));
        }
        return list;
    }

}
