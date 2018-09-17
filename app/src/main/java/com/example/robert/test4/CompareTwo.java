package com.example.robert.test4;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;

public class CompareTwo extends AppCompatActivity {

    ArrayList myList;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CompareList c = (CompareList) getApplication();
        myList = c.getList();

        if(myList.size() == 2) {
            setContentView(R.layout.activity_compare_two);
        } else if(myList.size() == 3) {
            setContentView(R.layout.compare_three);
        } else if(myList.size() == 4) {
            setContentView(R.layout.compare_four);
        }


        else {
            setContentView(R.layout.activity_compare_two);
        }
        Cursor cursor1;
        Cursor cursor2;
        Cursor cursor3;
        Cursor cursor4;
        int school1Cost;
        int school2Cost;
        int school1Complete;
        int school2Complete;
        double school1debt;
        double school2debt;
        double faid1;
        double faid2;
        double completion61;
        double completion62;
        float loan1;
        float loan2;
        double earnings1;
        double earnings2;
        double admitted1;
        double admitted2;



        DBhelper db = new DBhelper(this);
        SQLiteDatabase sdb = db.getWritableDatabase();

        CompareList compareList = new CompareList();

//uncommennt if otherr thing fails
        //CompareList c = (CompareList) getApplication();
        //myList = c.getList();
        textView1 = (TextView) findViewById(R.id.school1);
        textView2 = (TextView) findViewById(R.id.school2);
        textView3 = (TextView) findViewById(R.id.textView8);
        textView4 = (TextView) findViewById(R.id.textView9);
        if (myList.size() == 2) {
            String school1 = myList.get(0).toString();
            String school2 = myList.get(1).toString();
            textView1.setText("2. " + school1);
            textView2.setText("4. " + school2);


            //Graph1
            cursor1 = sdb.rawQuery("SELECT [totalprice_instate_oncampus] FROM costs WHERE [Institution Name] = '" + school1 + "'", null);
            cursor2 = sdb.rawQuery("SELECT [totalprice_instate_oncampus] FROM costs WHERE [Institution Name] = '" + school2 + "'", null);
            cursor1.moveToFirst();
            cursor2.moveToFirst();
            //textView.append(cursor1.getString(0) + " " + cursor2.getString(0));
            if (cursor1.getString(0).equals("NULL") || cursor2.getString(0).equals("NULL") || cursor1.getString(0).equals("PrivacySuppressed") || cursor2.getString(0).equals("PrivacySuppressed") || cursor1.getString(0).equals("") || cursor2.getString(0).equals("")) {
                GraphView graph = (GraphView) findViewById(R.id.costsGraph);
                BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, 0),
                        new DataPoint(3, 0),
                        new DataPoint(4, 0),
                        new DataPoint(5, 0),


                });
                graph.addSeries(series);
                graph.setTitle("Couldn't compare costs");
                series.setDrawValuesOnTop(true);
                series.setValuesOnTopColor(Color.RED);

                series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });

            } else {
                school1Cost = Integer.parseInt(cursor1.getString(0));
                school2Cost = Integer.parseInt(cursor2.getString(0));
                GraphView graph = (GraphView) findViewById(R.id.costsGraph);
                BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, school1Cost),
                        new DataPoint(3, 0),
                        new DataPoint(4, school2Cost),
                        new DataPoint(5, 0),


                });
                graph.addSeries(series);
                graph.setTitle("In State Costs");
                series.setDrawValuesOnTop(true);
                series.setValuesOnTopColor(Color.RED);

                series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
            }


//Graph2

            cursor1 = sdb.rawQuery("SELECT [percent_completed_bachdegree_in4years] FROM gradrates WHERE [Institution Name] = '" + school1 + "'", null);
            cursor2 = sdb.rawQuery("SELECT [percent_completed_bachdegree_in4years] FROM gradrates WHERE [Institution Name] = '" + school2 + "'", null);
            cursor1.moveToFirst();
            cursor2.moveToFirst();

            if (cursor1.getString(0).equals("NULL") || cursor2.getString(0).equals("NULL") || cursor1.getString(0).equals("PrivacySuppressed") || cursor2.getString(0).equals("PrivacySuppressed") || cursor1.getString(0).equals("") || cursor2.getString(0).equals("")) {
                GraphView graph2 = (GraphView) findViewById(R.id.completionGraph);
                BarGraphSeries<DataPoint> series2 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, 0),
                        new DataPoint(3, 0),
                        new DataPoint(4, 0),
                        new DataPoint(5, 0),


                });
                graph2.addSeries(series2);
                graph2.setTitle("Couldn't compare completion rates");
                series2.setDrawValuesOnTop(true);
                series2.setValuesOnTopColor(Color.RED);

// styling
                series2.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
            } else {
                school1Complete = Integer.parseInt(cursor1.getString(0));
                school2Complete = Integer.parseInt(cursor2.getString(0));
                GraphView graph2 = (GraphView) findViewById(R.id.completionGraph);
                BarGraphSeries<DataPoint> series2 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, school1Complete),
                        new DataPoint(3, 0),
                        new DataPoint(4, school2Complete),
                        new DataPoint(5, 0),


                });
                graph2.addSeries(series2);
                graph2.setTitle("Percent Completed in 4 years");
                series2.setDrawValuesOnTop(true);
                series2.setValuesOnTopColor(Color.RED);

// styling
                series2.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
            }


            //Graph3
            cursor1 = sdb.rawQuery("SELECT [avg_debt_postcompletion] FROM debt WHERE [Institution Name] = '" + school1 + "'", null);
            cursor2 = sdb.rawQuery("SELECT [avg_debt_postcompletion] FROM debt WHERE [Institution Name] = '" + school2 + "'", null);
            cursor1.moveToFirst();
            cursor2.moveToFirst();

            if (cursor1.getString(0).equals("NULL") || cursor2.getString(0).equals("NULL") || cursor1.getString(0).equals("PrivacySuppressed") || cursor2.getString(0).equals("PrivacySuppressed") || cursor1.getString(0).equals("") || cursor2.getString(0).equals("")) {
                GraphView graph3 = (GraphView) findViewById(R.id.debtGraph);
                BarGraphSeries<DataPoint> series3 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, 0),
                        new DataPoint(3, 0),
                        new DataPoint(4, 0),
                        new DataPoint(5, 0),


                });
                graph3.addSeries(series3);
                graph3.setTitle("Couldn't compare debts");
                series3.setDrawValuesOnTop(true);
                series3.setValuesOnTopColor(Color.RED);

// styling
                series3.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
            } else {
                school1debt = Double.parseDouble(cursor1.getString(0));
                school2debt = Double.parseDouble(cursor2.getString(0));
                GraphView graph3 = (GraphView) findViewById(R.id.debtGraph);
                BarGraphSeries<DataPoint> series3 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, school1debt),
                        new DataPoint(3, 0),
                        new DataPoint(4, school2debt),
                        new DataPoint(5, 0),


                });
                graph3.addSeries(series3);
                graph3.setTitle("Average Debt After Graduating");
                series3.setDrawValuesOnTop(true);
                series3.setValuesOnTopColor(Color.RED);

