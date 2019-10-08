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
import android.widget.Toast;


public class Register_page extends AppCompatActivity {

    private EditText username_id, user_fullName, user_pass_id,user_email,user_mobile_no;
    private Button submit_button;
    //private TextView login;
    private String userName,fullName, password;
    private String email;

    private String mobileNo;
    private static final String KEY_EMPTY = "";


    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //fullscreen mode
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_register_page);

        username_id = findViewById(R.id.register_username);
        user_pass_id = findViewById(R.id.register_password);
        user_fullName = findViewById(R.id.register_fullName);
        user_email = findViewById(R.id.etEmail);
        user_mobile_no = findViewById(R.id.etMobileNo);
        submit_button = findViewById(R.id.register_submit);

        dialog = new ProgressDialog(this);

        /*login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Register_page.this, MainActivity.class);
                startActivity(i);
            }
        });  */

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (view == submit_button) {
                    if(haveNetwork())
                        registerUser();
                    else if (!haveNetwork()){
                        Toast.makeText(Register_page.this,"No network",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }

    private void registerUser() {
        if (validateInputs()) {
            userName = username_id.getText().toString().trim();
            password = user_pass_id.getText().toString().trim();
            fullName = user_fullName.getText().toString().trim();
            email = user_email.getText().toString().trim();

            mobileNo = user_mobile_no.getText().toString().trim();


            dialog.setMessage("Registering the user...");
            dialog.show();

            JSONObject request = new JSONObject();
            try {
                //Populate the request parameters
                request.put("username", userName);
                request.put("password",password);
                request.put("full_name",fullName);
                request.put("email",email);
                request.put("mobileNo",mobileNo);


            } catch (JSONException e) {
                e.printStackTrace();
            }

            JsonObjectRequest stringRequest = new JsonObjectRequest
                    (Request.Method.POST, Constants.REGISTER_URL, request, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            dialog.dismiss();
                            try {
                                if (response.getInt("status") == 1) {
                                    Toast.makeText(getApplicationContext(),
                                            response.getString("message"), Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(Register_page.this,MainActivity.class);
                                    startActivity(i);

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
                                    "message", Toast.LENGTH_SHORT).show();   ///errrrrrrrr

                        }
                    });


            RequestQueue requestQueue;
            requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }

    }


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
        if (KEY_EMPTY.equals(userName)) {
            username_id.setError("Username cannot be empty");
            username_id.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(password)) {
            user_pass_id.setError("Password cannot be empty");
            user_pass_id.requestFocus();
            return false;
        }

        if (KEY_EMPTY.equals(fullName)){
            user_fullName.setError("Name cannot be empty");
            user_fullName.requestFocus();
            return false;
        }

        if (KEY_EMPTY.equals(email)) {
            user_email.setError("Email cannot be empty");
            user_email.requestFocus();
            return false;
        }
        /*if (KEY_EMPTY.equals(mobileNo)) {
            user_mobile_no.setError("mob no cannot be empty");
            user_mobile_no.requestFocus();
            return false;
        }*/

        return true;

    }
}