package com.example.balancetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText amount_input_update, description_input_update, date_input_update;
    Button update_button, delete_button;

    String id, amount, description, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        amount_input_update  = findViewById(R.id.amount_input_update );
        description_input_update = findViewById(R.id.description_input_update);
        date_input_update = findViewById(R.id.date_input_update);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);


        getAndSetIntentData();

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);

                amount = amount_input_update.getText().toString().trim();
                description = description_input_update.getText().toString().trim();
                date = date_input_update.getText().toString().trim();

                myDB.updateRecord(id, Double.valueOf(amount), description, date);

            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // here we handle the deleting process
                confirmDialog();
            }
        });


    }

    void getAndSetIntentData() {
        if(getIntent().hasExtra("id")  && getIntent().hasExtra("amount") &&
                getIntent().hasExtra("description") && getIntent().hasExtra("date")) {
            // getting data
            id = getIntent().getStringExtra("id");
            amount = getIntent().getStringExtra("amount");
            description = getIntent().getStringExtra("description");
            date = getIntent().getStringExtra("date");

            // setting data
            amount_input_update.setText(amount);
            description_input_update.setText(description);
            date_input_update.setText(date);
        } else {
            Toast.makeText(this, "il y a pas de contenu!", Toast.LENGTH_SHORT).show();
        }
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Supprimer l'enregistrement");
        builder.setMessage("Êtes vous sure que vous voulez supprimer ça?");

        builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        builder.create().show();
    }
}