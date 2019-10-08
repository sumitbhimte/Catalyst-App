package com.example.catalyst;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {

    private EditText aUsername, aPassword;
    private Button aSubmit;
    private static final String KEY_EMPTY = "";
    private String adminusername, adminPassword;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_admin_login);
        setViews();


        aSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                setViews();
                if (validateInputs()) {
                    displayLoader();

                   if(adminusername.equals("aa") || adminPassword.equals("aa")){

                        Intent i = new Intent(AdminLogin.this,AdminDataInsert.class);
                        startActivity(i);
                        }else{

                       Toast.makeText(getApplicationContext(), "Username or Password is incorrect", Toast.LENGTH_SHORT).show();
                   }
                }

            }
        });

    }

    public void setViews() {
        aUsername = findViewById(R.id.etAdminUsername);
        adminusername = aUsername.getText().toString().trim();

        aPassword = findViewById(R.id.etAdminPassword);
        adminPassword = aPassword.getText().toString().trim();

        aSubmit = findViewById(R.id.btnALogin);
    }

    private boolean validateInputs() {
        //checks weather the username nd password string in null
        //if null then print the toast

        if (KEY_EMPTY.equals(adminusername)) {
            aUsername.setError("Username cannot be empty");
            aUsername.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(adminPassword)) {
            aPassword.setError("Password cannot be empty");
            aPassword.requestFocus();
            return false;
        }
        return true;
    }

    private void displayLoader() {
        dialog = new ProgressDialog(AdminLogin.this);
        dialog.setMessage("Logging in.. Please wait...");
        dialog.setIndeterminate(false);
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(this,MainActivity.class));
    }

}


