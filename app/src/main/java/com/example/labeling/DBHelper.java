package com.example.labeling;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String DATA_TABLE_NAME = "Data";
    public static final String DATA_COLUMN_ID = "id";
    public static final String DATA_COLUMN_TYPE = "type";
    public static final String DATA_COLUMN_CONTENT = "content";
    public static final String DATA_COLUMN_GROUP = "noPack";
//    public static final String CONTACTS_COLUMN_CITY = "place";
//    public static final String CONTACTS_COLUMN_PHONE = "phone";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table Data " +
                        "(id integer primary key, type text, content text, noPack integer)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS Data");
        onCreate(db);
    }

    public boolean insertData(String type, String content, int noPack) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATA_COLUMN_TYPE, type);
        contentValues.put(DATA_COLUMN_CONTENT, content);
        contentValues.put(DATA_COLUMN_GROUP, noPack);
        db.insert(DATA_TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getData(int noPack) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from Data where noPack=" + noPack + "", null);
        return res;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, DATA_TABLE_NAME);
        return numRows;
    }

    public boolean updateData(Integer id, String type, String content, int noPack) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATA_COLUMN_TYPE, type);
        contentValues.put(DATA_COLUMN_CONTENT, content);
        contentValues.put(DATA_COLUMN_GROUP, noPack);
        db.update(DATA_TABLE_NAME, contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public Integer deleteData(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(DATA_TABLE_NAME,
                "id = ? ",
                new String[]{Integer.toString(id)});
    }

    public ArrayList<String> getAllData() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from Data", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex(DATA_COLUMN_TYPE)));
            res.moveToNext();
        }
        return array_list;
    }
}
