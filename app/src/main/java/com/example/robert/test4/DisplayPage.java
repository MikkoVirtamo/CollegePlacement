package com.example.robert.test4;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayPage extends AppCompatActivity {

    public String str;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    TextView textView7;
    TextView textView8;
    TextView textView9;
    TextView textView10;
    //TextView textView1;
    //TextView textView1;
    //TextView textView1;

    @Override

    protected void onCreate(Bundle savedInstanceState) {


        int satm25;
        int satm75;
        int sate25;
        int sate75;
        int actc25;
        int actc75;
        int acte25;
        int acte75;
        int actm25;
        int actm75;

        double percentAid;
        double percentInstate;
        double percentOutstate;

        int avgNetPriceAid;
        int avgFaid;
        double percentGrants;

        int totalPriceInstate;
        int totalPriceOutstate;

        String location;
        String website;
        int acceptRate;

        double retentionRate;
        double studentFacultyRatio;

        int averageEarnings;
        int avgDebt;
        int repayLoans;

        double bach4;
        double bach6;

        //demographics
        double men;
        double women;
        double africanAmerican;
        double asian;
        double white;
        double hispanic;

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display_page);

        //TextView textView = (TextView) findViewById(R.id.textView);
        textView1 = (TextView) findViewById(R.id.textView12);
        textView2 = (TextView) findViewById(R.id.textView13);
        textView3 = (TextView) findViewById(R.id.textView18);
        textView4 = (TextView) findViewById(R.id.textView16);
        textView5 = (TextView) findViewById(R.id.textView26);
        textView7 = (TextView) findViewById(R.id.textView28);
        textView8 = (TextView) findViewById(R.id.textView30);
        textView9 = (TextView) findViewById(R.id.textView32);
        textView10 = (TextView) findViewById(R.id.textView33);
        Boolean hasInfo = true;

        Intent intent = getIntent();

        str = intent.getStringExtra("variable"); //Gets the string variable that was passed from MainActivity

        textView10.setText(str);

        String str2 = str;
        String newIName = str2.replace("'", "");

        DBhelper db = new DBhelper(this);
        SQLiteDatabase sdb = db.getWritableDatabase();
        Cursor cursor = sdb.rawQuery("SELECT * FROM sms WHERE [Institution Name] = '" + newIName + "'", null);
cursor.moveToNext();


        if (cursor.getCount() == 0) {
            cursor.close();
            sdb.close();
            db.close();

            Toast.makeText(DisplayPage.this, "No information available",
                    Toast.LENGTH_LONG).show();

        } else {


                // cursor.moveToNext();
    if(cursor.getString(2).equals("") || cursor.getString(2).equals("PrivacySuppressed") || cursor.getString(2).equals("NULL")) {
        satm25 = 0;
    } else {
        satm25 = Integer.parseInt(cursor.getString(2));
    }
    if(cursor.getString(3).equals("") || cursor.getString(3).equals("PrivacySuppressed") || cursor.getString(3).equals("NULL")) {
        satm75 = 0;
    } else {
        satm75 = Integer.parseInt(cursor.getString(3));
    }
    if(cursor.getString(4).equals("") || cursor.getString(4).equals("PrivacySuppressed") || cursor.getString(4).equals("NULL")) {
        sate25 = 0;
    } else {
        sate25 = Integer.parseInt(cursor.getString(4));
    }
       if(cursor.getString(5).equals("") || cursor.getString(5).equals("PrivacySuppressed") || cursor.getString(5).equals("NULL")) {
        actc25 = 0;
       } else {
           actc25 = Integer.parseInt(cursor.getString(5));
       }
    if(cursor.getString(6).equals("") || cursor.getString(6).equals("PrivacySuppressed") || cursor.getString(6).equals("NULL")) {
        actc75 = 0;
    } else {
        actc75 = Integer.parseInt(cursor.getString(6));
    }
    if(cursor.getString(7).equals("") || cursor.getString(7).equals("PrivacySuppressed") || cursor.getString(7).equals("NULL")) {
        acte25 = 0;
    } else {
        acte25 = Integer.parseInt(cursor.getString(7));
    }

    if(cursor.getString(8).equals("") || cursor.getString(8).equals("PrivacySuppressed") || cursor.getString(8).equals("NULL")) {
        acte75 = 0;
    } else {
        acte75 = Integer.parseInt(cursor.getString(8));
    }
    if(cursor.getString(9).equals("") || cursor.getString(9).equals("PrivacySuppressed") || cursor.getString(9).equals("NULL")) {
        actm25 = 0;
    } else {
        actm25 = Integer.parseInt(cursor.getString(9));
    }

    if(cursor.getString(10).equals("") || cursor.getString(10).equals("PrivacySuppressed") || cursor.getString(10).equals("NULL")) {
       actm75 = 0;
    } else {
        actm75 = Integer.parseInt(cursor.getString(10));
    }

       if(cursor.getString(15).equals("") || cursor.getString(15).equals("PrivacySuppressed") || cursor.getString(15).equals("NULL")) {
        percentAid = 0;
       } else {
           percentAid = Integer.parseInt(cursor.getString(15));
       }
    if(cursor.getString(19).equals("") || cursor.getString(19).equals("PrivacySuppressed") || cursor.getString(19).equals("NULL")) {
        percentInstate = 0;
    } else {
        percentInstate = Integer.parseInt(cursor.getString(19));
    }
    if(cursor.getString(21).equals("") || cursor.getString(21).equals("PrivacySuppressed") || cursor.getString(21).equals("NULL")) {
        percentOutstate = 0;
    } else {
        percentOutstate = Integer.parseInt(cursor.getString(21));
    }

    if(cursor.getString(37).equals("") || cursor.getString(37).equals("PrivacySuppressed") || cursor.getString(37).equals("NULL")) {
     avgFaid = 0;
    } else {
        avgFaid = Integer.parseInt(cursor.getString(37));
    }

    if(cursor.getString(22).equals("") || cursor.getString(22).equals("PrivacySuppressed") || cursor.getString(22).equals("NULL")){
        avgNetPriceAid = 0;
    } else {
        avgNetPriceAid = Integer.parseInt(cursor.getString(22));
    }

    if(cursor.getString(13).equals("") || cursor.getString(13).equals("PrivacySuppressed") || cursor.getString(13).equals("NULL")) {
        percentGrants = 0;
    } else {
        percentGrants = Double.parseDouble(cursor.getString(13));
    }

    if(cursor.getString(38).equals("") || cursor.getString(38).equals("PrivacySuppressed") || cursor.getString(38).equals("NULL")) {
        totalPriceInstate = 0;
    } else {
        totalPriceInstate = Integer.parseInt(cursor.getString(38));
    }

    if(cursor.getString(39).equals("") || cursor.getString(39).equals("PrivacySuppressed") || cursor.getString(39).equals("NULL")) {
        totalPriceOutstate = 0;
    } else {
        totalPriceOutstate = Integer.parseInt(cursor.getString(39));
    }


                textView1.setText("SAT Math 25th Percentile                  " + satm25 + "\n" + "SAT Math 75th Percentile                  " + satm75 + "\nSAT English 25th Percentile  " +
                        "            " + sate25);
                textView2.setText("ACT Composite 25th Percentile        " + actc25 + "\nACTC Composite 75th Percentile     " + actc75 + "\nACT Math 25th Percentile ACT         " + actm25 + "\nACT Math 75th Percentile                  " + actm75 + "\nACT English 25th Percentile              " + acte25 + "\nACT English 75th Percentile              " + acte75);
                textView3.setText("Average Financial Aid                         " + avgFaid + "\nNet Price After Financial Aid             " + avgNetPriceAid + "\nPercent Given Aid                                " + percentAid + "\nPercent Given Grants                          " + percentGrants, null);
                textView4.setText("Total Price In-state                              " + totalPriceInstate + "\nTotal Price Out-of-tate                         " + totalPriceOutstate + "\nPercent Paying In-State                      " + percentInstate + "\nPercent Paying Out-of-State               " + percentOutstate);

                cursor.close();
                sdb.close();
                db.close();




        DBhelper db2 = new DBhelper(this);
        SQLiteDatabase sdb2 = db.getWritableDatabase();
        Cursor cursor2 = sdb2.rawQuery("SELECT * FROM admissions WHERE [Institution Name] = '" + newIName + "'", null);
        cursor2.moveToNext();

    if(cursor2.getString(2).equals("") || cursor2.getString(2).equals("PrivacySuppressed") || cursor2.getString(2).equals("NULL")) {
        location = "Not available";
    } else {
        location = cursor2.getString(2);
    }

    String state;
    if(cursor2.getString(3).equals("") || cursor2.getString(3).equals("PrivacySuppressed") || cursor2.getString(3).equals("NULL")) {
        state = " ";
    } else {
        state = cursor2.getString(3);
    }
    String zip_code;
    if(cursor2.getString(4).equals("") || cursor2.getString(4).equals("PrivacySuppressed") || cursor2.getString(4).equals("NULL")) {
        zip_code = " ";
    } else {
        zip_code = cursor2.getString(4);
    }
    int size;
    if(cursor2.getString(11).equals("") || cursor2.getString(11).equals("PrivacySuppressed") || cursor2.getString(11).equals("NULL")) {
        size = 0;
    } else {
         size = Integer.parseInt(cursor2.getString(11));
    }
    int male;
    if(cursor2.getString(16).equals("") || cursor2.getString(16).equals("PrivacySuppressed") || cursor2.getString(16).equals("NULL")) {
        male = 0;
    }else{
        male = Integer.parseInt(cursor2.getString(16));
    }
    int female;
    if(cursor2.getString(17).equals("") || cursor2.getString(17).equals("PrivacySuppressed") || cursor2.getString(17).equals("NULL")) {
        female = 0;
    } else {
         female = Integer.parseInt(cursor2.getString(17));
    }
    double totalApplicants;
    double totalAdmissions;
    if(cursor2.getString(9).equals("") || cursor2.getString(9).equals("PrivacySuppressed") || cursor2.getString(9).equals("NULL")) {
        totalApplicants = 0;
    } else {
        totalApplicants = Double.parseDouble(cursor2.getString(9));
    }
    if(cursor2.getString(10).equals("") || cursor2.getString(10).equals("PrivacySuppressed") || cursor2.getString(10).equals("NULL")) {
        totalAdmissions = 0;
    } else {
        totalAdmissions = Double.parseDouble(cursor2.getString(10));
    }
    double admissionRate;
    if (totalApplicants == 0) {
        admissionRate = 0;
    } else {
        admissionRate = (int) (100 * (totalAdmissions / totalApplicants));
    }

    textView5.setText("Location: " + location + ", " + state + " " + String.valueOf(zip_code) + "\nIncoming Students                              " + size + "\nMale Applicants                                    " + male + "\nFemale Applicants                               " + female + "\nTotal Applicants                                   " + totalApplicants + "\nTotal Admissions                                 " + totalAdmissions + "\nAcceptance Rate                                 " + admissionRate);

    cursor2.close();
    sdb2.close();
    db2.close();


        DBhelper db3 = new DBhelper(this);
        SQLiteDatabase sdb3 = db.getWritableDatabase();
        Cursor cursor3 = sdb3.rawQuery("SELECT * FROM classsizeratio WHERE [Institution Name] = '" + newIName + "'", null);
        cursor3.moveToNext();

            int fullTimeRetentionRate;
            if(cursor3.getString(3).equals("") || cursor3.getString(3).equals("PrivacySuppressed") || cursor3.getString(3).equals("NULL")) {
                fullTimeRetentionRate = 0;
            } else {
            fullTimeRetentionRate = Integer.parseInt(cursor3.getString(3));
            }
            if(cursor3.getString(7).equals("") || cursor3.getString(7).equals("PrivacySuppressed") || cursor3.getString(7).equals("NULL")) {
                studentFacultyRatio = 0;
            } else {
                studentFacultyRatio = Integer.parseInt(cursor3.getString(7));
            }

            cursor3.close();
            sdb3.close();
            db3.close();

            textView7.setText("Retention Rate                                      " + fullTimeRetentionRate + "\nStudent-Faculty Ratio                          " + studentFacultyRatio);


        DBhelper db4 = new DBhelper(this);
        SQLiteDatabase sdb4 = db.getWritableDatabase();
        Cursor cursor4 = sdb4.rawQuery("SELECT * FROM debt WHERE [Institution Name] = '" + newIName + "'", null);
        cursor4.moveToNext();

            double avgDebtPostGrad;
            double avgLoan;



            int avgEarningPostGrad;
            if(cursor4.getString(1).equals("") || cursor4.getString(1).equals("PrivacySuppressed") || cursor4.getString(1).equals("NULL")) {
                avgEarningPostGrad = 0;
            } else {
                avgEarningPostGrad = Integer.parseInt(cursor4.getString(1));
            }

            if(cursor4.getString(3).equals("") || cursor4.getString(3).equals("PrivacySuppressed") || cursor4.getString(3).equals("NULL")) {
                avgDebtPostGrad = 0;
            } else {
                avgDebtPostGrad = Double.parseDouble(cursor4.getString(3));
            }

            if(cursor4.getString(4).equals("") || cursor4.getString(4).equals("PrivacySuppressed") || cursor4.getString(4).equals("NULL")){
                avgLoan = 0;
            } else {
                avgLoan = (int) Double.parseDouble(cursor4.getString(4));
            }

            if(cursor4.getString(5).equals("")|| cursor4.getString(5).equals("PrivacySuppressed") || cursor4.getString(5).equals("NULL")) {
                repayLoans = 0;
            } else {
                repayLoans = (int) (100 * Double.parseDouble(cursor4.getString(5)));
            }

            textView8.setText("Average Debt                                              " + avgEarningPostGrad + "\nAverage Loan Payment                           " + avgLoan + "\nPercent of Students Repaying Loans    " + repayLoans);

            cursor4.close();
            sdb4.close();
            db4.close();


        DBhelper db5 = new DBhelper(this);
        SQLiteDatabase sdb5 = db.getWritableDatabase();
        Cursor cursor5 = sdb5.rawQuery("SELECT * FROM gradrates WHERE [Institution Name] = '" + newIName + "'", null);
cursor5.moveToNext();

    if(cursor5.getString(3).equals("") || cursor5.getString(3).equals("PrivacySuppressed") || cursor5.getString(3).equals("NULL")) {
        bach4 = 0;
    } else {
        bach4 = Double.parseDouble(cursor5.getString(3));
    }

    if(cursor5.getString(5).equals("") || cursor5.getString(5).equals("PrivacySuppressed") || cursor5.getString(5).equals("NULL")) {
        bach6 = 0;
    } else {
        bach6 = Double.parseDouble(cursor5.getString(5));
    }

    textView9.setText("Percent Graduating Within 4 Years    " + bach4 + "\nPercent Graduating Within 4 Years    " + bach6);
    cursor5.close();
    sdb5.close();
    db5.close();
       }
    }









    public void backClicked(View view){

        finish();





    }



    public void addToList(View view){ //Adds school to list, shows pop up notification confirming add or saying list was full

        CompareList c = (CompareList) getApplication();

        String response = c.addToList(str);

        Toast.makeText(DisplayPage.this, response,

                Toast.LENGTH_SHORT).show();

    }



    public Boolean emptyCur(Cursor cursor) {
     if (cursor.getCount() == 0) {
         return false;
         } else {

         for(int i = 0; i < cursor.getColumnCount(); i++ ) {
             if(cursor.getString(i).equals("")) {
                 return false;
             }
             }

     }

    return true;
    }

}