// styling
                series3.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
            }


//Graph 4
            cursor1 = sdb.rawQuery("SELECT [avg_netprice_studentsawardedaid] FROM faid WHERE [Institution Name] = '" + school1 + "'", null);
            cursor2 = sdb.rawQuery("SELECT [avg_netprice_studentsawardedaid] FROM faid WHERE [Institution Name] = '" + school2 + "'", null);
            cursor1.moveToFirst();
            cursor2.moveToFirst();

            if (cursor1.getString(0).equals("NULL") || cursor2.getString(0).equals("NULL") || cursor1.getString(0).equals("PrivacySuppressed") || cursor2.getString(0).equals("PrivacySuppressed") || cursor1.getString(0).equals("") || cursor2.getString(0).equals("")) {
                GraphView graph4 = (GraphView) findViewById(R.id.faidGraph);
                BarGraphSeries<DataPoint> series4 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, 0),
                        new DataPoint(3, 0),
                        new DataPoint(4, 0),
                        new DataPoint(5, 0),


                });
                graph4.addSeries(series4);
                graph4.setTitle("Couldn't compare financial aid");
                series4.setDrawValuesOnTop(true);
                series4.setValuesOnTopColor(Color.RED);

// styling
                series4.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
            } else {
                faid1 = Integer.parseInt(cursor1.getString(0));
                faid2 = Integer.parseInt(cursor2.getString(0));
                GraphView graph4 = (GraphView) findViewById(R.id.faidGraph);
                BarGraphSeries<DataPoint> series4 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, faid1),
                        new DataPoint(3, 0),
                        new DataPoint(4, faid2),
                        new DataPoint(5, 0),


                });
                graph4.addSeries(series4);
                graph4.setTitle("Average Debt After Graduating");
                series4.setDrawValuesOnTop(true);
                series4.setValuesOnTopColor(Color.RED);

// styling
                series4.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
            }


            //Graph 5
            cursor1 = sdb.rawQuery("SELECT [percent_completed_bachdegree_in6years] FROM gradrates WHERE [Institution Name] = '" + school1 + "'", null);
            cursor2 = sdb.rawQuery("SELECT [percent_completed_bachdegree_in6years] FROM gradrates WHERE [Institution Name] = '" + school2 + "'", null);
            cursor1.moveToFirst();
            cursor2.moveToFirst();

            if (cursor1.getString(0).equals("NULL") || cursor2.getString(0).equals("NULL") || cursor1.getString(0).equals("PrivacySuppressed") || cursor2.getString(0).equals("PrivacySuppressed") || cursor1.getString(0).equals("") || cursor2.getString(0).equals("")) {
                GraphView graph5 = (GraphView) findViewById(R.id.completion6Graph);
                BarGraphSeries<DataPoint> series5 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, 0),
                        new DataPoint(3, 0),
                        new DataPoint(4, 0),
                        new DataPoint(5, 0),


                });
                graph5.addSeries(series5);
                graph5.setTitle("Couldn't compare 6 year completion");
                series5.setDrawValuesOnTop(true);
                series5.setValuesOnTopColor(Color.RED);

// styling
                series5.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
            } else {
                completion61 = Integer.parseInt(cursor1.getString(0));
                completion62 = Integer.parseInt(cursor2.getString(0));
                GraphView graph5 = (GraphView) findViewById(R.id.completion6Graph);
                BarGraphSeries<DataPoint> series5 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, completion61),
                        new DataPoint(3, 0),
                        new DataPoint(4, completion62),
                        new DataPoint(5, 0),


                });
                graph5.addSeries(series5);
                graph5.setTitle("Percent Completed in 6 years");
                series5.setDrawValuesOnTop(true);
                series5.setValuesOnTopColor(Color.RED);

// styling
                series5.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
            }


            //Graph 6
            cursor1 = sdb.rawQuery("SELECT [avg_loan_payment] FROM debt WHERE [Institution Name] = '" + school1 + "'", null);
            cursor2 = sdb.rawQuery("SELECT [avg_loan_payment] FROM debt WHERE [Institution Name] = '" + school2 + "'", null);
            cursor1.moveToFirst();
            cursor2.moveToFirst();

            if (cursor1.getString(0).equals("NULL") || cursor2.getString(0).equals("NULL") || cursor1.getString(0).equals("PrivacySuppressed") || cursor2.getString(0).equals("PrivacySuppressed") || cursor1.getString(0).equals("") || cursor2.getString(0).equals("")) {
                GraphView graph6 = (GraphView) findViewById(R.id.loanGraph);
                BarGraphSeries<DataPoint> series6 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, 0),
                        new DataPoint(3, 0),
                        new DataPoint(4, 0),
                        new DataPoint(5, 0),


                });
                graph6.addSeries(series6);
                graph6.setTitle("Couldn't compare loans");
                series6.setDrawValuesOnTop(true);
                series6.setValuesOnTopColor(Color.RED);

// styling
                series6.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
            } else {
                loan1 = Float.parseFloat(cursor1.getString(0));
                loan2 = Float.parseFloat(cursor2.getString(0));
                GraphView graph6 = (GraphView) findViewById(R.id.loanGraph);
                BarGraphSeries<DataPoint> series6 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, loan1),
                        new DataPoint(3, 0),
                        new DataPoint(4, loan2),
                        new DataPoint(5, 0),


                });
                graph6.addSeries(series6);
                graph6.setTitle("Average loans");
                series6.setDrawValuesOnTop(true);
                series6.setValuesOnTopColor(Color.RED);

// styling
                series6.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
            }


            //Graph 7
            cursor1 = sdb.rawQuery("SELECT [avg_earnings_postgrad] FROM debt WHERE [Institution Name] = '" + school1 + "'", null);
            cursor2 = sdb.rawQuery("SELECT [avg_earnings_postgrad] FROM debt WHERE [Institution Name] = '" + school2 + "'", null);
            cursor1.moveToFirst();
            cursor2.moveToFirst();

            if (cursor1.getString(0).equals("NULL") || cursor2.getString(0).equals("NULL") || cursor1.getString(0).equals("") || cursor2.getString(0).equals("")) {
                GraphView graph7 = (GraphView) findViewById(R.id.earningsGraph);
                BarGraphSeries<DataPoint> series7 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, 0),
                        new DataPoint(3, 0),
                        new DataPoint(4, 0),
                        new DataPoint(5, 0),


                });
                graph7.addSeries(series7);
                graph7.setTitle("Couldn't compare loans");
                series7.setDrawValuesOnTop(true);
                series7.setValuesOnTopColor(Color.RED);

// styling
                series7.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
            } else {
                earnings1 = Integer.parseInt(cursor1.getString(0));
                earnings2 = Integer.parseInt(cursor2.getString(0));
                GraphView graph7 = (GraphView) findViewById(R.id.earningsGraph);
                BarGraphSeries<DataPoint> series7 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, earnings1),
                        new DataPoint(3, 0),
                        new DataPoint(4, earnings2),
                        new DataPoint(5, 0),


                });
                graph7.addSeries(series7);
                graph7.setTitle("Average earnings post graduation");
                series7.setDrawValuesOnTop(true);
                series7.setValuesOnTopColor(Color.RED);

