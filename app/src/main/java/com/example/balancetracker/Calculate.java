package com.example.balancetracker;

import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class Calculate {
    ArrayList<String> amounts, type;

    public Calculate(ArrayList<String> amounts, ArrayList<String> type) {
        this.amounts = amounts;
        this.type = type;
    }

    double calculateBalance() {
        double balance = 0;

        for (int i = 0; i<amounts.size(); i++) {
            if(type.get(i).toString().equals("Revenue")) balance += Double.parseDouble(amounts.get(i));
            else if(type.get(i).toString().equals("Achat")) balance -= Double.parseDouble(amounts.get(i));
        }

        return balance;
    }

    double calculateExpense() {
        double expense = 0;

        for (int i = 0; i<amounts.size(); i++) {
            if(type.get(i).toString().equals("Achat"))
                expense += Double.parseDouble(amounts.get(i));
        }

        return expense;
    }

    double calculateIncome() {
        double expense = 0;

        for (int i = 0; i<amounts.size(); i++) {
            if(type.get(i).toString().equals("Revenue")) expense += Double.parseDouble(amounts.get(i));
        }

        return expense;
    }


}
