package com.example.robert.test4;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SActivity extends AppCompatActivity {

    public static final String TAG = "SActivity";

    SeekBar distanceSB;

    TextView t1;
    EditText et2;
    EditText et3;
    EditText et4;
    EditText et5;
    EditText et6;
    EditText et7;
    EditText et8;
    EditText et12;
    EditText et13;
    EditText et16;

    Button bt1;
    Button bt2;
    Spinner sp1;
    Spinner sp2;

    ListView listView;

    int inputedSATM;
    int inputedSATE;
    int inputedACTM;
    int inputedACTE;
    int inputedACTC;
    int userZipCode;
    int inputedDistance;
    int inputedMaxCost;
    int inputedIncome;
    double inputedGpa;
    double inputedPercentOfCost;


    Context context = SActivity.this;

    String[] stateNames={ " ",
            "AL",
            "AK",
            " ",
            "AZ",
            "AR",
            "CA",
            " ",
            "CO",
            "CT",
            "DE",
            "DC",
            "FL",
            "GA",
            " ",
            "HI",
            "ID",
            "IL",
            "IN",
            "IA",
            "KS",
            "KY",
            "LA",
            "ME",
            "MD",
            "MA",
            "MI",
            "MN",
            "MS",
            "MO",
            "MT",
            "NE",
            "NV",
            "NH",
            "NJ",
            "NM",
            "NY",
            "NC",
            "ND",
            "OH",
            "OK",
            "OR",
            "PA",
            " ",
            "RI",
            "SC",
            "SD",
            "TN",
            "TX",
            "UT",
            "VT",
            "VA",
            "WA",
            "WV",
            "WI",
            "WY"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s);



        t1 = (TextView) findViewById(R.id.textView1);

        et2 = (EditText) findViewById(R.id.editText2);
        et3 = (EditText) findViewById(R.id.editText3);
        et4 = (EditText) findViewById(R.id.editText4);
        et5 = (EditText) findViewById(R.id.editText5);
        et6 = (EditText) findViewById(R.id.editText6);
        //et7 = (EditText) findViewById(R.id.editText7); was distance from
        et8 = (EditText) findViewById(R.id.editText8);
        et12 = (EditText) findViewById(R.id.editText12);
        et13 = (EditText) findViewById(R.id.editText13);
        //et16 = (EditText) findViewById(R.id.editText16); was zip code

        bt1 = (Button) findViewById(R.id.button);
        bt2 = (Button) findViewById(R.id.button7);

        listView = (ListView) findViewById(R.id.smartsearch_list_view);







       sp1 = (Spinner) findViewById(R.id.spinner1);
       // sp1.setOnItemSelectedListener(this);
        sp2 = (Spinner) findViewById(R.id.spinner2);

 //       final String[] schoolArr;

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.costpercentage_array,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapter1);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.state_array,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2.setAdapter(adapter2);













