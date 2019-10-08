package com.example.catalyst;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import android.widget.Spinner;
import android.widget.Toast;



public class AdminDataInsert extends AppCompatActivity {

    private EditText college_code,college_name,college_website,college_location,college_cet_cutoff;
    private Spinner spinStream,spinCategory;
    private Button submit_btn;

    private String name,website,location,stream,category,code,cet_cutoff;


    private static final String KEY_EMPTY = "";
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_admin_data_insert);

        college_code = findViewById(R.id.etCollegeCode);
        college_name = findViewById(R.id.etCollegeName);
        college_website = findViewById(R.id.etCollegeWebsite);
        college_location = findViewById(R.id.etCollegeLocation);
        college_cet_cutoff = findViewById(R.id.etCetScore);
        spinStream = findViewById(R.id.spinnerStream);
        spinCategory = findViewById(R.id.spinnerCategory);
        submit_btn = findViewById(R.id.btnSubmit);


        dialog = new ProgressDialog(this);

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (view == submit_btn) {
                    if(haveNetwork())
                        registerUser();
                    else if (!haveNetwork()){
                        Toast.makeText(AdminDataInsert.this,"No network",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }

    private void registerUser() {
        if (validateInputs()) {

           // if((college_code.getText().toString().trim()).equals("") )
            code = college_code.getText().toString().trim();
            name = college_name.getText().toString().trim();
            website = college_website.getText().toString().trim();
            location = college_location.getText().toString().trim();
          //  if((college_cet_cutoff.getText().toString().trim()).equals(""))
            cet_cutoff =college_cet_cutoff.getText().toString().trim();
            stream = spinStream.getSelectedItem().toString().trim();
            category = spinCategory.getSelectedItem().toString().trim();


            dialog.setMessage("Submitting the Data...");
            dialog.show();

            JSONObject request = new JSONObject();
            try {
                //Populate the request parameters
                request.put("code", code);
                request.put("name",name);
                request.put("website",website);
                request.put("location",location);
                request.put("cet_cutoff",cet_cutoff);
                request.put("stream",stream);
                request.put("category",category);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            JsonObjectRequest stringRequest = new JsonObjectRequest
                    (Request.Method.POST, Constants.URL_COLLEGE_ADMIN, request, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            dialog.dismiss();
                            try {
                                if (response.getInt("status") == 1) {
                                   // toast err
                                    Toast.makeText(getApplicationContext(),response.getString("message"), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(AdminDataInsert.this,MainActivity.class);
                                    startActivity(intent);

                                } else {
                                    Toast.makeText(getApplicationContext(),
                                            response.getString("message"), Toast.LENGTH_SHORT).show();

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            dialog.dismiss();

                            //Display error message whenever an error occurs
                            Toast.makeText(getApplicationContext(),
                                    "connection error", Toast.LENGTH_SHORT).show();   ///errrrrrrrr

                        }
                    });

            RequestQueue requestQueue;
            requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
     }

    /*public void gotoLogin(View view) {
        Intent intent = new Intent(Register_page.this, MainActivity.class);
        startActivity(intent);
    }*/


    public boolean haveNetwork() {

        boolean have_wifi = false;
        boolean have_mobiledata = false;
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos = cm.getAllNetworkInfo();

        for (NetworkInfo info : networkInfos) {
            if (info.getTypeName().equalsIgnoreCase("WIFI"))
                if (info.isConnected())
                    have_wifi = true;
            if (info.getTypeName().equalsIgnoreCase("MOBILE"))
                if (info.isConnected())
                    have_mobiledata = true;

        }
        return have_mobiledata | have_wifi;
    }

    private boolean validateInputs() {
        if (KEY_EMPTY.equals(code)) {
            college_code.setError("code cannot be empty");
            college_code.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(name)) {
            college_name.setError("Name cannot be empty");
            college_name.requestFocus();
            return false;
        }

        if (KEY_EMPTY.equals(website)){
            college_website.setError("Website cannot be empty");
            college_website.requestFocus();
            return false;
        }

        if (KEY_EMPTY.equals(stream)) {
            college_location.setError("Email cannot be empty");
            college_location.requestFocus();
            return false;
        }

        if (KEY_EMPTY.equals(location)) {
            college_location.setError("Location cannot be empty");
            college_location.requestFocus();
            return false;
        }if (KEY_EMPTY.equals(cet_cutoff)) {
            college_location.setError("Cet-cutoff cannot be empty");
            college_location.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(category)) {
            college_location.setError("Category cannot be empty");
            college_location.requestFocus();
            return false;
        }

         return true;

    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(this,AdminLogin.class));
    }

}