// styling
                series7.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
            }


            //Graph 8
            cursor1 = sdb.rawQuery("SELECT [total_applicants], [total_admissions] FROM admissions WHERE [Institution Name] = '" + school1 + "'", null);
            cursor2 = sdb.rawQuery("SELECT [total_applicants], [total_admissions] FROM admissions WHERE [Institution Name] = '" + school2 + "'", null);
            cursor1.moveToFirst();
            cursor2.moveToFirst();

            if (cursor1.getString(0).equals("NULL") || cursor2.getString(0).equals("NULL") || cursor1.getString(0).equals("PrivacySuppressed") || cursor2.getString(0).equals("PrivacySuppressed") || cursor1.getString(0).equals("") || cursor2.getString(0).equals("") || cursor1.getString(1).equals("") || cursor2.getString(1).equals("")) {
                GraphView graph8 = (GraphView) findViewById(R.id.admittedGraph);
                BarGraphSeries<DataPoint> series8 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, 0),
                        new DataPoint(3, 0),
                        new DataPoint(4, 0),
                        new DataPoint(5, 0),


                });
                graph8.addSeries(series8);
                graph8.setTitle("Couldn't compare admittance rates");
                series8.setDrawValuesOnTop(true);
                series8.setValuesOnTopColor(Color.RED);

// styling
                series8.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
            } else {
                admitted1 = (Double.parseDouble(cursor1.getString(1)) / Double.parseDouble(cursor1.getString(0))) * 100;
                admitted2 = (Double.parseDouble(cursor2.getString(1)) / Double.parseDouble(cursor2.getString(0))) * 100;
                GraphView graph8 = (GraphView) findViewById(R.id.admittedGraph);
                BarGraphSeries<DataPoint> series8 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, admitted1),
                        new DataPoint(3, 0),
                        new DataPoint(4, admitted2),
                        new DataPoint(5, 0),


                });
                graph8.addSeries(series8);
                graph8.setTitle("Percent Admitted");
                series8.setDrawValuesOnTop(true);
                series8.setValuesOnTopColor(Color.RED);

// styling
                series8.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
            }
        } else if(myList.size() == 3) {
            String school1 = myList.get(0).toString();
            String school2 = myList.get(1).toString();
            String school3 = myList.get(2).toString();
            textView1.setText("2. " + school1);
            textView3.setText("3. " + school3);
            textView2.setText("4. " + school2);


            //Graph1
            cursor1 = sdb.rawQuery("SELECT [totalprice_instate_oncampus] FROM costs WHERE [Institution Name] = '" + school1 + "'", null);
            cursor2 = sdb.rawQuery("SELECT [totalprice_instate_oncampus] FROM costs WHERE [Institution Name] = '" + school2 + "'", null);
            cursor3 = sdb.rawQuery("SELECT [totalprice_instate_oncampus] FROM costs WHERE [Institution Name] = '" + school3 + "'", null);
            cursor1.moveToFirst();
            cursor2.moveToFirst();
            cursor3.moveToNext();
            //textView.append(cursor1.getString(0) + " " + cursor2.getString(0));
            if (cursor1.getString(0).equals("NULL") || cursor2.getString(0).equals("NULL") || cursor1.getString(0).equals("PrivacySuppressed") || cursor2.getString(0).equals("PrivacySuppressed") || cursor1.getString(0).equals("") || cursor2.getString(0).equals("") || cursor3.getString(0).equals("NULL") || cursor3.getString(0).equals("PrivacySuppressed") || cursor3.getString(0).equals("")) {
                GraphView graph = (GraphView) findViewById(R.id.costsGraph);
                BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, 0),
                        new DataPoint(3, 0),
                        new DataPoint(4, 0),
                        new DataPoint(5, 0),


                });
                graph.addSeries(series);
                graph.setTitle("Couldn't compare costs");
                series.setDrawValuesOnTop(true);
                series.setValuesOnTopColor(Color.RED);

                series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });

            } else {
                school1Cost = Integer.parseInt(cursor1.getString(0));
                school2Cost = Integer.parseInt(cursor2.getString(0));
                int schoolCost3 = Integer.parseInt(cursor3.getString(0));
                GraphView graph = (GraphView) findViewById(R.id.costsGraph);
                BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, school1Cost),
                        new DataPoint(3, schoolCost3),
                        new DataPoint(4, school2Cost),
                        new DataPoint(5, 0),


                });
                graph.addSeries(series);
                graph.setTitle("In State Costs");
                series.setDrawValuesOnTop(true);
                series.setValuesOnTopColor(Color.RED);

                series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
            }


//Graph2

            cursor1 = sdb.rawQuery("SELECT [percent_completed_bachdegree_in4years] FROM gradrates WHERE [Institution Name] = '" + school1 + "'", null);
            cursor2 = sdb.rawQuery("SELECT [percent_completed_bachdegree_in4years] FROM gradrates WHERE [Institution Name] = '" + school2 + "'", null);
            cursor3 = sdb.rawQuery("SELECT [percent_completed_bachdegree_in4years] FROM gradrates WHERE [Institution Name] = '" + school3 + "'", null);
            cursor1.moveToFirst();
            cursor2.moveToFirst();
            cursor3.moveToFirst();
            if (cursor1.getString(0).equals("NULL") || cursor2.getString(0).equals("NULL") || cursor1.getString(0).equals("PrivacySuppressed") || cursor2.getString(0).equals("PrivacySuppressed") || cursor1.getString(0).equals("") || cursor2.getString(0).equals("") || cursor3.getString(0).equals("NULL") || cursor3.getString(0).equals("PrivacySuppressed") || cursor3.getString(0).equals("")) {
                GraphView graph2 = (GraphView) findViewById(R.id.completionGraph);
                BarGraphSeries<DataPoint> series2 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, 0),
                        new DataPoint(3, 0),
                        new DataPoint(4, 0),
                        new DataPoint(5, 0),


                });
                graph2.addSeries(series2);
                graph2.setTitle("Couldn't compare completion rates");
                series2.setDrawValuesOnTop(true);
                series2.setValuesOnTopColor(Color.RED);

// styling
                series2.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
            } else {
                school1Complete = Integer.parseInt(cursor1.getString(0));
                school2Complete = Integer.parseInt(cursor2.getString(0));
                int school3Complete = Integer.parseInt(cursor3.getString(0).toString());
                GraphView graph2 = (GraphView) findViewById(R.id.completionGraph);
                BarGraphSeries<DataPoint> series2 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, school1Complete),
                        new DataPoint(3, school3Complete),
                        new DataPoint(4, school2Complete),
                        new DataPoint(5, 0),


                });
                graph2.addSeries(series2);
                graph2.setTitle("Percent Completed in 4 years");
                series2.setDrawValuesOnTop(true);
                series2.setValuesOnTopColor(Color.RED);