bt2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        finish();
    }
});




        bt1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {




        if(!(et2.getText().toString().equals(""))) {
            inputedSATM = Integer.parseInt(et2.getText().toString());
        } else {
            inputedSATM = 0;
        }

        if(!(et3.getText().toString().equals(""))) {
            inputedSATE = Integer.parseInt(et3.getText().toString());
        } else {
            inputedSATE = 0;
        }

        if(!(et12.getText().toString().equals(""))) {
         inputedACTM = Integer.parseInt(et12.getText().toString());
        } else {

            inputedACTM = 0;
        }

        if(!(et13.getText().toString().equals(""))) {
            inputedACTE = Integer.parseInt(et13.getText().toString());
        } else {
            inputedACTE = 0;
        }


        if(!(et4.getText().toString().equals(""))) {
            inputedACTC = Integer.parseInt(et4.getText().toString());
        } else {

            inputedACTC = 0;
        }

        if(!(et5.getText().toString().equals(""))) {
            inputedMaxCost = Integer.parseInt(et5.getText().toString());
        } else {
            inputedMaxCost = 0;
        }

        if(!(et8.getText().toString().equals(""))) {
            inputedIncome = Integer.parseInt(et8.getText().toString());
        } else {
            inputedIncome = 0;
        }

        if(!(et6.getText().toString().equals(""))) {
            inputedGpa = Double.parseDouble(et6.getText().toString());
        } else {
            inputedGpa = 0;
        }


        inputedPercentOfCost = Double.parseDouble(sp1.getSelectedItem().toString());




        //SActivity s1 = new SActivity();

if(inputedSATM == 0 && inputedSATE == 0 && inputedACTC == 0 && inputedACTE == 0 && inputedACTM == 0) {
    Toast.makeText(SActivity.this, "Input test scores",
         Toast.LENGTH_LONG).show();


} else if(inputedSATM == 0 && !(inputedSATE == 0)) {
    Toast.makeText(SActivity.this, "Complete SAT scores",
            Toast.LENGTH_LONG).show();

} else if(!(inputedSATM == 0) && inputedSATE == 0) {
            Toast.makeText(SActivity.this, "Complete SAT scores",
                    Toast.LENGTH_LONG).show();

        }
else {


    List<String> schoolArr;
    schoolArr = llistToArray(toLlist());
    String[] newarr = new String[schoolArr.toArray().length];
    for (int i = 0; i < schoolArr.toArray().length; i++) {
        newarr[i] = schoolArr.get(i);

    }

    for (int i = 0; i < newarr.length; i++) {
        Log.d(TAG, "newarr value is " + newarr[i]);
    }
//sending the array to smart search listview activity to be displayed
    Intent intent = new Intent(SActivity.this, SmartSearchListActivity.class);
    intent.putExtra("var", newarr);
    startActivity(intent);

//ListAdapter myAdapter = new ArrayAdapter<String>(SActivity.this, android.R.layout.simple_list_item_1,schoolArr);


}
            }


});




    }

    public LinkedList toLlist() {

        //       double userLat = getLatitude(userZipCode);
  //     double userLong = getLongitude(userZipCode);

int bottomSATM = inputedSATM - 100;
int bottomSATE = inputedSATE - 100;
int bottomACTC = inputedACTC - 5;
int bottomACTM = inputedACTM - 5;
int bottomACTE = inputedACTE - 5;

        LinkedList L1 = new LinkedList();

        DBhelper db = new DBhelper(this);
        SQLiteDatabase sdb = db.getWritableDatabase();

        String state = sp2.getSelectedItem().toString();
        int userStateCode = getStateCode(sp2.getSelectedItem().toString(), stateNames);
        Cursor cursor;

if( !(state.equals("All"))) {
    Log.d(TAG,"entered where there is a state");
    if(inputedSATM == 0 || inputedSATE == 0) {
        if(!(inputedACTM == 0 && inputedACTE == 0)) {
            cursor = sdb.rawQuery("SELECT [Institution Name] FROM searchFilter WHERE act_english_25th <= " + inputedACTE + " AND act_math_25th <= " + inputedACTM + " AND state_code = " + userStateCode, null);
        } else if (!(inputedACTC == 0)) {
            cursor = sdb.rawQuery("SELECT [Institution Name] FROM searchFilter WHERE act_composite_25th <= " + inputedACTC + " AND state_code = " + userStateCode, null);
        } else {
            cursor = sdb.rawQuery("SELECT [Institution Name] FROM admissions", null);
        }
    } else {
        cursor = sdb.rawQuery("SELECT [Institution Name] FROM searchFilter WHERE sat_math_25th <= " + inputedSATM + " AND sat_cr_25th <= " + inputedSATE + " AND state_code = " + userStateCode,null);
    }

} else {
    Log.d(TAG, "entered where there is not state");
    if ((inputedSATM == 0 || inputedSATE == 0)) {
        if (!(inputedACTM == 0 && inputedACTE == 0)) {
            cursor = sdb.rawQuery("SELECT [Institution Name] FROM act WHERE act_english_25th <= " + inputedACTE + " AND act_math_25th <= " + inputedACTM + " AND act_math_25th >= "+ bottomACTM + " AND act_english_25th >= " + bottomACTE, null);
        } else if (!(inputedACTC == 0)) {
            cursor = sdb.rawQuery("SELECT [Institution Name] FROM act WHERE act_composite_25th <= " + inputedACTC + " AND act_composite_25th >= " + bottomACTC, null);
        } else {
            cursor = sdb.rawQuery("SELECT [Institution Name] FROM admissions", null);

        }

    } else {
        cursor = sdb.rawQuery("SELECT [Institution Name] FROM sat WHERE sat_math_25th <= " + inputedSATM + " AND sat_cr_25th <= " + inputedSATE + " AND sat_cr_25th >= " + bottomSATE + " AND sat_math_25th >= " + bottomSATM, null);
    }
}
       // Cursor cursor = sdb.rawQuery("SELECT [Institution Name] FROM admissions1 WHERE state_abbrev = " + st1, null);
if (cursor.getCount() == 0) {
    LinkedList l1 = new LinkedList();
    return l1;
}

        while(cursor.moveToNext()) {
            L1.addtoList(cursor.getString(0));
        }

        cursor.close();
        sdb.close();
        db.close();

LNode curr = L1.head;

        for(int i = 0; i < L1.numberOfElements(); i++) {
double priority = 0;
priority = priority + prioritySchool(inputedMaxCost, inputedPercentOfCost, inputedIncome, curr.getSchoolName());
curr.setValue(priority);
curr = curr.next;
        }




        return L1;
    }

    public List<String> llistToArray(LinkedList l1) {


        //String arr[] = new String[l1.numberOfElements()];
        List<String> arr = new ArrayList<>();

        if(l1.head == null) {

            return arr;
        }

        TreeClass t1 = new TreeClass();
        LNode curr = l1.head;

        //Toast.makeText(this, "got this far" + "currs head has " + curr.next.getSchoolName(),
          //      Toast.LENGTH_LONG).show();
        int counter = 0;
        for (int i = 0; i < l1.numberOfElements(); i++) {
            Log.d(TAG, "Institution " + curr.getSchoolName() + " " + curr.getValue());
        }

        while (curr.next != null){
            if(curr.getValue() <= 0) {

            } else {
                t1.insert(curr);
            }

            curr = curr.next;
        }

        t1.reverseInOrder(t1.root);
        arr = t1.getarr();

        //return arr;
        return arr;
    }

