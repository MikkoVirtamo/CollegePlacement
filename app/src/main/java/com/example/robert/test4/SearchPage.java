package com.example.robert.test4;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
/*
 import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
 */


public class SearchPage extends AppCompatActivity {

    private static final String TAG = "SearchPage";
    String[] schoolArray = new String[10];
    String[] secondArr;
    // TextView textView;
    String search;
    public int counter = 0;
    ListView listview;
    private String savedSearch;
    private String savedCounter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: in");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);


        listview = (ListView) findViewById(R.id.listview);


        Intent intent = getIntent();
        String str = intent.getStringExtra("variable"); //Gets the string variable that was passed from MainActivity
        this.search = str;
        Cursor cursor;
        Cursor mcursor;

        for (int i = 0; i < 10; i++) {
            schoolArray[i] = "Test Phrase"; //Writing this into the array because nothing else will have that phrase in it, so i can check something later
        }

        DBhelper db = new DBhelper(this);
        SQLiteDatabase sdb = db.getWritableDatabase();

        if (search.length() == 2) {
            cursor = sdb.rawQuery("SELECT * FROM admissions WHERE [state_abbrev] like '" + search + "'", null);
            mcursor = sdb.rawQuery("SELECT count(*) FROM admissions WHERE [state_abbrev] like '" + search + "'", null);

        } else {
            cursor = sdb.rawQuery("SELECT * FROM admissions WHERE [Institution Name] like '%" + search + "%'", null);
            mcursor = sdb.rawQuery("SELECT count(*) FROM admissions WHERE [Institution Name] like '%" + search + "%'", null);
        }

        mcursor.moveToFirst();
        int count = mcursor.getInt(0);

