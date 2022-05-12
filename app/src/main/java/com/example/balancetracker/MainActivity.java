package com.example.balancetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView balance, income, expense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        balance = (TextView) findViewById(R.id.balance);
        income = (TextView) findViewById(R.id.income);
        expense = (TextView) findViewById(R.id.expense);
    }
}