// styling
                series2.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
            }


            //Graph3
            cursor1 = sdb.rawQuery("SELECT [avg_debt_postcompletion] FROM debt WHERE [Institution Name] = '" + school1 + "'", null);
            cursor2 = sdb.rawQuery("SELECT [avg_debt_postcompletion] FROM debt WHERE [Institution Name] = '" + school2 + "'", null);
            cursor3 = sdb.rawQuery("SELECT [avg_debt_postcompletion] FROM debt WHERE [Institution Name] = '" + school3 + "'", null);

            cursor1.moveToFirst();
            cursor2.moveToFirst();
            cursor3.moveToNext();

            if (cursor1.getString(0).equals("NULL") || cursor2.getString(0).equals("NULL") || cursor1.getString(0).equals("PrivacySuppressed") || cursor2.getString(0).equals("PrivacySuppressed") || cursor1.getString(0).equals("") || cursor2.getString(0).equals("") || cursor3.getString(0).equals("NULL") || cursor3.getString(0).equals("PrivacySuppressed") || cursor3.getString(0).equals("")) {
                GraphView graph3 = (GraphView) findViewById(R.id.debtGraph);
                BarGraphSeries<DataPoint> series3 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, 0),
                        new DataPoint(3, 0),
                        new DataPoint(4, 0),
                        new DataPoint(5, 0),


                });
                graph3.addSeries(series3);
                graph3.setTitle("Couldn't compare debts");
                series3.setDrawValuesOnTop(true);
                series3.setValuesOnTopColor(Color.RED);

// styling
                series3.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
            } else {
                school1debt = Double.parseDouble(cursor1.getString(0));
                school2debt = Double.parseDouble(cursor2.getString(0));
                double school3debt = Double.parseDouble(cursor3.getString(0));
                GraphView graph3 = (GraphView) findViewById(R.id.debtGraph);
                BarGraphSeries<DataPoint> series3 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, school1debt),
                        new DataPoint(3, school3debt),
                        new DataPoint(4, school2debt),
                        new DataPoint(5, 0),


                });
                graph3.addSeries(series3);
                graph3.setTitle("Average Debt After Graduating");
                series3.setDrawValuesOnTop(true);
                series3.setValuesOnTopColor(Color.RED);

// styling
                series3.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
            }


//Graph 4
            cursor1 = sdb.rawQuery("SELECT [avg_netprice_studentsawardedaid] FROM faid WHERE [Institution Name] = '" + school1 + "'", null);
            cursor2 = sdb.rawQuery("SELECT [avg_netprice_studentsawardedaid] FROM faid WHERE [Institution Name] = '" + school2 + "'", null);
            cursor3 = sdb.rawQuery("SELECT [avg_netprice_studentsawardedaid] FROM faid WHERE [Institution Name] = '" + school3 + "'", null);

            cursor1.moveToFirst();
            cursor2.moveToFirst();
            cursor3.moveToNext();

            if (cursor1.getString(0).equals("NULL") || cursor2.getString(0).equals("NULL") || cursor1.getString(0).equals("PrivacySuppressed") || cursor2.getString(0).equals("PrivacySuppressed") || cursor1.getString(0).equals("") || cursor2.getString(0).equals("") || cursor3.getString(0).equals("NULL") || cursor3.getString(0).equals("PrivacySuppressed") || cursor3.getString(0).equals("")) {
                GraphView graph4 = (GraphView) findViewById(R.id.faidGraph);
                BarGraphSeries<DataPoint> series4 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, 0),
                        new DataPoint(3, 0),
                        new DataPoint(4, 0),
                        new DataPoint(5, 0),


                });
                graph4.addSeries(series4);
                graph4.setTitle("Couldn't compare financial aid");
                series4.setDrawValuesOnTop(true);
                series4.setValuesOnTopColor(Color.RED);

// styling
                series4.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
            } else {
                faid1 = Integer.parseInt(cursor1.getString(0));
                faid2 = Integer.parseInt(cursor2.getString(0));
                int faid3 = Integer.parseInt(cursor3.getString(0));
                GraphView graph4 = (GraphView) findViewById(R.id.faidGraph);
                BarGraphSeries<DataPoint> series4 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, faid1),
                        new DataPoint(3, faid3),
                        new DataPoint(4, faid2),
                        new DataPoint(5, 0),


                });
                graph4.addSeries(series4);
                graph4.setTitle("Average Debt After Graduating");
                series4.setDrawValuesOnTop(true);
                series4.setValuesOnTopColor(Color.RED);

// styling
                series4.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
            }


            //Graph 5
            cursor1 = sdb.rawQuery("SELECT [percent_completed_bachdegree_in6years] FROM gradrates WHERE [Institution Name] = '" + school1 + "'", null);
            cursor2 = sdb.rawQuery("SELECT [percent_completed_bachdegree_in6years] FROM gradrates WHERE [Institution Name] = '" + school2 + "'", null);
            cursor3 = sdb.rawQuery("SELECT [percent_completed_bachdegree_in6years] FROM gradrates WHERE [Institution Name] = '" + school3 + "'", null);

            cursor1.moveToFirst();
            cursor2.moveToFirst();
            cursor3.moveToNext();
            if (cursor1.getString(0).equals("NULL") || cursor2.getString(0).equals("NULL") || cursor1.getString(0).equals("PrivacySuppressed") || cursor2.getString(0).equals("PrivacySuppressed") || cursor1.getString(0).equals("") || cursor2.getString(0).equals("") || cursor3.getString(0).equals("NULL") || cursor3.getString(0).equals("PrivacySuppressed") || cursor3.getString(0).equals("")) {
                GraphView graph5 = (GraphView) findViewById(R.id.completion6Graph);
                BarGraphSeries<DataPoint> series5 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, 0),
                        new DataPoint(3, 0),
                        new DataPoint(4, 0),
                        new DataPoint(5, 0),


                });
                graph5.addSeries(series5);
                graph5.setTitle("Couldn't compare 6 year completion");
                series5.setDrawValuesOnTop(true);
                series5.setValuesOnTopColor(Color.RED);

// styling
                series5.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
            } else {
                completion61 = Integer.parseInt(cursor1.getString(0));
                completion62 = Integer.parseInt(cursor2.getString(0));
                int completion63 = Integer.parseInt(cursor3.getString(0));
                GraphView graph5 = (GraphView) findViewById(R.id.completion6Graph);
                BarGraphSeries<DataPoint> series5 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, completion61),
                        new DataPoint(3, completion63),
                        new DataPoint(4, completion62),
                        new DataPoint(5, 0),


                });
                graph5.addSeries(series5);
                graph5.setTitle("Percent Completed in 6 years");
                series5.setDrawValuesOnTop(true);
                series5.setValuesOnTopColor(Color.RED);

