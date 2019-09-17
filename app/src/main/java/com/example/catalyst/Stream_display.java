package com.example.catalyst;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Stream_display extends AppCompatActivity {

    //this is the JSON Data URL
    //make sure you are using the correct ip else it will not work


    //a list to store all the products
    List<College> collegeList;

    //the recyclerview
    RecyclerView recyclerView;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stream_display);

        //getting the recycler view from xml
        recyclerView = findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the product list
        collegeList = new ArrayList<>();
//*****************
        //Intent i = getIntent();
        //str = i.getStringExtra("stream");
        //Toast.makeText(this,str,Toast.LENGTH_LONG).show();
//****************


        //this method will fetch and parse json
        //to display it in recycler view
        loadProducts();
    }

    private void loadProducts() {

        /*
          Creating a String Request
          The request type is GET defined by first parameter
          The URL is defined in the second parameter
         *Then we have a Response Listener and a Error Listener
          In response listener we will get the JSON response as a String
          */
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.URL_COLLEGE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);

                                //adding the product to product list
                                collegeList.add(new College(
                                        product.getInt("college_code"),
                                        product.getString("college_name"),
                                        product.getString("college_website"),
                                        product.getString("college_location"),
                                        product.getString("college_course"),
                                        product.getString("image")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview

                            CollegeAdapter adapter = new CollegeAdapter(Stream_display.this, collegeList);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //adding our stringrequest to queue

        Volley.newRequestQueue(this).add(stringRequest);
    }
}
