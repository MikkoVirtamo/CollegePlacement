package com.example.robert.test4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class FiltersForSearch extends AppCompatActivity {
    String[] stateCodes = {"0",
            "1",
            "2",
            "4",
            "5",
            "6",
            "8",
            "9",
            "10",
            "11",
            "12",
            "13",
            "15",
            "16",
            "17",
            "18",
            "19",
            "20",
            "21",
            "22",
            "23",
            "24",
            "25",
            "26",
            "27",
            "28",
            "29",
            "30",
            "31",
            "32",
            "33",
            "34",
            "35",
            "36",
            "37",
            "38",
            "39",
            "40",
            "41",
            "42",
            "44",
            "45",
            "46",
            "47",
            "48",
            "49",
            "50",
            "51",
            "53",
            "54",
            "55",
            "56",
    };

    String[] instate = {"In-state or out of state?",
            "In-state",
            "Out of state"
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters_for_search);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
        String[] orderBy = {"Order By",
                "Highest Financial Aid",
                "Lowest Financial Aid",
                "Highest SAT Scores",
                "Lowest SAT Scores",
                "Highest Cost",
                "Lowest Cost"};

        String[] stateNames={"Select State",
                "Alabama",
                "Alaska",
                "Arizona",
                "Arkansas",
                "California",
                "Colorado",
                "Connecticut",
                "Delaware",
                "District of Columbia",
                "Florida",
                "Georgia",
                "Hawaii",
                "Idaho",
                "Illinois",
                "Indiana",
                "Iowa",
                "Kansas",
                "Kentucky",
                "Louisiana",
                "Maine",
                "Maryland",
                "Massachusetts",
                "Michigan",
                "Minnesota",
                "Mississippi",
                "Missouri",
                "Montana",
                "Nebraska",
                "Nevada",
                "New Hampshire",
                "New Jersey",
                "New Mexico",
                "New York",
                "North Carolina",
                "North Dakota",
                "Ohio",
                "Oklahoma",
                "Oregon",
                "Pennsylvania",
                "Rhode Island",
                "South Carolina",
                "South Dakota",
                "Tennessee",
                "Texas",
                "Utah",
                "Vermont",
                "Virginia",
                "Washington",
                "West Virginia",
                "Wisconsin",
                "Wyoming"};
        //spinner.setOnItemSelectedListener(this);

//Creating the ArrayAdapter instance having the bank name list
        ArrayAdapter stateAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,stateNames);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Setting the ArrayAdapter data on the Spinner
        spinner.setAdapter(stateAdapter);

        ArrayAdapter orderAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,orderBy);
        orderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Setting the ArrayAdapter data on the Spinner
        spinner2.setAdapter(orderAdapter);

        ArrayAdapter inAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,instate);
        orderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(inAdapter);
    }


    public void chosenFilters(View view){
        String orderByText = "";
        String search = "";
        String countSearch = "";
        EditText schoolName = (EditText) findViewById(R.id.schoolName);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
        int arrayPosition = spinner.getSelectedItemPosition();
        String stateCode = stateCodes[arrayPosition];
        TextView myText = (TextView) findViewById(R.id.myText);
        String orderBy = spinner2.getSelectedItem().toString();

        // String orderBy = spinner2.getSelectedItem().toString();

        if(spinner3.getSelectedItem().toString().equals("In-state or out of state?") && (orderBy.equals("Highest Cost")||orderBy.equals("Lowest Cost"))){
            Toast.makeText(FiltersForSearch.this, "You must select whether you are in state or out of state!",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            if (!orderBy.equals("Order By")) {
                if (orderBy.equals("Highest Financial Aid")) {
                    orderByText = " AND [avg_total_faid] LIKE '%_%' ORDER BY [avg_total_faid] DESC";
                } else if (orderBy.equals("Lowest Financial Aid")) {
                    orderByText = " AND [avg_total_faid] LIKE '%_%' ORDER BY [avg_total_faid] ASC";
                } else if (orderBy.equals("Highest SAT Scores")) {
                    orderByText = " AND [sat_math_25th] LIKE '%_%' ORDER BY [sat_math_25th] DESC";
                } else if (orderBy.equals("Lowest SAT Scores")) {
                    orderByText = " AND [sat_math_25th] LIKE '%_%' ORDER BY [sat_math_25th] ASC";
                } else if (orderBy.equals("Highest Cost") || orderBy.equals("Lowest Cost")) {
                    if (spinner3.getSelectedItem().toString().equals("In-state or out of state?")) {
                        Toast.makeText(FiltersForSearch.this, "You must select whether you are in state or out of state!",
                                Toast.LENGTH_SHORT).show();
                    } else if (spinner3.getSelectedItem().toString().equals("In-state")) {
                        if (orderBy.equals("Highest Cost")) {
                            orderByText = " AND [totalprice_instate_oncampus] LIKE '%_%' ORDER BY [totalprice_instate_oncampus] DESC";
                        } else if (orderBy.equals("Lowest Cost")) {
                            orderByText = " AND [totalprice_instate_oncampus] LIKE '%_%' ORDER BY [totalprice_instate_oncampus] ASC";
                        }
                    } else if (spinner3.getSelectedItem().toString().equals("Out of state")) {
                        if (orderBy.equals("Highest Cost")) {
                            orderByText = " AND [totalprice_outstate_oncampus] LIKE '%_%' ORDER BY [totalprice_outstate_oncampus] DESC";
                        } else if (orderBy.equals("Lowest Cost")) {
                            orderByText = " AND [totalprice_outstate_oncampus] LIKE '%_%' ORDER BY [totalprice_outstate_oncampus] ASC";
                        }
                    }

                }

            }
            if (!schoolName.getText().toString().equals("Search by name")) {
                search = "SELECT * FROM [searchFilter] WHERE [Institution Name] LIKE '%" + schoolName.getText().toString() + "%' ";
                countSearch = "SELECT count(*) FROM [searchFilter] WHERE [Institution Name] LIKE '%" + schoolName.getText().toString() + "%' ";
            } else if (schoolName.getText().toString().equals("Search by name")) {
                search = "SELECT * FROM [searchFilter] WHERE [Institution Name] LIKE '%%' ";
                countSearch = "SELECT count(*) FROM [searchFilter] WHERE [Institution Name] LIKE '%%' ";
            }
            if (!stateCode.equals("0")) {
                search = search + " AND [state_code] = " + stateCode;
                countSearch = countSearch + " AND [state_code] = " + stateCode;
            }
            search = search + orderByText;
            countSearch = countSearch + orderByText;


        }
        myText.setText(search);

        Intent intent = new Intent(this, FilterSearchResults.class);
        Bundle extras = new Bundle();
        extras.putString("search",search);
        extras.putString("countSearch",countSearch);
        intent.putExtras(extras);
        startActivity(intent);
    }




}