// styling
                series5.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
            }


            //Graph 6
            cursor1 = sdb.rawQuery("SELECT [avg_loan_payment] FROM debt WHERE [Institution Name] = '" + school1 + "'", null);
            cursor2 = sdb.rawQuery("SELECT [avg_loan_payment] FROM debt WHERE [Institution Name] = '" + school2 + "'", null);
            cursor3 = sdb.rawQuery("SELECT [avg_loan_payment] FROM debt WHERE [Institution Name] = '" + school3 + "'", null);

            cursor1.moveToFirst();
            cursor2.moveToFirst();
            cursor3.moveToNext();

            if (cursor1.getString(0).equals("NULL") || cursor2.getString(0).equals("NULL") || cursor1.getString(0).equals("PrivacySuppressed") || cursor2.getString(0).equals("PrivacySuppressed") || cursor1.getString(0).equals("") || cursor2.getString(0).equals("") || cursor3.getString(0).equals("NULL") || cursor3.getString(0).equals("PrivacySuppressed") || cursor3.getString(0).equals("")) {
                GraphView graph6 = (GraphView) findViewById(R.id.loanGraph);
                BarGraphSeries<DataPoint> series6 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, 0),
                        new DataPoint(3, 0),
                        new DataPoint(4, 0),
                        new DataPoint(5, 0),


                });
                graph6.addSeries(series6);
                graph6.setTitle("Couldn't compare loans");
                series6.setDrawValuesOnTop(true);
                series6.setValuesOnTopColor(Color.RED);

// styling
                series6.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
            } else {
                loan1 = Float.parseFloat(cursor1.getString(0));
                loan2 = Float.parseFloat(cursor2.getString(0));
                float loan3 = Float.parseFloat(cursor3.getString(0));
                GraphView graph6 = (GraphView) findViewById(R.id.loanGraph);
                BarGraphSeries<DataPoint> series6 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, loan1),
                        new DataPoint(3, loan3),
                        new DataPoint(4, loan2),
                        new DataPoint(5, 0),


                });
                graph6.addSeries(series6);
                graph6.setTitle("Average loans");
                series6.setDrawValuesOnTop(true);
                series6.setValuesOnTopColor(Color.RED);

// styling
                series6.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
            }


            //Graph 7
            cursor1 = sdb.rawQuery("SELECT [avg_earnings_postgrad] FROM debt WHERE [Institution Name] = '" + school1 + "'", null);
            cursor2 = sdb.rawQuery("SELECT [avg_earnings_postgrad] FROM debt WHERE [Institution Name] = '" + school2 + "'", null);
            cursor3 = sdb.rawQuery("SELECT [avg_earnings_postgrad] FROM debt WHERE [Institution Name] = '" + school3 + "'", null);

            cursor1.moveToFirst();
            cursor2.moveToFirst();
            cursor3.moveToNext();

            if (cursor1.getString(0).equals("NULL") || cursor2.getString(0).equals("NULL") || cursor1.getString(0).equals("PrivacySuppressed") || cursor2.getString(0).equals("PrivacySuppressed") || cursor1.getString(0).equals("") || cursor2.getString(0).equals("") || cursor3.getString(0).equals("NULL") || cursor3.getString(0).equals("PrivacySuppressed") || cursor3.getString(0).equals("")) {
                GraphView graph7 = (GraphView) findViewById(R.id.earningsGraph);
                BarGraphSeries<DataPoint> series7 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, 0),
                        new DataPoint(3, 0),
                        new DataPoint(4, 0),
                        new DataPoint(5, 0),


                });
                graph7.addSeries(series7);
                graph7.setTitle("Couldn't compare loans");
                series7.setDrawValuesOnTop(true);
                series7.setValuesOnTopColor(Color.RED);

// styling
                series7.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
            } else {
                earnings1 = Integer.parseInt(cursor1.getString(0));
                earnings2 = Integer.parseInt(cursor2.getString(0));
                int earnings3 = Integer.parseInt(cursor3.getString(0));
                GraphView graph7 = (GraphView) findViewById(R.id.earningsGraph);
                BarGraphSeries<DataPoint> series7 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, earnings1),
                        new DataPoint(3, earnings3),
                        new DataPoint(4, earnings2),
                        new DataPoint(5, 0),


                });
                graph7.addSeries(series7);
                graph7.setTitle("Average earnings post graduation");
                series7.setDrawValuesOnTop(true);
                series7.setValuesOnTopColor(Color.RED);

// styling
                series7.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
            }


            //Graph 8
            cursor1 = sdb.rawQuery("SELECT [total_applicants], [total_admissions] FROM admissions WHERE [Institution Name] = '" + school1 + "'", null);
            cursor2 = sdb.rawQuery("SELECT [total_applicants], [total_admissions] FROM admissions WHERE [Institution Name] = '" + school2 + "'", null);
            cursor3 = sdb.rawQuery("SELECT [total_applicants], [total_admissions] FROM admissions WHERE [Institution Name] = '" + school3 + "'", null);

            cursor1.moveToFirst();
            cursor2.moveToFirst();
            cursor3.moveToFirst();
            if (cursor1.getString(0).equals("NULL") || cursor2.getString(0).equals("NULL") || cursor1.getString(0).equals("PrivacySuppressed") || cursor2.getString(0).equals("PrivacySuppressed") || cursor1.getString(0).equals("") || cursor2.getString(0).equals("") || cursor3.getString(0).equals("NULL") || cursor3.getString(0).equals("PrivacySuppressed") || cursor3.getString(0).equals("")) {
                GraphView graph8 = (GraphView) findViewById(R.id.admittedGraph);
                BarGraphSeries<DataPoint> series8 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, 0),
                        new DataPoint(3, 0),
                        new DataPoint(4, 0),
                        new DataPoint(5, 0),


                });
                graph8.addSeries(series8);
                graph8.setTitle("Couldn't compare admittance rates");
                series8.setDrawValuesOnTop(true);
                series8.setValuesOnTopColor(Color.RED);

// styling
                series8.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
            } else {
                admitted1 = (Double.parseDouble(cursor1.getString(1)) / Double.parseDouble(cursor1.getString(0))) * 100;
                admitted2 = (Double.parseDouble(cursor2.getString(1)) / Double.parseDouble(cursor2.getString(0))) * 100;
                double admitted3 = (Double.parseDouble(cursor3.getString(1)) / Double.parseDouble(cursor3.getString(0))) * 100;
                GraphView graph8 = (GraphView) findViewById(R.id.admittedGraph);
                BarGraphSeries<DataPoint> series8 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, admitted1),
                        new DataPoint(3, admitted3),
                        new DataPoint(4, admitted2),
                        new DataPoint(5, 0),


                });
                graph8.addSeries(series8);
                graph8.setTitle("Percent Admitted");
                series8.setDrawValuesOnTop(true);
                series8.setValuesOnTopColor(Color.RED);

