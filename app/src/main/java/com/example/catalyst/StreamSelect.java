package com.example.catalyst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class StreamSelect extends AppCompatActivity {
    private Spinner spinner;
    private Button btnSubmit;
    private EditText twelveth_marks ;
    private String sss;
    float twl_marks;
    float qualification_marks = 50;
    //public int KEY_EMPTY = Integer.parseInt("");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_streamselect);

        addItemsOnSpinner();

         addListenerOnButton();
        addListenerOnSpinnerItemSelection();

    }



    /*private boolean validateInputs() {
        if (KEY_EMPTY ==twl_marks) {
            twelveth_marks.setError("12th% cannot be empty");
            twelveth_marks.requestFocus();
            return false;
        }
        return true;
    }*/

    // add items into spinner dynamically

    public void addItemsOnSpinner() {

        spinner = findViewById(R.id.spinner);
        List<String> list = new ArrayList<String>();
        list.add("list 1");
        list.add("list 2");
        list.add("list 3");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        }

    public void addListenerOnSpinnerItemSelection() {
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    // get the selected dropdown list value
    public void addListenerOnButton() {

        spinner = findViewById(R.id.spinner);

        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                //validate12score();
                twelveth_marks = findViewById(R.id.et12marks);

                twl_marks = Float.parseFloat(twelveth_marks.getText().toString());

            //    if(validateInputs()) {

                    if (twl_marks < qualification_marks) {
                        Intent i = new Intent(StreamSelect.this, NotQualify12th.class);
                        startActivity(i);
                    } else {


                        Intent i = new Intent(StreamSelect.this, Stream_display.class);
                       // i.putExtra("stream", spinner.getSelectedItem().toString());
                        startActivity(i);

                        Toast.makeText(StreamSelect.this,
                                "Selected Stream :" + (spinner.getSelectedItem()),
                                Toast.LENGTH_SHORT).show();
                    }

            }

        });

    }
}
