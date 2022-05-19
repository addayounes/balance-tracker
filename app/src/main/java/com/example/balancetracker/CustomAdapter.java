package com.example.balancetracker;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    Context context;
    Activity activity;
    ArrayList<String> id, amount, type, description, date;

    public CustomAdapter(Activity activity, Context context, ArrayList<String> id, ArrayList<String> amount, ArrayList<String> type,
                         ArrayList<String> description, ArrayList<String> date) {
        this.activity = activity;
        this.context = context;
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.description = description;
        this.date = date;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =  inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.id_txt.setText(String.valueOf(id.get(position)));
        holder.amount_txt.setText(String.valueOf(amount.get(position)) + " DA");
        holder.type_txt.setText(String.valueOf(type.get(position)));
        holder.description_txt.setText(String.valueOf(description.get(position)));
        holder.date_txt.setText(String.valueOf(date.get(position)));

        holder.myRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);

                intent.putExtra("id", String.valueOf(id.get(position)));
                intent.putExtra("amount", String.valueOf(amount.get(position)));
                intent.putExtra("description", String.valueOf(description.get(position)));
                intent.putExtra("date", String.valueOf(date.get(position)));

                activity.startActivityForResult(intent, 1);
            }
        });


        /*
        not working for the moment

        if(String.valueOf(type.get(position)) == "Achat") {
            holder.myRow.setBackgroundColor(Color.parseColor("#EEDFE2"));
        } else if(String.valueOf(type.get(position)) == "Revenue") {
            holder.myRow.setBackgroundColor(Color.parseColor("#567845"));
        }
        */

    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id_txt, amount_txt, type_txt, description_txt, date_txt;
        LinearLayout myRow;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            id_txt = itemView.findViewById(R.id.id_txt);
            amount_txt = itemView.findViewById(R.id.date_txt);
            type_txt = itemView.findViewById(R.id.type_txt);
            description_txt = itemView.findViewById(R.id.description_txt);
            date_txt = itemView.findViewById(R.id.amount_txt);
            myRow = itemView.findViewById(R.id.my_row);
        }
    }
}
