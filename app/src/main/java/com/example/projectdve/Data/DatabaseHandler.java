package com.example.projectdve.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.projectdve.Model.Book;
import com.example.projectdve.R;
import com.example.projectdve.Util.DBUtils;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {


    public DatabaseHandler(@Nullable Context context) {
        super(context, DBUtils.DATABASE_NAME, null, DBUtils.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //create table
        String CREATE_TABLE = "CREATE TABLE "+ DBUtils.DATABASE_TABLE_NAME + "("
                +DBUtils.COLUMN_ID + " INTEGER PRIMARY KEY,"
                +DBUtils.COLUMN_NAME  + " TEXT,"
                +DBUtils.COLUMN_AUTHOR  + " TEXT,"
                +DBUtils.COLUMN_PRICE  +" REAL,"
                +DBUtils.COLUMN_DATE + " LONG,"
                +DBUtils.COLUMN_TIME +" LONG" +")";

        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String DROP_TABLE = String.valueOf((R.string.drop_table));
        sqLiteDatabase.execSQL(DROP_TABLE,new String[]{DBUtils.DATABASE_NAME});

        //create  table again
        onCreate(sqLiteDatabase);
    }

    //CRUD

    //create--add--contentValues-insert
    public void addItem(Book book){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBUtils.COLUMN_NAME,book.getBookName());
        contentValues.put(DBUtils.COLUMN_AUTHOR,book.getAuthorName());
        contentValues.put(DBUtils.COLUMN_PRICE,book.getBookPrice());
        contentValues.put(DBUtils.COLUMN_DATE,java.lang.System.currentTimeMillis());// current time mills
        contentValues.put(DBUtils.COLUMN_TIME,java.lang.System.currentTimeMillis());

        sqLiteDatabase.insert(DBUtils.DATABASE_TABLE_NAME,null,contentValues);

        //close db
        sqLiteDatabase.close();
    }

    //read--getSingleItem--cursor-query
    public Book getSingleItem(int id){

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(DBUtils.DATABASE_TABLE_NAME,
                new String[]{DBUtils.COLUMN_ID,DBUtils.COLUMN_NAME,DBUtils.COLUMN_AUTHOR,DBUtils.COLUMN_PRICE,
                DBUtils.COLUMN_DATE,DBUtils.COLUMN_TIME},
                DBUtils.COLUMN_ID+"=?",
                new String[]{String.valueOf(id)},
                null,null,null);

        if(cursor!=null)
            cursor.moveToFirst();
            Book book = new Book();
            book.setId(cursor.getInt(cursor.getColumnIndex(DBUtils.COLUMN_ID)));
            book.setBookName(cursor.getString(cursor.getColumnIndex(DBUtils.COLUMN_NAME)));
            book.setAuthorName(cursor.getString(cursor.getColumnIndex(DBUtils.COLUMN_AUTHOR)));
            book.setBookPrice(cursor.getDouble(cursor.getColumnIndex(DBUtils.COLUMN_PRICE)));

            //Get Date & Time


        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL);
        DateFormat timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT);


        String getDate = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(DBUtils.COLUMN_DATE))).getTime());
        String getTime = timeFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(DBUtils.COLUMN_TIME))).getTime());

        book.setDate(getDate);
        book.setTime(getTime);
        return book;
    }

    //read--getAllItems-cursor-query
    public List<Book> getAllItems(){
        List<Book> bookList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(DBUtils.DATABASE_TABLE_NAME,
                new String[]{DBUtils.COLUMN_ID, DBUtils.COLUMN_NAME,DBUtils.COLUMN_AUTHOR,DBUtils.COLUMN_PRICE,
                DBUtils.COLUMN_DATE,DBUtils.COLUMN_TIME},
                null,null,null,null,
                DBUtils.COLUMN_TIME + " DESC");

        if (cursor.moveToFirst()){
            do {
                Book book = new Book();
                book.setId(cursor.getInt(cursor.getColumnIndex(DBUtils.COLUMN_ID)));
                book.setBookName(cursor.getString(cursor.getColumnIndex(DBUtils.COLUMN_NAME)));
                book.setAuthorName(cursor.getString(cursor.getColumnIndex(DBUtils.COLUMN_AUTHOR)));
                book.setBookPrice(cursor.getDouble(cursor.getColumnIndex(DBUtils.COLUMN_PRICE)));

                DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL);
                DateFormat timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT);

                String getDate = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(DBUtils.COLUMN_DATE))).getTime());
                String getTime = timeFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(DBUtils.COLUMN_TIME))).getTime());

                book.setDate(getDate);
                book.setTime(getTime);

                bookList.add(book);
            }while (cursor.moveToNext());
        }
        return bookList;
    }

    //update--contentValues--update

    public int updateItem(Book book){
        SQLiteDatabase sqLiteDatabase =  this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBUtils.COLUMN_NAME,book.getBookName());
        contentValues.put(DBUtils.COLUMN_AUTHOR,book.getAuthorName());
        contentValues.put(DBUtils.COLUMN_PRICE,book.getBookPrice());
        contentValues.put(DBUtils.COLUMN_DATE,book.getDate());
        contentValues.put(DBUtils.COLUMN_TIME,book.getTime());

        return sqLiteDatabase.update(DBUtils.DATABASE_TABLE_NAME,contentValues,
                DBUtils.COLUMN_ID+"=?",
                new String[]{String.valueOf(book.getId())});
    }

    //delete--singleItem
    public void deleteSingleItem(int id){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        sqLiteDatabase.delete(DBUtils.DATABASE_TABLE_NAME,DBUtils.COLUMN_ID+"=?",new String[]{String.valueOf(id)});

        sqLiteDatabase.close();
    }

    //delete-allItems
    public void deleteAllItems(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        sqLiteDatabase.execSQL("delete from "+DBUtils.DATABASE_TABLE_NAME);
    }

    //get total count of entries/items--COUNT QUERY--cursor--rawQuery
    public  int getCount(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String GET_COUNT = " SELECT * FROM "+DBUtils.DATABASE_TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(GET_COUNT,null);

        return cursor.getCount();
    }
}