//this is a composite of the priorities in order to reduce the amount of database calls
    public double prioritySchool(double maxCost, double percentCostCovered, double userIncome, String Institution_Name) {
        double priority = 0;
        percentCostCovered = percentCostCovered / 100;
//variables for cost and faid
        double schoolCost;
        double avgFaid;
        double netprice = 0;
        double publicSchool;
        Boolean isPublic;

//varaibles for SAT
        int SATM_adjusted;
        int SATmath25th;
        int SATmath75th;
        int SATCR25th;

        int userSATM = inputedSATM;
        int userSATCR = inputedSATE;

        //variables for ACT
        int ACTC_25th;
        int ACTC_75th;
        int ACTM_25th;
        int ACTM_75th;
        int ACTE_25th;
        int ACTE_75th;

        int userACTC = inputedACTC;
        int userACTE = inputedACTE;
        int userACTM = inputedACTM;


        //variables for GPA
        double schoolGpa;
        double userGpa = inputedGpa;

        //variables for economic benefit
        double loanRepayValueAdd; // measure of how institution resulted in increases loan repayment (percentage)
        double midcareerSalValueAdded; //measure of how institution resulted in increase in midcareer salary increaase (percentage)
        double occupationSalValueAdded; // measure of how institution resulted in salary of average (percentage)


        String newIName = Institution_Name.replace("'", "");


        DBhelper db = new DBhelper(this);
        SQLiteDatabase sdb = db.getWritableDatabase();
        Cursor cursor = sdb.rawQuery("SELECT * FROM sms WHERE [Institution Name] = '" + newIName + "'", null);
        cursor.moveToNext();

        //setting SAT variables
        if (cursor.getCount() == 0) {
            SATmath25th = 0;
            SATmath75th = 0;
            SATCR25th = 0;
            cursor.close();
            sdb.close();
            db.close();
            return 0;

        }
        SATmath25th = cursor.getInt(2);
        SATmath75th = cursor.getInt(3);
        SATCR25th = cursor.getInt(4);

        //setting ACT variables
        ACTC_25th = cursor.getInt(5);
        ACTC_75th = cursor.getInt(6);
        ACTE_25th = cursor.getInt(7);
        ACTE_75th = cursor.getInt(8);
        ACTM_25th = cursor.getInt(9);
        ACTM_75th = cursor.getInt(10);

        //setting cost and faid variables
        publicSchool = cursor.getInt(22);
        avgFaid = cursor.getDouble(37);
        schoolCost = cursor.getDouble(38);





        //setting gpa variables
        if (cursor.isNull(40)) {
            schoolGpa = 0;
        } else {
            schoolGpa = cursor.getDouble(40);
        }


        if (cursor.isNull(41)) {
            midcareerSalValueAdded = 0;
        } else {
            midcareerSalValueAdded = cursor.getDouble(42);
        }

        if (cursor.isNull(42)) {
            loanRepayValueAdd = 0;
        } else {
            loanRepayValueAdd = cursor.getDouble(40);
        }

        if (cursor.isNull(43)) {
            occupationSalValueAdded = 0;
        } else {
            occupationSalValueAdded = cursor.getDouble(40);
        }


        //avgFaid = cursor.getColumnIndex("avg_total_faid");
//schoolCost = cursor.getColumnIndex("totalprice_instate_oncampus");


        if (publicSchool > 0) {
            isPublic = true;
        } else {
            isPublic = false;
        }


        if (isPublic) {
            if (userIncome > 0 && userIncome <= 30000) {
                netprice = cursor.getDouble(23);
            } else if (userIncome >= 30001 && userIncome <= 48000) {
                netprice = cursor.getDouble(24);
            } else if (userIncome >= 48001 && userIncome <= 75000) {
                netprice = cursor.getDouble(25);
            } else if (userIncome >= 750001 && userIncome <= 110000) {
                netprice = cursor.getDouble(26);
            } else if (userIncome >= 110001) {
                netprice = cursor.getDouble(27);
            }
        } else {// data in table was flipped for private so 0 to 30 comes after 30 to 38 in columns
            if (userIncome > 0 && userIncome <= 30000) {
                netprice = cursor.getDouble(30);
            } else if (userIncome >= 30001 && userIncome <= 48000) {
                netprice = cursor.getDouble(29);
            } else if (userIncome >= 48001 && userIncome <= 75000) {
                netprice = cursor.getDouble(31);
                ;
            } else if (userIncome >= 750001 && userIncome <= 110000) {
                netprice = cursor.getDouble(32);
            } else if (userIncome >= 110001) {
                netprice = cursor.getDouble(33);

            }

        }
        cursor.close();
        sdb.close();
        db.close();
//if the price is less than or equal to max acceptable
        if (maxCost == 0) {
            if(avgFaid >= percentCostCovered * schoolCost) {
                priority = priority + 50;
            } else {
                priority = priority + 0;
            }

        } else {
        if (netprice <= maxCost) {
            priority = priority + 100;
            if (avgFaid >= percentCostCovered * schoolCost) {
                priority = priority + 60;
            }

        }
    }

       if(SATmath25th == 0 ||SATmath75th == 0 || SATCR25th == 0 || ACTC_25th == 0 || ACTC_75th == 0 || ACTE_25th == 0 || ACTE_75th == 0 || ACTM_25th == 0 || ACTM_75th == 0) {
            priority = priority - 2000;
       }

        //SAT priority logic
        if (userSATM == 0 || userSATCR == 0) {
            //check if there is act score
            if((userACTM == 0 || userACTE == 0) && userACTC == 0 ) {
                //dont add priority
            } else {

                if (userACTM == 0 || userACTE == 0) {
                    if (userACTC >= 0) {
                        if (userACTC >= ACTC_75th) {
                            priority = priority + 400;

                        } else if (userACTC >= ACTC_25th) {
                            priority = priority + 80;

                        } else if(userACTC <= 0){

                            priority = priority - 1000;
                        }
                    }


                } else {

                    if (userACTM >= ACTM_75th) {
                    priority = priority + 400;
                } else if (userACTM >= ACTM_25th) {
                    priority = priority + 80;
                } else if(userACTM <= 0) {
                        priority = priority - 1000;
                    }

                if (userACTE >= ACTE_75th) {
                    priority = priority + 400;
                } else if (userACTE >= ACTE_25th) {
                    priority = priority + 80;
                } else if(userACTE <= 0) {
                        priority = priority - 1000;
                }
            }
            }

        } else {
            if (userSATM >= SATmath75th) {
                priority = priority + 400;
            } else if(userSATM >= SATmath25th) {
                priority = priority + 80;
            } else if (userSATM <= 0){
                priority = priority - 1000;
            }

            if ( userSATCR >= SATCR25th ) {
                priority = priority + 80;
            } else if (SATCR25th <= 0){
                priority = priority - 1000;
            }

        }


        if(userGpa == 0) {
        } else {
            if(userGpa >= schoolGpa){
                priority = priority + 20;
            }

        }


        //value econ added
        if(occupationSalValueAdded >= .10) {
            priority = priority + 100;
        } else if (occupationSalValueAdded >= .08) {
            priority = priority + 80;
        } else if (occupationSalValueAdded >= .07) {
            priority = priority + 70;
        } else if (occupationSalValueAdded >= .06) {
            priority = priority + 60;
        } else if (occupationSalValueAdded >= .05) {
            priority = priority + 50;
        } else if (occupationSalValueAdded >= .04) {
            priority = priority + 40;
        } else if (occupationSalValueAdded >= .03) {
            priority = priority + 30;
        } else if (occupationSalValueAdded >= .02) {
            priority = priority + 20;
        } else if (occupationSalValueAdded >= .01) {
            priority = priority + 10;
        } else if (occupationSalValueAdded <= 0) {
priority = priority - 100;
        }

        if(midcareerSalValueAdded  >= .36) {
            priority = priority + 100;
        } else if (midcareerSalValueAdded  >= .24) {
            priority = priority + 80;
        } else if (midcareerSalValueAdded  >= .12) {
            priority = priority + 70;
        } else if (midcareerSalValueAdded  >= .06) {
            priority = priority + 60;
        } else if (midcareerSalValueAdded  >= .05) {
            priority = priority + 50;
        } else if (midcareerSalValueAdded  >= .04) {
            priority = priority + 40;
        } else if (midcareerSalValueAdded  >= .03) {
            priority = priority + 30;
        } else if (midcareerSalValueAdded  >= .02) {
            priority = priority + 20;
        } else if (midcareerSalValueAdded  >= .01) {
            priority = priority + 10;
        } else if (midcareerSalValueAdded  <= 0) {
priority = priority - 100;
        }
        /*
        Data in table for loan repayment value added is not decimal format
        ie the values are 2.94 versus .0294, so the numbers in the expression are whole numbers
        */
        if (loanRepayValueAdd >= 8) {
            priority = priority + 100;
        } else if (loanRepayValueAdd >= 7) {
            priority = priority + 80;
        } else if (loanRepayValueAdd >= 6) {
            priority = priority + 60;
        } else if (loanRepayValueAdd >= 5) {
            priority = priority + 50;
        } else if (loanRepayValueAdd >= 4) {
            priority = priority + 40;
        } else if (loanRepayValueAdd >= 3) {
            priority = priority + 30;
        } else if (loanRepayValueAdd >= 2) {
            priority = priority + 20;
        } else if (loanRepayValueAdd >= 1) {
            priority = priority + 10;
        } else if (loanRepayValueAdd <= 0) {
priority = priority - 100;
        }









        return priority;
    }