        if (count != 0) {
            while (cursor.moveToNext()) {                    //Cursor moves to first row
                schoolArray[counter] = cursor.getString(1);   //Name of school placed into array
                counter++;

                if (counter == 10) { //Only lists 10 items at once
                    cursor.moveToLast();
                }
            }

            int notNull = 0;

            for (int i = 0; i < schoolArray.length; i++) {
                if (!schoolArray[i].equals("Test Phrase")) {
                    notNull++;
                }
            }
            if (notNull < 10) {
                secondArr = new String[notNull];
                for (int j = 0; j < notNull; j++) {
                    secondArr[j] = schoolArray[j];
                }
                ListAdapter myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, secondArr);
                listview.setAdapter(myAdapter);

            } else {
                // textView.append("Counter = " + counter);
                ListAdapter myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, schoolArray);
                listview.setAdapter(myAdapter);
            }

            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(SearchPage.this, DisplayPage.class);
                    String str = (String) listview.getItemAtPosition(i);
                    intent.putExtra("variable", str);
                    startActivity(intent);
                }
            });
        }
    }

    public void nextSet(View view) { //Basically does the same stuff as above, with slight changes

        Intent intent = getIntent();
        String str = intent.getStringExtra("variable"); //Gets the string variable that was passed from MainActivity
        this.search = str;

        for (int i = 0; i < 10; i++) {
            schoolArray[i] = "Test Phrase"; //Writing this into the array because nothing else will have that phrase in it, so i can check something later
        }
        int newCounter = 0;
        DBhelper db = new DBhelper(this);
        SQLiteDatabase sdb = db.getWritableDatabase();
        // Cursor cursor = sdb.rawQuery("SELECT * FROM admissions WHERE [State abbreviation (HD2016)] like '" + search + "'", null);
        Cursor cursor;
        if(search.length() == 2){
            cursor = sdb.rawQuery("SELECT * FROM admissions WHERE [state_abbrev] like '" + search + "'", null);
            // mcursor = sdb.rawQuery("SELECT count(*) FROM admissions WHERE [State abbreviation (HD2016)] like '" + search + "'", null);

        }else{
            cursor = sdb.rawQuery("SELECT * FROM admissions WHERE [Institution Name] like '%" + search + "%'", null);
            //    mcursor = sdb.rawQuery("SELECT count(*) FROM admissions WHERE [Institution Name] like '%" + search + "%'", null);
        }

        cursor.moveToPosition(counter - 1);
        if (cursor.isLast()) {

        } else {
            listview = (ListView) findViewById(R.id.listview);


            while (cursor.moveToNext()) {
                schoolArray[newCounter] = cursor.getString(1);
                newCounter++;
                counter++;
                //counter keeps track of location in query, newCounter keeps track of how many items are being listed this time

                if (newCounter == 10) {
                    cursor.moveToLast();
                }


            }
            //The below thing only comes into play at the very end of the query, when there would be less than 10 items listed
            //It makes a new array with only as many schools as there are left
            //this makes it so that you dont end up with 10 items on a list where only some of them actually have a name
            int notNull = 0;

            for (int i = 0; i < schoolArray.length; i++) {
                if (!schoolArray[i].equals("Test Phrase")) {
                    notNull++;
                }
            }
            if (notNull < 10) {
                secondArr = new String[notNull];
                for (int j = 0; j < notNull; j++) {
                    secondArr[j] = schoolArray[j];
                }
                ListAdapter myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, secondArr);
                listview.setAdapter(myAdapter);

            } else {
                // textView.append("Counter = " + counter);
                ListAdapter myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, schoolArray);
                listview.setAdapter(myAdapter);
            }

            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(SearchPage.this, DisplayPage.class);
                    String str = (String) listview.getItemAtPosition(i);
                    intent.putExtra("variable", str);
                    startActivity(intent);
                }
            });

        }
    }




    public void lastSet(View view) {//Again basically the same thing, but going backwards instead of forward

        Intent intent = getIntent();
        String str = intent.getStringExtra("variable"); //Gets the string variable that was passed from MainActivity
        this.search = str;

        for (int i = 0; i < 10; i++) {
            schoolArray[i] = "Test Phrase";
        }
        //This odd bit of math here makes sure that the counter goes into the correct spot
        counter = counter - 11;
        counter = counter / 10;
        counter = counter * 10;

        if (counter < 0) {
            counter = counter + 20;
        } else {
            listview = (ListView) findViewById(R.id.listview);
            int newCounter = 0;
            DBhelper db = new DBhelper(this);
            SQLiteDatabase sdb = db.getWritableDatabase();

            Cursor cursor;
            if(search.length() == 2){
                cursor = sdb.rawQuery("SELECT * FROM admissions WHERE [state_abbrev] like '" + search + "'", null);
                // mcursor = sdb.rawQuery("SELECT count(*) FROM admissions WHERE [State abbreviation (HD2016)] like '" + search + "'", null);

            }else{
                cursor = sdb.rawQuery("SELECT * FROM admissions WHERE [Institution Name] like '%" + search + "%'", null);
                //    mcursor = sdb.rawQuery("SELECT count(*) FROM admissions WHERE [Institution Name] like '%" + search + "%'", null);
            }

            cursor.moveToPosition(counter - 1);
            while (cursor.moveToNext()) {
                schoolArray[newCounter] = cursor.getString(1);
                newCounter++;
                counter++;

                if (newCounter == 10) {
                    cursor.moveToLast();
                }


            }
            ListAdapter myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, schoolArray);
            listview.setAdapter(myAdapter);
            // textView.append("Counter = " + counter);
        }

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SearchPage.this, DisplayPage.class);
                String str = (String) listview.getItemAtPosition(i);
                intent.putExtra("variable", str);
                startActivity(intent);
            }
        });

    }


    public void goBack(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: in");
        super.onStart();
        Log.d(TAG, "onStart: out");
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: in");
        super.onDestroy();
        Log.d(TAG, "onDestroy: out");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, "onRestoreInstanceState: in");
        super.onRestoreInstanceState(savedInstanceState);



        Log.d(TAG, "onRestoreInstanceState: out");
    }


    @Override
    protected void onRestart() {
        Log.d(TAG, "onRestart: in");

        super.onRestart();



        Log.d(TAG, "onRestart: out");
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: in");
        super.onPause();
        Log.d(TAG, "onPause: out");
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, "onSaveInstanceState: in");
        Intent intent = getIntent();
        String str = intent.getStringExtra("variable");

        savedInstanceState.putString(savedSearch, str);
        savedInstanceState.putInt(savedCounter,counter);
        //outState.putString(TEXT_CONTENTS, textView.getText().toString());
        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG, "onSaveInstanceState: out");
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: in");
        super.onResume();
        Log.d(TAG, "onResume: out");
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: in");
        super.onStop();
        Log.d(TAG, "onStop: out");
    }











}

