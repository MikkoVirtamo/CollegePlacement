package com.example.robert.test4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ComparePage extends AppCompatActivity {

    ListView listview;

    TextView textView;

    ArrayList myList;

    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_page);


        textView = (TextView) findViewById(R.id.textView2);

        listview = (ListView) findViewById(R.id.listView);

        button = (Button) findViewById(R.id.button9);

        CompareList compareList = new CompareList();


        //ArrayList myList;

        CompareList c = (CompareList) getApplication();
                //(CompareList) getApplication();

        myList = c.getList();

        if (myList.size() != 0) {

            textView.setText(myList.get(0).toString());


        } else {


            textView.setText("List is Empty");

        }


        if (myList.size() > 0) {

            CustomAdapter customAdapter = new CustomAdapter();

            listview.setAdapter(customAdapter);


        }

      


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(ComparePage.this, CompareTwo.class);

                String str = (String) myList.get(i).toString();

                intent.putExtra("variable", str);

                startActivity(intent);

            }

        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(myList.size() > 4) {
                    Toast.makeText(ComparePage.this, "No more than three can be compared",
                            Toast.LENGTH_LONG).show();

                } else {

                    Intent intent = new Intent(ComparePage.this, CompareTwo.class);
                    startActivity(intent);
                }
            }
        });


    }




    public void goBack(View view) {

        Intent intent = new Intent(ComparePage.this, MainActivity.class);

        startActivity(intent);

    }


    class CustomAdapter extends BaseAdapter {


        @Override

        public int getCount() {


            return myList.size();

        }


        @Override

        public Object getItem(int i) {

            return null;

        }


        @Override

        public long getItemId(int i) {

            return 0;

        }


        @Override

        public View getView(final int i, View view, ViewGroup viewGroup) {

            view = getLayoutInflater().inflate(R.layout.customlayout, null);

            TextView textView = (TextView) view.findViewById(R.id.schoolName);

            Button button = (Button) view.findViewById(R.id.remove);


            textView.setOnClickListener(new View.OnClickListener() {

                @Override

                public void onClick(View view) {

                    Intent intent = new Intent(ComparePage.this, CompareTwo.class);

                    String str = (String) myList.get(i).toString();

                    intent.putExtra("variable", str);

                    startActivity(intent);


                }


            });


            button.setOnClickListener(new View.OnClickListener() {


                @Override

                public void onClick(View v) {

                    // TODO Auto-generated method stub

                    String response = myList.get(i).toString() + " removed";

                    Toast.makeText(ComparePage.this, response,

                            Toast.LENGTH_SHORT).show();

                    myList.remove(i);

                    notifyDataSetChanged();

                }

            });


            textView.setText(myList.get(i).toString());

            return view;

        }

    }
}



