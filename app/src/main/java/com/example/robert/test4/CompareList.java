package com.example.robert.test4;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;

public class CompareList extends Application {




    public ArrayList<String> places = new ArrayList<String>();

    private static final String TAG = "CompareList";



    public void CompareList(){}



    public String addToList(String schoolName){ //Adds school to list

        if(places.size()<4) {

            this.places.add(schoolName);

            int i = places.size();

            Log.d(TAG, "add to list called");

            String test = places.get(i - 1);

            Log.d(TAG, places.size() + " " + test);

            String response = schoolName + " added!";

            return response;

        }

        else{

            String fullResponse = "Couldn't add to list. List full";

            return fullResponse;

        }





    }



    public ArrayList getList(){

        return this.places;

    }

    /*public static synchronized CompareList getInstance(){

        if(instance == null){

            instance = new CompareList();

        }

        return instance;

    }*/

}

