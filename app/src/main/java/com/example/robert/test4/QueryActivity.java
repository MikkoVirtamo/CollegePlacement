package com.example.robert.test4;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SearchView;


public class QueryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_query);

        Intent intent = getIntent();
        if (intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            DoSearch(query);
        }

        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

public void DoSearch(String query) {

        DBhelper db = new DBhelper(this);
    SQLiteDatabase sdb = db.getWritableDatabase();

    //Cursor cursor = sdb.rawQuery("SELECT * FROM admissions", null);
//    Cursor cursor = sdb.rawQuery("SELECT * FROM admissions WHERE [State abbreviation (HD2016)] = '" + query + "'", null);
sdb.close();


    }



}
