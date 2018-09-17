package com.example.robert.test4;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        TextView textView = (TextView) findViewById(R.id.textView3);
        textView.setText("ajshfgkjashfgkjsdfh");
*/

////////////////
        GraphView graph = (GraphView) findViewById(R.id.graph);
        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(1, 10000),
                new DataPoint(2, 5000),
                new DataPoint(3, 34800),
                new DataPoint(4, 2000),
                new DataPoint(5, 60000)
        });
        graph.addSeries(series);
        //graph.setTitle("Some stuff");

// styling
        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX()*255/4, (int) Math.abs(data.getY()*255/6), 100);
            }
        });

        series.setSpacing(50);

// draw values on top
        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.RED);
//series.setValuesOnTopSize(50);

        Button btnGo = (Button) findViewById(R.id.button);

        btnGo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                EditText editText = (EditText) findViewById(R.id.editText);
                Intent intent = new Intent(MainActivity.this,SearchPage.class);
                intent.putExtra("variable", editText.getText().toString());
                startActivity(intent);
            }
        });


        Button bttoSMS = (Button) findViewById(R.id.button8);

        bttoSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SActivity.class);
                startActivity(intent);
            }
        });
    }



    public void goToCompare(View view){
        Intent intent = new Intent(MainActivity.this,ComparePage.class);
        startActivity(intent);
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

    void onFilters(View view){
        Intent intent = new Intent(MainActivity.this,FiltersForSearch.class);
        startActivity(intent);
    }

}













