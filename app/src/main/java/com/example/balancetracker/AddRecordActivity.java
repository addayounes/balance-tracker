package com.example.balancetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class AddRecordActivity extends AppCompatActivity {

    EditText amount_input, date_input, description_input;
    Button add_button;
    RadioGroup radioGroup;
    RadioButton type_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record2);


        radioGroup = findViewById(R.id.radioGroup);
        int selectedId = radioGroup.getCheckedRadioButtonId();

        type_input = findViewById(selectedId);
        amount_input = findViewById(R.id.amount_input);
        date_input = findViewById(R.id.date_input);
        description_input = findViewById(R.id.description_input);
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddRecordActivity.this);

                int type = 1;

                if(type_input.getText()=="Revenue") type = 1;
                else if(type_input.getText()=="Achat") type = 0;

                myDB.addRecord( type,
                                description_input.getText().toString().trim(),
                                Integer.valueOf(amount_input.getText().toString().trim()),
                                date_input.getText().toString().trim());
                //finish();
            }

        });
    }
}