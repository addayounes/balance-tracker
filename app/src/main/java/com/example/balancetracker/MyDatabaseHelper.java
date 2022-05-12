package com.example.balancetracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DB_NAME = "BalanceTracker.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "records";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_AMOUNT = "amount";
    private static final String COLUMN_TYPE = "type";



    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_DESCRIPTION + " TEXT," +
                COLUMN_AMOUNT + " REAL," +
                COLUMN_TYPE + " INTEGER," +
                COLUMN_DATE + " TEXT);";

        db.execSQL(createTableQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addRecord(int type, String description, double amount, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();

        content.put(COLUMN_TYPE, type);
        content.put(COLUMN_DESCRIPTION, description);
        content.put(COLUMN_AMOUNT, amount);
        content.put(COLUMN_DATE, date);

        long result = db.insert(TABLE_NAME, null, content);

        if(result==-1) {
            Toast.makeText(context, "Une erreur s'est produite lors de l'ajout de l'enregistrement", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "L'enregistrement a été ajouté avec succcés", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor getAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;

        if(db != null) {
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }
}
