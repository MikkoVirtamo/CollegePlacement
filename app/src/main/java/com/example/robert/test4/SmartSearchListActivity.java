package com.example.robert.test4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

public class SmartSearchListActivity extends AppCompatActivity {
ListView listView;
Button bt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_search_list);
//        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
       // getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.activity_smart_search_list);
        listView = (ListView) findViewById(R.id.smartsearch_list_view);
        bt1 = (Button)  findViewById(R.id.button2);
        Intent intent = getIntent();
        String[] arr;

       arr =  intent.getStringArrayExtra("var");
       for(int i = 0; i < arr.length; i++) {
           if(arr[i] == null) {
               arr[i] = " ";
           }

       }

        ListAdapter myAdapter = new ArrayAdapter<String>(SmartSearchListActivity.this, android.R.layout.simple_list_item_1,arr);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SmartSearchListActivity.this, DisplayPage.class);
                String str = (String) listView.getItemAtPosition(i);
                intent.putExtra("variable", str);
                startActivity(intent);
            }
        });


        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }



}
