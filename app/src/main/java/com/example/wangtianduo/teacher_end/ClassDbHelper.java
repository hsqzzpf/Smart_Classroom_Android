package com.example.wangtianduo.teacher_end;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.ContentObservable;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by norman_lee on 6/10/17.
 */

public class ClassDbHelper extends SQLiteOpenHelper {


    private final Context context;
    private static String PACKAGE_NAME;
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase sqLiteDatabase;
    private SQLiteDatabase readableDb;
    private SQLiteDatabase writeableDb;
    private static ClassDbHelper classDbHelper;
    private static String TAG = "ss";

    //TODO 7.4 Create the Constructor and make it a singleton
    // Singleton Pattern
    private ClassDbHelper(Context context){
        super(context, ClassContract.ClassEntry.TABLE_NAME, null, DATABASE_VERSION );
        this.context = context;
    }

    static ClassDbHelper createClassDbHelper(Context context) {
        // context object of a particular activity is passed to it
        // so we get the context object of the entire app below
        if (classDbHelper == null) {
            classDbHelper = new ClassDbHelper(context.getApplicationContext());
        }
        return classDbHelper;
    }

    //TODO 7.5 Complete onCreate. You may make use of fillTable below
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i("ASSS", "Database OnCreate");
        sqLiteDatabase.execSQL(ClassContract.ClassSql.SQL_CREATE_TABLE);
        fillTable(sqLiteDatabase);
        Log.i(TAG, "onCreate is activated");
    }

    //TODO 7.6 Complete onUpgrade
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(ClassContract.ClassSql.SQL_DROP_TABLE);
        onCreate(sqLiteDatabase);
        Log.i(TAG, "onUgrade is activated");
    }

    //TODO 7.5 --- written for you
    private void fillTable(SQLiteDatabase sqLiteDatabase){

        ArrayList<ClassData> arrayList = new ArrayList<>();
        PACKAGE_NAME = context.getPackageName();

        //open the Json file pictures stored in the res/raw folder
        InputStream inputStream = context.getResources().openRawResource(R.raw.courses);
        String string = Utils.convertStreamToString(inputStream);

        Log.i("ASSS", string);

        //parse the Json file and store data in the ArrayList using the ClassData class
        Log.i("ASDF", "ssssdddddd");
        try{
            JSONArray jsonArray = new JSONArray(string);
            for(int i = 0; i <= jsonArray.length(); i++){
                String name = jsonArray.getJSONObject(i).getString("name");
                String session = jsonArray.getJSONObject(i).getString("session");
                String date = jsonArray.getJSONObject(i).getString("date");
                String timing = jsonArray.getJSONObject(i).getString("timing");
                String venue = jsonArray.getJSONObject(i).getString("venue");

                arrayList.add(new ClassDbHelper.ClassData(name, session, date, timing, venue));
            }
        }catch(JSONException e){
            e.printStackTrace();
        }

        //Each entry in the arrayList is stored as a ContentValues object
        //Then this ContentValues object is inserted to the sqLiteDatabase to create a new row
        for(int i = 0; i< arrayList.size(); i++){
            Log.i("Norman","" + arrayList.get(i).getSession());
            ContentValues cv = new ContentValues();

            cv.put(ClassContract.ClassEntry.COL_NAME, arrayList.get(i).getName());
            cv.put(ClassContract.ClassEntry.COL_SESSION, arrayList.get(i).getSession());
            cv.put(ClassContract.ClassEntry.COL_DATE, arrayList.get(i).getDate());
            cv.put(ClassContract.ClassEntry.COL_TIMING, arrayList.get(i).getTiming());
            cv.put(ClassContract.ClassEntry.COL_VENUE, arrayList.get(i).getVenue());

            sqLiteDatabase.insert(ClassContract.ClassEntry.TABLE_NAME,null,cv);
        }

        Cursor cursor = sqLiteDatabase.rawQuery(ClassContract.ClassSql.SQL_QUERY_ALL_ROWS, null);
        Log.i("Norman","Table Filled. Rows = " + cursor.getCount());


    }

    //TODO 7.8 query one row at random
    public ClassData queryOneRowRandom(){

        if (readableDb == null) {
            readableDb = getReadableDatabase();
        }

        Log.i("ASDF", "RANDOM");
        Cursor cursor = readableDb.rawQuery(
                ClassContract.ClassSql.SQL_QUERY_ONE_RANDOM_ROW, null);
        return getDataFromCursor(0, cursor);

    }

    //TODO 7.9 queryOneRow gets the entire database and returns the row in position as a ClassData object
    public ClassData queryOneRow(int position){

        if (readableDb == null) {
            readableDb = getReadableDatabase();
        }

        Cursor cursor = readableDb.rawQuery(ClassContract.ClassSql.SQL_QUERY_ALL_ROWS, null);

        return getDataFromCursor(position, cursor);

    }

    //TODO 7.8 Get the data from cursor
    private ClassData getDataFromCursor(int position, Cursor cursor){

        String name = null;
        String session = null;
        String date = null;
        String timing = null;
        String venue = null;

        cursor.moveToPosition(position);
        // extract the name column
        int nameIndex = cursor.getColumnIndex(ClassContract.ClassEntry.COL_NAME);
        name = cursor.getString(nameIndex);

        int sessionIndex = cursor.getColumnIndex(ClassContract.ClassEntry.COL_SESSION);
        session = cursor.getString(sessionIndex);

        int dateIndex = cursor.getColumnIndex(ClassContract.ClassEntry.COL_DATE);
        date = cursor.getString(dateIndex);

        int timingIndex = cursor.getColumnIndex(ClassContract.ClassEntry.COL_TIMING);
        timing = cursor.getString(timingIndex);

        int venueIndex = cursor.getColumnIndex(ClassContract.ClassEntry.COL_VENUE);
        venue = cursor.getString(venueIndex);

        return new ClassData(name, session, date, timing, venue);
    }

    //TODO 7.10 Insert one row when data is passed to it
    public void insertOneRow(ClassData ClassData){
        if (writeableDb == null) {
            writeableDb = getWritableDatabase();
        }

        ContentValues contentValues = new ContentValues();

        contentValues.put(ClassContract.ClassEntry.COL_NAME,
                ClassData.getName());

        contentValues.put(ClassContract.ClassEntry.COL_SESSION,
                ClassData.getSession());

        contentValues.put(ClassContract.ClassEntry.COL_DATE,
                ClassData.getDate());

        contentValues.put(ClassContract.ClassEntry.COL_TIMING,
                ClassData.getTiming());

        contentValues.put(ClassContract.ClassEntry.COL_VENUE,
                ClassData.getVenue());


        long row = writeableDb.insert(ClassContract.ClassEntry.TABLE_NAME, null, contentValues);
        Log.i("Logcat", "insertOneRow: row = " + row);
    }


    //TODO 7.11 Delete one row given the name field
    public int deleteOneRow(String name){
        if (writeableDb == null) {
            writeableDb = getWritableDatabase();
        }

        String WHERE_CLAUSE = ClassContract.ClassEntry.COL_NAME + " = ?";
        String[] WHERE_ARGS = {name};
        int rowsDeleted = writeableDb.delete(
                ClassContract.ClassEntry.TABLE_NAME,
                WHERE_CLAUSE,
                WHERE_ARGS
        );

        return rowsDeleted;
    }

    //TODO 7.7 return the number of rows in the database
    public long queryNumRows(){

        if (readableDb == null) {
            readableDb = getReadableDatabase();
        }
        return DatabaseUtils.queryNumEntries(readableDb, ClassContract.ClassEntry.TABLE_NAME);
    }

    public Context getContext(){
        return context;
    }


    //TODO 7.3 Create a model class to represent our data
    static class ClassData{

        private String name;
        private String session;
        private String date;
        private String timing;
        private String venue;

        public ClassData(String name, String session, String date,
                         String timing, String venue) {
            this.name = name;
            this.session = session;
            this.date = date;
            this.timing = timing;
            this.venue = venue;
        }

        public String getSession() { return session; }

        public String getName() {
            return name;
        }

        public String getTiming() { return timing; }

        public String getDate() { return date; }

        public String getVenue() { return venue; }
    }

}