public int getStateCode(String userState, String[] arr) {

        int ind = 0;
    for(int i = 0; i < arr.length; i++) {
        if(arr[i].equals(userState)) {
            ind = i;
        }
    }
return ind;
    }





    public double prioritySAT(int SATM, int SATCR, String Institution_Name) {
        double priority = 0;
        int SATM_adjusted;
        int SATmath25th;
        int SATmath75th;
        int SATCR25th;

        String newIName = Institution_Name.replace("'","");
        DBhelper db = new DBhelper(this);
        SQLiteDatabase sdb = db.getWritableDatabase();
        Cursor cursor = sdb.rawQuery("SELECT * FROM sat WHERE [Institution Name] = '" + newIName + "'", null);
        cursor.moveToNext();

        SATmath25th = cursor.getInt(2);
        SATmath75th = cursor.getInt(3);;
        SATCR25th = cursor.getInt(4);


        //SATmath25th = Integer.parseInt(cursor.getString(2));
        //SATmath75th = Integer.parseInt(cursor.getString(3));
        //SATCR25th = Integer.parseInt(cursor.getString(4));
        cursor.close();
        sdb.close();
        db.close();
        //sdb.close();

        if (SATM == 0 || SATCR == 0) {
            return 0;
        }


        if (SATM >= SATmath75th) {
            priority = priority + 100;
            //Log.d(TAG,"at this point SATM is " + SATM);
        } else if(SATM >= SATmath25th) {
            priority = priority + 80;
        } else {
            priority = priority + 0;
        }

if ( SATCR >= SATCR25th ) {
            priority = priority + 80;
    Log.d(TAG,"at this point SATCR is " + SATCR);
    Log.d(TAG,"at this point SATcr25th is " + SATCR25th);
} else {
       priority = priority + 0;
}


        return priority;

    }


    public double priorityACT(int myACTM, int myACTE, int myACTC, String Institution_Name) {
    double priority = 0;
    int ACTC_25th;
    int ACTC_75th;
    int ACTM_25th;
    int ACTM_75th;
    int ACTE_25th;
    int ACTE_75th;
        String newIName = Institution_Name.replace("'","");

    if((myACTM == 0 || myACTE == 0) && myACTC == 0 ) {
        return 0;
    }

        DBhelper db = new DBhelper(this);
        SQLiteDatabase sdb = db.getWritableDatabase();
        Cursor cursor = sdb.rawQuery("SELECT * FROM act WHERE [Institution Name] = '" + newIName + "'", null);
        cursor.moveToNext();
        ACTC_25th = cursor.getInt(2);
         ACTC_75th = cursor.getInt(3);
         ACTE_25th = cursor.getInt(4);
         ACTE_75th = cursor.getInt(5);
         ACTM_25th = cursor.getInt(6);
         ACTM_75th = cursor.getInt(7);
         cursor.close();
        sdb.close();
        db.close();




         if(myACTM == 0 || myACTE == 0) {
             if(myACTC >= 0) {
                 if(myACTC >= ACTC_75th) {
                     priority = priority + 100;
                     return priority;
                 } else if(myACTC >= ACTC_25th ) {
                     priority = priority + 80;
                     return priority;
                 }

             } else {
                 return 0;
             }

         }

         if (myACTM >= ACTM_75th ) {
             priority = priority + 100;
         } else if(myACTM >= ACTM_25th) {
             priority = priority + 80;
         }

         if(myACTE >= ACTE_75th) {
             priority = priority + 100;
         } else if(myACTE >= ACTE_25th) {
             priority = priority + 80;
         }

    return priority;
    }


    public double priorityDistance(int myZipCode, int userDistance, double userLong, double userLat, String Institution_Name) {
    double priority = 0;
    String zipCode_string;
    String newZip;
    int zipCode;
        String newIName = Institution_Name.replace("'","");


        double institutionLong;
        double institutionLat;

        if(myZipCode == 0 || userDistance == 0) {
            return 0;
        }



        DBhelper db = new DBhelper(this);
        SQLiteDatabase sdb = db.getWritableDatabase();
        Cursor cursor = sdb.rawQuery("SELECT * FROM longlat WHERE [Institution Name] = '" + newIName + "'", null);
        cursor.moveToNext();
        institutionLat = cursor.getInt(4);
        institutionLong = cursor.getInt(5);
        cursor.close();
        sdb.close();
        db.close();




        if(userDistance <= .5 * findDistToSchool(userLong, userLat, institutionLat, institutionLong)) {
            priority = priority + 50;
            } else if(userDistance >= findDistToSchool(userLong, userLat, institutionLat, institutionLong)) {
            priority = priority + 40;

        }
        return priority;
    }

    public double getLatitude(int myZipCode){
    double lat = 0;
        DBhelper db = new DBhelper(this);
        SQLiteDatabase sdb = db.getWritableDatabase();
        String adjustedZip = String.valueOf(myZipCode).substring(0,4);
        Cursor cursor = sdb.rawQuery("SELECT [longitude], [latitude] FROM longlat WHERE [zip_code] LIKE '" + adjustedZip + "%" + "'",null);

cursor.moveToNext();
        lat = cursor.getDouble(cursor.getColumnIndex("latitude"));
        //myLong = cursor.getDouble(5);
        cursor.close();
        sdb.close();
        db.close();


        return lat;
    }
    public double getLongitude(int myZipCode){
    int longit = 0;
    String adjustedZip = String.valueOf(myZipCode).substring(0,4);
        DBhelper db = new DBhelper(this);
        SQLiteDatabase sdb = db.getWritableDatabase();
        Cursor cursor = sdb.rawQuery("SELECT [longitude], [latitude] FROM longlat WHERE [zip_code] LIKE '" + adjustedZip + "%" + "'",null);
cursor.moveToNext();
        longit = cursor.getInt(cursor.getColumnIndex("longitude"));

        cursor.close();
        sdb.close();
        db.close();

        return longit;
    }


    public double priorityCost(double maxCost, double percentCostCovered, double userIncome, String Institution_Name) {
        double priority = 0;
percentCostCovered = percentCostCovered / 100;

double schoolCost;
double avgFaid;
double netprice = 0;

double publicSchool;
Boolean isPublic;

        String newIName = Institution_Name.replace("'","");
//double privateSchool;
//double percentAwarded;


        if(maxCost == 0) {
        return 0;
        }

DBhelper db = new DBhelper(this);
SQLiteDatabase sdb = db.getWritableDatabase();
Cursor cursor = sdb.rawQuery("SELECT * FROM searchFilter WHERE [Institution Name] = '" + newIName + "'",null);
cursor.moveToNext();
publicSchool = cursor.getInt(22);

avgFaid = cursor.getDouble(37);
schoolCost = cursor.getDouble(38);
//avgFaid = cursor.getColumnIndex("avg_total_faid");
//schoolCost = cursor.getColumnIndex("totalprice_instate_oncampus");


if (publicSchool > 0) {
    isPublic = true;
} else {
    isPublic = false;
}


if(isPublic) {
    if(userIncome > 0 && userIncome <= 30000) {
        netprice = cursor.getDouble(23);
        } else if(userIncome >= 30001 && userIncome <= 48000) {
        netprice = cursor.getDouble(24);
    } else if(userIncome >= 48001 && userIncome <= 75000) {
        netprice = cursor.getDouble(25);
        } else if(userIncome >= 750001 && userIncome <= 110000) {
      netprice = cursor.getDouble(26);
    } else if(userIncome >= 110001) {
        netprice = cursor.getDouble(27);
    }
} else {// data in table was flipped for private so 0 to 30 comes after 30 to 38 in columns
    if(userIncome > 0 && userIncome <= 30000) {
        netprice = cursor.getDouble(30);
    } else if(userIncome >= 30001 && userIncome <= 48000) {
        netprice = cursor.getDouble(29);
    } else if(userIncome >= 48001 && userIncome <= 75000) {
        netprice = cursor.getDouble(31);;
    } else if(userIncome >= 750001 && userIncome <= 110000) {
        netprice = cursor.getDouble(32);
    } else if(userIncome >= 110001) {
        netprice = cursor.getDouble(33);

    }

}
cursor.close();
sdb.close();
db.close();
//if the price is less than or equal to max acceptable
if(netprice <= maxCost) {
    priority  = priority + 20;
if(avgFaid >= percentCostCovered * schoolCost) {
    priority = priority + 10;
}

} else if(avgFaid >= percentCostCovered * schoolCost) {
    priority = priority + 10;
} else {
    priority = priority + 0;
}


//faidSchool = cursor.getDouble(28);
//percentAwarded = cursor.getDouble(6);
//if (faidschool >= pCC * cost) {}


    return priority;
    }


    public double priorityGpa(double userGpa, String Institution_Name) {
        double priority = 0;
        double schoolGpa;
        String newIName = Institution_Name.replace("'","");

        if(userGpa == 0) {
    return 0;
}

        DBhelper db = new DBhelper(this);
        SQLiteDatabase sdb = db.getWritableDatabase();
        Cursor cursor = sdb.rawQuery("SELECT * FROM gpa WHERE [Institution Name] = '" + newIName + "'",null);
cursor.moveToNext();

        schoolGpa = cursor.getDouble(2);

        cursor.close();
        sdb.close();
        db.close();


        if(userGpa >= schoolGpa){
            priority = priority + 20;
        }

    return priority;
    }


    public double priorityEconBenefit(String Institution_Name) {
        double priority = 0;
        double loanRepayValueAdd; // measure of how institution resulted in increases loan repayment (percentage)
        double midcareerSalValueAdded; //measure of how institution resulted in increase in midcareer salary increaase (percentage)
        double occupationSalValueAdded; // measure of how institution resulted in salary of average (percentage)
        String newIName = Institution_Name.replace("'","");

        DBhelper db = new DBhelper(this);
        SQLiteDatabase sdb = db.getWritableDatabase();
        Cursor cursor = sdb.rawQuery("SELECT * FROM valueadd WHERE [Institution Name] = '" + newIName + "'",null);
        cursor.moveToNext();
//      midcareerSalValueAdded = Double.parseDouble(cursor.getString(2));
        if (cursor.getCount() == 0) {
            return 0;
        }

        midcareerSalValueAdded = cursor.getDouble(2);
        loanRepayValueAdd = cursor.getDouble(3);
        occupationSalValueAdded = cursor.getDouble(4);

        cursor.close();
        sdb.close();
        db.close();

        if(occupationSalValueAdded >= .10) {
            priority = priority + 20;
        } else if (occupationSalValueAdded >= .08) {
        priority = priority + 15;
        } else if (occupationSalValueAdded >= .07) {
            priority = priority + 10;
        } else if (occupationSalValueAdded >= .06) {
            priority = priority + 6;
        } else if (occupationSalValueAdded >= .05) {
            priority = priority + 5;
        } else if (occupationSalValueAdded >= .04) {
            priority = priority + 4;
        } else if (occupationSalValueAdded >= .03) {
            priority = priority + 3;
        } else if (occupationSalValueAdded >= .02) {
            priority = priority + 2;
        } else if (occupationSalValueAdded >= .01) {
            priority = priority + 1;
        } else if (occupationSalValueAdded <= 0) {

        }

        if(midcareerSalValueAdded  >= .36) {
            priority = priority + 20;
        } else if (midcareerSalValueAdded  >= .24) {
            priority = priority + 15;
        } else if (midcareerSalValueAdded  >= .12) {
            priority = priority + 10;
        } else if (midcareerSalValueAdded  >= .06) {
            priority = priority + 6;
        } else if (midcareerSalValueAdded  >= .05) {
            priority = priority + 5;
        } else if (midcareerSalValueAdded  >= .04) {
            priority = priority + 4;
        } else if (midcareerSalValueAdded  >= .03) {
            priority = priority + 3;
        } else if (midcareerSalValueAdded  >= .02) {
            priority = priority + 2;
        } else if (midcareerSalValueAdded  >= .01) {
            priority = priority + 1;
        } else if (midcareerSalValueAdded  <= 0) {

        }
        /*
        Data in table for loan repayment value added is not decimal format
        ie the values are 2.94 versus .0294, so the numbers in the expression are whole numbers
        */
        if (loanRepayValueAdd >= 8) {
            priority = priority + 15;
        } else if (loanRepayValueAdd >= 7) {
            priority = priority + 10;
        } else if (loanRepayValueAdd >= 6) {
            priority = priority + 6;
        } else if (loanRepayValueAdd >= 5) {
            priority = priority + 5;
        } else if (loanRepayValueAdd >= 4) {
            priority = priority + 4;
        } else if (loanRepayValueAdd >= 3) {
            priority = priority + 3;
        } else if (loanRepayValueAdd >= 2) {
            priority = priority + 2;
        } else if (loanRepayValueAdd >= 1) {
            priority = priority + 1;
        } else if (loanRepayValueAdd <= 0) {

        }




        return priority;
    }





//this is how we find distance to each school to compare to the user distance
    public double findDistToSchool(double longi1, double lat1, double longi2, double lat2) {
        //Haversine formula that calculates distance between two sets of longitudes and latitudes
        // in the US, error was at most 18% at long distances

        double longi1_rads = toRad(longi1);
        double longi2_rads = toRad(longi2);
        double lat1_rads = toRad(lat1);
        double lat2_rads = toRad(lat2);

        double distance;
        double radius = 3959;
        //radius of Earth can differ other values plausible, eg 3949
        double composite;

        composite = Math.sqrt(haver(lat2_rads - lat1_rads) + (Math.cos(lat1_rads) * Math.cos(lat2_rads)) * haver(longi2_rads - longi1_rads));
        distance = 2 * radius * Math.asin(composite);

    return distance;
    }

//modularized it to makes it a bit cleaner
    public double haver(double value) {
        double result;
        result = Math.sin((value) / 2) * Math.sin((value) / 2);
        return result;
    }

    public double toRad(double value) {
        value = value * (Math.PI/180);
        return value;

    }

}