// styling
                series8.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
            }


            // end of size3 thing
        } else if (myList.size() == 4) {
            String school1 = myList.get(0).toString();
            String school2 = myList.get(1).toString();
            String school3 = myList.get(2).toString();
            String school4 = myList.get(3).toString();
            textView1.setText("2. " + school1);
            textView3.setText("3. " + school3);
            textView2.setText("4. " + school2);
            textView4.setText("5. " + school4);


            //Graph1
            cursor1 = sdb.rawQuery("SELECT [totalprice_instate_oncampus] FROM costs WHERE [Institution Name] = '" + school1 + "'", null);
            cursor2 = sdb.rawQuery("SELECT [totalprice_instate_oncampus] FROM costs WHERE [Institution Name] = '" + school2 + "'", null);
            cursor3 = sdb.rawQuery("SELECT [totalprice_instate_oncampus] FROM costs WHERE [Institution Name] = '" + school3 + "'", null);
            cursor4 = sdb.rawQuery("SELECT [totalprice_instate_oncampus] FROM costs WHERE [Institution Name] = '" + school4 + "'", null);

            cursor1.moveToFirst();
            cursor2.moveToFirst();
            cursor3.moveToFirst();
            cursor4.moveToFirst();
            //textView.append(cursor1.getString(0) + " " + cursor2.getString(0));
            if (cursor1.getString(0).equals("NULL") || cursor2.getString(0).equals("NULL") || cursor1.getString(0).equals("PrivacySuppressed") || cursor2.getString(0).equals("PrivacySuppressed") || cursor1.getString(0).equals("") || cursor2.getString(0).equals("") || cursor3.getString(0).equals("NULL") || cursor3.getString(0).equals("PrivacySuppressed") || cursor3.getString(0).equals("") || cursor4.getString(0).equals("NULL") || cursor4.getString(0).equals("PrivacySuppressed") || cursor4.getString(0).equals("")) {
                GraphView graph = (GraphView) findViewById(R.id.costsGraph);
                BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, 0),
                        new DataPoint(3, 0),
                        new DataPoint(4, 0),
                        new DataPoint(5, 0),


                });
                graph.addSeries(series);
                graph.setTitle("Couldn't compare costs");
                series.setDrawValuesOnTop(true);
                series.setValuesOnTopColor(Color.RED);

                series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });

            } else {
                school1Cost = Integer.parseInt(cursor1.getString(0));
                school2Cost = Integer.parseInt(cursor2.getString(0));
                int schoolCost3 = Integer.parseInt(cursor3.getString(0));
                int schoolCost4 = Integer.parseInt(cursor4.getString(0));
                GraphView graph = (GraphView) findViewById(R.id.costsGraph);
                BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, school1Cost),
                        new DataPoint(3, schoolCost3),
                        new DataPoint(4, school2Cost),
                        new DataPoint(5, schoolCost4),


                });
                graph.addSeries(series);
                graph.setTitle("In State Costs");
                series.setDrawValuesOnTop(true);
                series.setValuesOnTopColor(Color.RED);

                series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
            }


//Graph2

            cursor1 = sdb.rawQuery("SELECT [percent_completed_bachdegree_in4years] FROM gradrates WHERE [Institution Name] = '" + school1 + "'", null);
            cursor2 = sdb.rawQuery("SELECT [percent_completed_bachdegree_in4years] FROM gradrates WHERE [Institution Name] = '" + school2 + "'", null);
            cursor3 = sdb.rawQuery("SELECT [percent_completed_bachdegree_in4years] FROM gradrates WHERE [Institution Name] = '" + school3 + "'", null);
            cursor4 = sdb.rawQuery("SELECT [percent_completed_bachdegree_in4years] FROM gradrates WHERE [Institution Name] = '" + school4 + "'", null);

            cursor1.moveToFirst();
            cursor2.moveToFirst();
            cursor3.moveToFirst();
            cursor4.moveToFirst();
            if (cursor1.getString(0).equals("NULL") || cursor2.getString(0).equals("NULL") || cursor1.getString(0).equals("PrivacySuppressed") || cursor2.getString(0).equals("PrivacySuppressed") || cursor1.getString(0).equals("") || cursor2.getString(0).equals("") || cursor3.getString(0).equals("NULL") || cursor3.getString(0).equals("PrivacySuppressed") || cursor3.getString(0).equals("") || cursor4.getString(0).equals("NULL") || cursor4.getString(0).equals("PrivacySuppressed") || cursor4.getString(0).equals("")) {                GraphView graph2 = (GraphView) findViewById(R.id.completionGraph);
                BarGraphSeries<DataPoint> series2 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, 0),
                        new DataPoint(3, 0),
                        new DataPoint(4, 0),
                        new DataPoint(5, 0),


                });
                graph2.addSeries(series2);
                graph2.setTitle("Couldn't compare completion rates");
                series2.setDrawValuesOnTop(true);
                series2.setValuesOnTopColor(Color.RED);

// styling
                series2.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
            } else {
                school1Complete = Integer.parseInt(cursor1.getString(0));
                school2Complete = Integer.parseInt(cursor2.getString(0));
                int school3Complete = Integer.parseInt(cursor3.getString(0).toString());
                int school4Complete = Integer.parseInt(cursor4.getString(0).toString());

                GraphView graph2 = (GraphView) findViewById(R.id.completionGraph);
                BarGraphSeries<DataPoint> series2 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, school1Complete),
                        new DataPoint(3, school3Complete),
                        new DataPoint(4, school2Complete),
                        new DataPoint(5, school4Complete),


                });
                graph2.addSeries(series2);
                graph2.setTitle("Percent Completed in 4 years");
                series2.setDrawValuesOnTop(true);
                series2.setValuesOnTopColor(Color.RED);

// styling
                series2.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
            }


            //Graph3
            cursor1 = sdb.rawQuery("SELECT [avg_debt_postcompletion] FROM debt WHERE [Institution Name] = '" + school1 + "'", null);
            cursor2 = sdb.rawQuery("SELECT [avg_debt_postcompletion] FROM debt WHERE [Institution Name] = '" + school2 + "'", null);
            cursor3 = sdb.rawQuery("SELECT [avg_debt_postcompletion] FROM debt WHERE [Institution Name] = '" + school3 + "'", null);
            cursor4 = sdb.rawQuery("SELECT [avg_debt_postcompletion] FROM debt WHERE [Institution Name] = '" + school4 + "'", null);

            cursor1.moveToFirst();
            cursor2.moveToFirst();
            cursor3.moveToFirst();
            cursor4.moveToFirst();

            if (cursor1.getString(0).equals("NULL") || cursor2.getString(0).equals("NULL") || cursor1.getString(0).equals("PrivacySuppressed") || cursor2.getString(0).equals("PrivacySuppressed") || cursor1.getString(0).equals("") || cursor2.getString(0).equals("") || cursor3.getString(0).equals("NULL") || cursor3.getString(0).equals("PrivacySuppressed") || cursor3.getString(0).equals("") || cursor4.getString(0).equals("NULL") || cursor4.getString(0).equals("PrivacySuppressed") || cursor4.getString(0).equals("")) {                GraphView graph3 = (GraphView) findViewById(R.id.debtGraph);
                BarGraphSeries<DataPoint> series3 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, 0),
                        new DataPoint(3, 0),
                        new DataPoint(4, 0),
                        new DataPoint(5, 0),


                });
                graph3.addSeries(series3);
                graph3.setTitle("Couldn't compare debts");
                series3.setDrawValuesOnTop(true);
                series3.setValuesOnTopColor(Color.RED);

