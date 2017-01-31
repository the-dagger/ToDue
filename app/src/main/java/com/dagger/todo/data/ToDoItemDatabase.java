package com.dagger.todo.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Harshit on 1/28/2017
 */

public class ToDoItemDatabase extends SQLiteOpenHelper {

    private static ToDoItemDatabase toDoItemDatabase;
    private static final String DATABASE_NAME = "todoDatabase.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_TODO = "todo";

    private static final String TODO_ID = "id";
    private static final String TODO_TITLE = "title";
    private static final String TODO_CONTENT = "content";
    private static final String TODO_DUE_DATE = "dueDate";
    private static final String TODO_STATUS = "status";
    private static final String TODO_PRIORITY = "priority";
    private static final String TODO_TIME = "creationTime";

    private ToDoItemDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized ToDoItemDatabase getToDoItemDatabase(Context context) {
        if (toDoItemDatabase == null) {
            toDoItemDatabase = new ToDoItemDatabase(context);
        }
        return toDoItemDatabase;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TODO_TABLE = "CREATE TABLE " + TABLE_TODO +
                "(" +
                TODO_ID + " INTEGER PRIMARY KEY," + // Define a primary key
                TODO_TITLE + " TEXT, " + TODO_CONTENT + " TEXT," + // Define a foreign key
                TODO_DUE_DATE + " TEXT, " + TODO_PRIORITY + " INTEGER, " + TODO_STATUS + " INTEGER, " + TODO_TIME + " INTEGER)";
        Log.e("Todo", CREATE_TODO_TABLE);
        db.execSQL(CREATE_TODO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
            onCreate(db);
        }
    }

    public void addToDoItem(ToDo toDo) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(TODO_TITLE, toDo.getTitle());
            values.put(TODO_CONTENT, toDo.getContent());
            values.put(TODO_DUE_DATE, toDo.getDueDate());
            values.put(TODO_PRIORITY, toDo.getPriority());
            values.put(TODO_STATUS, toDo.isComplete());
            values.put(TODO_TIME, toDo.getTimeOfAddition());
            long id = sqLiteDatabase.insertOrThrow(TABLE_TODO, null, values);
            Log.e("InsertedID", String.valueOf(id));
            sqLiteDatabase.setTransactionSuccessful();
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

    public void updateToDoItem(ToDo toDo) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        ContentValues values = new ContentValues();
        values.put(TODO_TITLE, toDo.getTitle());
        values.put(TODO_CONTENT, toDo.getContent());
        values.put(TODO_DUE_DATE, toDo.getDueDate());
        values.put(TODO_PRIORITY, toDo.getPriority());
        values.put(TODO_STATUS, toDo.isComplete());
        values.put(TODO_TIME, toDo.getTimeOfAddition());
        try {
            int rows = sqLiteDatabase.update(TABLE_TODO, values, TODO_TIME + " = ?", new String[]{String.valueOf(toDo.getTimeOfAddition())});
            Log.e("updateRows", String.valueOf(rows));
            if (rows == 1) {
                // Get the primary key of the user we just updated
                String toDoUpdateQuery = String.format("SELECT * FROM %s WHERE %s = ?",
                        TABLE_TODO, TODO_TIME);
                Cursor cursor = sqLiteDatabase.rawQuery(toDoUpdateQuery, new String[]{
                        String.valueOf(toDo.getTimeOfAddition())});
                Log.e("usersSelectQuery", toDoUpdateQuery);
                try {
                    if (cursor.moveToFirst()) {
                    }
                } finally {
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                        sqLiteDatabase.setTransactionSuccessful();
                        sqLiteDatabase.endTransaction();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteToDoFromDatabase(ToDo toDo) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        try {
            String toDoDeleteQuery = String.format("DELETE FROM %s WHERE %s = ?",
                    TABLE_TODO, TODO_TIME);
            Log.e("DELETE", toDoDeleteQuery);
            int rowsDeleted = sqLiteDatabase.delete(TABLE_TODO, TODO_TIME + " = ?", new String[]{String.valueOf(toDo.getTimeOfAddition())});
            Log.e("Rows", String.valueOf(rowsDeleted));
            sqLiteDatabase.setTransactionSuccessful();
            sqLiteDatabase.endTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ToDo> getToDoFromDatabase() {
        ArrayList<ToDo> toDoList = new ArrayList<>();
        String SELECTION_QUERY = String.format("SELECT * FROM %s", TABLE_TODO);
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECTION_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    ToDo toDo = new ToDo(cursor.getString(cursor.getColumnIndex(TODO_TITLE)),
                            cursor.getString(cursor.getColumnIndex(TODO_CONTENT)),
                            cursor.getInt(cursor.getColumnIndex(TODO_STATUS)),
                            cursor.getString(cursor.getColumnIndex(TODO_DUE_DATE)),
                            cursor.getInt(cursor.getColumnIndex(TODO_PRIORITY)),
                            cursor.getLong(cursor.getColumnIndex(TODO_TIME)));
                    toDoList.add(toDo);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        Log.e("ToDoLisSize", String.valueOf(toDoList.size()));
        return toDoList;
    }
}
