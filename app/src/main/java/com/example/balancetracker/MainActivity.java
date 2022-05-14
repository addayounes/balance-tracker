package com.example.balancetracker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MyDatabaseHelper myDB;

    RecyclerView recyclerView;

    TextView balance, income, expense;
    FloatingActionButton addRecordButton;

    ArrayList<String> id, amount, type, description, date;

    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        balance = findViewById(R.id.balance);
        income = findViewById(R.id.income);
        expense = findViewById(R.id.expense);
        addRecordButton = findViewById(R.id.addRecordButton);

        addRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddRecordActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.recycleView);

        myDB = new MyDatabaseHelper(MainActivity.this);

        id = new ArrayList<>();
        amount = new ArrayList<>();
        type = new ArrayList<>();
        description = new ArrayList<>();
        date = new ArrayList<>();

        getAndStoreData();

        customAdapter = new CustomAdapter(MainActivity.this, this, id, amount, type, description, date);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1) {
            recreate();
        }
    }

    void getAndStoreData() {
        Cursor cursor = myDB.getAllData();

        if(cursor.getCount() == 0) {
            Toast.makeText(this, "Cliquer sur le plus pour ajouter des enregistrement", Toast.LENGTH_SHORT).show();
        } else {

            while(cursor.moveToNext()) {
                String typeLabel = "";
                int typeVal = cursor.getInt(3);

                if(typeVal==0) typeLabel = "Achat";
                else if(typeVal==1) typeLabel = "Revenue";

                id.add(cursor.getString(0));
                description.add(cursor.getString(1));
                amount.add(cursor.getString(2));
                type.add(typeLabel);
                date.add(cursor.getString(4));
            }

        }
    }
}