// styling
                series3.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
            } else {
                school1debt = Double.parseDouble(cursor1.getString(0));
                school2debt = Double.parseDouble(cursor2.getString(0));
                double school3debt = Double.parseDouble(cursor3.getString(0));
                double school4debt = Double.parseDouble(cursor4.getString(0));

                GraphView graph3 = (GraphView) findViewById(R.id.debtGraph);
                BarGraphSeries<DataPoint> series3 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, school1debt),
                        new DataPoint(3, school3debt),
                        new DataPoint(4, school2debt),
                        new DataPoint(5, school4debt),


                });
                graph3.addSeries(series3);
                graph3.setTitle("Average Debt After Graduating");
                series3.setDrawValuesOnTop(true);
                series3.setValuesOnTopColor(Color.RED);

// styling
                series3.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
            }


//Graph 4
            cursor1 = sdb.rawQuery("SELECT [avg_netprice_studentsawardedaid] FROM faid WHERE [Institution Name] = '" + school1 + "'", null);
            cursor2 = sdb.rawQuery("SELECT [avg_netprice_studentsawardedaid] FROM faid WHERE [Institution Name] = '" + school2 + "'", null);
            cursor3 = sdb.rawQuery("SELECT [avg_netprice_studentsawardedaid] FROM faid WHERE [Institution Name] = '" + school3 + "'", null);
            cursor4 = sdb.rawQuery("SELECT [avg_netprice_studentsawardedaid] FROM faid WHERE [Institution Name] = '" + school4 + "'", null);

            cursor1.moveToFirst();
            cursor2.moveToFirst();
            cursor3.moveToFirst();
            cursor4.moveToFirst();

            if (cursor1.getString(0).equals("NULL") || cursor2.getString(0).equals("NULL") || cursor1.getString(0).equals("PrivacySuppressed") || cursor2.getString(0).equals("PrivacySuppressed") || cursor1.getString(0).equals("") || cursor2.getString(0).equals("") || cursor3.getString(0).equals("NULL") || cursor3.getString(0).equals("PrivacySuppressed") || cursor3.getString(0).equals("") || cursor4.getString(0).equals("NULL") || cursor4.getString(0).equals("PrivacySuppressed") || cursor4.getString(0).equals("")) {                GraphView graph4 = (GraphView) findViewById(R.id.faidGraph);
                BarGraphSeries<DataPoint> series4 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, 0),
                        new DataPoint(3, 0),
                        new DataPoint(4, 0),
                        new DataPoint(5, 0),


                });
                graph4.addSeries(series4);
                graph4.setTitle("Couldn't compare financial aid");
                series4.setDrawValuesOnTop(true);
                series4.setValuesOnTopColor(Color.RED);

// styling
                series4.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
            } else {
                faid1 = Integer.parseInt(cursor1.getString(0));
                faid2 = Integer.parseInt(cursor2.getString(0));
                int faid3 = Integer.parseInt(cursor3.getString(0));
                int faid4 = Integer.parseInt(cursor4.getString(0));

                GraphView graph4 = (GraphView) findViewById(R.id.faidGraph);
                BarGraphSeries<DataPoint> series4 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, faid1),
                        new DataPoint(3, faid3),
                        new DataPoint(4, faid2),
                        new DataPoint(5, faid4),


                });
                graph4.addSeries(series4);
                graph4.setTitle("Average Debt After Graduating");
                series4.setDrawValuesOnTop(true);
                series4.setValuesOnTopColor(Color.RED);

// styling
                series4.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
            }


            //Graph 5
            cursor1 = sdb.rawQuery("SELECT [percent_completed_bachdegree_in6years] FROM gradrates WHERE [Institution Name] = '" + school1 + "'", null);
            cursor2 = sdb.rawQuery("SELECT [percent_completed_bachdegree_in6years] FROM gradrates WHERE [Institution Name] = '" + school2 + "'", null);
            cursor3 = sdb.rawQuery("SELECT [percent_completed_bachdegree_in6years] FROM gradrates WHERE [Institution Name] = '" + school3 + "'", null);
            cursor4 = sdb.rawQuery("SELECT [percent_completed_bachdegree_in6years] FROM gradrates WHERE [Institution Name] = '" + school4 + "'", null);


            cursor1.moveToFirst();
            cursor2.moveToFirst();
            cursor3.moveToFirst();
            cursor4.moveToFirst();
            if (cursor1.getString(0).equals("NULL") || cursor2.getString(0).equals("NULL") || cursor1.getString(0).equals("PrivacySuppressed") || cursor2.getString(0).equals("PrivacySuppressed") || cursor1.getString(0).equals("") || cursor2.getString(0).equals("") || cursor3.getString(0).equals("NULL") || cursor3.getString(0).equals("PrivacySuppressed") || cursor3.getString(0).equals("") || cursor4.getString(0).equals("NULL") || cursor4.getString(0).equals("PrivacySuppressed") || cursor4.getString(0).equals("")) {                GraphView graph5 = (GraphView) findViewById(R.id.completion6Graph);
                BarGraphSeries<DataPoint> series5 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, 0),
                        new DataPoint(3, 0),
                        new DataPoint(4, 0),
                        new DataPoint(5, 0),


                });
                graph5.addSeries(series5);
                graph5.setTitle("Couldn't compare 6 year completion");
                series5.setDrawValuesOnTop(true);
                series5.setValuesOnTopColor(Color.RED);

// styling
                series5.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
            } else {
                completion61 = Integer.parseInt(cursor1.getString(0));
                completion62 = Integer.parseInt(cursor2.getString(0));
                int completion63 = Integer.parseInt(cursor3.getString(0));
                int completion64 = Integer.parseInt(cursor4.getString(0));

                GraphView graph5 = (GraphView) findViewById(R.id.completion6Graph);
                BarGraphSeries<DataPoint> series5 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, completion61),
                        new DataPoint(3, completion63),
                        new DataPoint(4, completion62),
                        new DataPoint(5, completion64),


                });
                graph5.addSeries(series5);
                graph5.setTitle("Percent Completed in 6 years");
                series5.setDrawValuesOnTop(true);
                series5.setValuesOnTopColor(Color.RED);

// styling
                series5.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
            }


            //Graph 6
            cursor1 = sdb.rawQuery("SELECT [avg_loan_payment] FROM debt WHERE [Institution Name] = '" + school1 + "'", null);
            cursor2 = sdb.rawQuery("SELECT [avg_loan_payment] FROM debt WHERE [Institution Name] = '" + school2 + "'", null);
            cursor3 = sdb.rawQuery("SELECT [avg_loan_payment] FROM debt WHERE [Institution Name] = '" + school3 + "'", null);
            cursor4 = sdb.rawQuery("SELECT [avg_loan_payment] FROM debt WHERE [Institution Name] = '" + school4 + "'", null);

            cursor1.moveToFirst();
            cursor2.moveToFirst();
            cursor3.moveToFirst();
            cursor4.moveToFirst();
            if (cursor1.getString(0).equals("NULL") || cursor2.getString(0).equals("NULL") || cursor1.getString(0).equals("PrivacySuppressed") || cursor2.getString(0).equals("PrivacySuppressed") || cursor1.getString(0).equals("") || cursor2.getString(0).equals("") || cursor3.getString(0).equals("NULL") || cursor3.getString(0).equals("PrivacySuppressed") || cursor3.getString(0).equals("") || cursor4.getString(0).equals("NULL") || cursor4.getString(0).equals("PrivacySuppressed") || cursor4.getString(0).equals("")) {
                GraphView graph6 = (GraphView) findViewById(R.id.loanGraph);
                BarGraphSeries<DataPoint> series6 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, 0),
                        new DataPoint(3, 0),
                        new DataPoint(4, 0),
                        new DataPoint(5, 0),


                });
                graph6.addSeries(series6);
                graph6.setTitle("Couldn't compare loans");
                series6.setDrawValuesOnTop(true);
                series6.setValuesOnTopColor(Color.RED);

// styling
                series6.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
            } else {
                loan1 = Float.parseFloat(cursor1.getString(0));
                loan2 = Float.parseFloat(cursor2.getString(0));
                float loan3 = Float.parseFloat(cursor3.getString(0));
                float loan4 = Float.parseFloat(cursor4.getString(0));
                GraphView graph6 = (GraphView) findViewById(R.id.loanGraph);
                BarGraphSeries<DataPoint> series6 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, loan1),
                        new DataPoint(3, loan3),
                        new DataPoint(4, loan2),
                        new DataPoint(5, 0),


                });
                graph6.addSeries(series6);
                graph6.setTitle("Average loans");
                series6.setDrawValuesOnTop(true);
                series6.setValuesOnTopColor(Color.RED);

// styling
                series6.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
            }


            //Graph 7
            cursor1 = sdb.rawQuery("SELECT [avg_earnings_postgrad] FROM debt WHERE [Institution Name] = '" + school1 + "'", null);
            cursor2 = sdb.rawQuery("SELECT [avg_earnings_postgrad] FROM debt WHERE [Institution Name] = '" + school2 + "'", null);
            cursor3 = sdb.rawQuery("SELECT [avg_earnings_postgrad] FROM debt WHERE [Institution Name] = '" + school3 + "'", null);
            cursor4 = sdb.rawQuery("SELECT [avg_earnings_postgrad] FROM debt WHERE [Institution Name] = '" + school4 + "'", null);

            cursor1.moveToFirst();
            cursor2.moveToFirst();
            cursor3.moveToFirst();
            cursor4.moveToFirst();

            if (cursor1.getString(0).equals("NULL") || cursor2.getString(0).equals("NULL") || cursor1.getString(0).equals("PrivacySuppressed") || cursor2.getString(0).equals("PrivacySuppressed") || cursor1.getString(0).equals("") || cursor2.getString(0).equals("") || cursor3.getString(0).equals("NULL") || cursor3.getString(0).equals("PrivacySuppressed") || cursor3.getString(0).equals("") || cursor4.getString(0).equals("NULL") || cursor4.getString(0).equals("PrivacySuppressed") || cursor4.getString(0).equals("")) {
                GraphView graph7 = (GraphView) findViewById(R.id.earningsGraph);
                BarGraphSeries<DataPoint> series7 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, 0),
                        new DataPoint(3, 0),
                        new DataPoint(4, 0),
                        new DataPoint(5, 0),


                });
                graph7.addSeries(series7);
                graph7.setTitle("Couldn't compare loans");
                series7.setDrawValuesOnTop(true);
                series7.setValuesOnTopColor(Color.RED);

// styling
                series7.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
            } else {
                earnings1 = Integer.parseInt(cursor1.getString(0));
                earnings2 = Integer.parseInt(cursor2.getString(0));
                int earnings3 = Integer.parseInt(cursor3.getString(0));
                int earnings4 = Integer.parseInt(cursor4.getString(0));
                GraphView graph7 = (GraphView) findViewById(R.id.earningsGraph);
                BarGraphSeries<DataPoint> series7 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, earnings1),
                        new DataPoint(3, earnings3),
                        new DataPoint(4, earnings2),
                        new DataPoint(5, 0),


                });
                graph7.addSeries(series7);
                graph7.setTitle("Average earnings post graduation");
                series7.setDrawValuesOnTop(true);
                series7.setValuesOnTopColor(Color.RED);

// styling
                series7.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
            }


            //Graph 8
            cursor1 = sdb.rawQuery("SELECT [total_applicants], [total_admissions] FROM admissions WHERE [Institution Name] = '" + school1 + "'", null);
            cursor2 = sdb.rawQuery("SELECT [total_applicants], [total_admissions] FROM admissions WHERE [Institution Name] = '" + school2 + "'", null);
            cursor3 = sdb.rawQuery("SELECT [total_applicants], [total_admissions] FROM admissions WHERE [Institution Name] = '" + school3 + "'", null);
            cursor4 = sdb.rawQuery("SELECT [total_applicants], [total_admissions] FROM admissions WHERE [Institution Name] = '" + school4 + "'", null);

            cursor1.moveToFirst();
            cursor2.moveToFirst();
            cursor3.moveToFirst();
            cursor4.moveToFirst();
            if (cursor1.getString(0).equals("NULL") || cursor2.getString(0).equals("NULL") || cursor1.getString(0).equals("PrivacySuppressed") || cursor2.getString(0).equals("PrivacySuppressed") || cursor1.getString(0).equals("") || cursor2.getString(0).equals("") || cursor3.getString(0).equals("NULL") || cursor3.getString(0).equals("PrivacySuppressed") || cursor3.getString(0).equals("") || cursor4.getString(0).equals("NULL") || cursor4.getString(0).equals("PrivacySuppressed") || cursor4.getString(0).equals("")) {
                GraphView graph8 = (GraphView) findViewById(R.id.admittedGraph);
                BarGraphSeries<DataPoint> series8 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(0, 0),
                        new DataPoint(1, 0),
                        new DataPoint(2, 0),
                        new DataPoint(3, 0),
                        new DataPoint(4, 0),


                });
                graph8.addSeries(series8);
                graph8.setTitle("Couldn't compare admittance rates");
                series8.setDrawValuesOnTop(true);
                series8.setValuesOnTopColor(Color.RED);

// styling
                series8.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
            } else {
                admitted1 = (Double.parseDouble(cursor1.getString(1)) / Double.parseDouble(cursor1.getString(0))) * 100;
                admitted2 = (Double.parseDouble(cursor2.getString(1)) / Double.parseDouble(cursor2.getString(0))) * 100;
                double admitted3 = (Double.parseDouble(cursor3.getString(1)) / Double.parseDouble(cursor3.getString(0))) * 100;
                double admitted4 = (Double.parseDouble(cursor4.getString(1)) / Double.parseDouble(cursor4.getString(0))) * 100;

                GraphView graph8 = (GraphView) findViewById(R.id.admittedGraph);
                BarGraphSeries<DataPoint> series8 = new BarGraphSeries<>(new DataPoint[]{
                        new DataPoint(1, 0),
                        new DataPoint(2, admitted1),
                        new DataPoint(3, admitted3),
                        new DataPoint(4, admitted2),
                        new DataPoint(5, admitted4),


                });
                graph8.addSeries(series8);
                graph8.setTitle("Percent Admitted");
                series8.setDrawValuesOnTop(true);
                series8.setValuesOnTopColor(Color.RED);

// styling
                series8.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                    @Override
                    public int get(DataPoint data) {
                        return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
                    }
                });
            }

        } // end of mylist 4 check
    }
}


