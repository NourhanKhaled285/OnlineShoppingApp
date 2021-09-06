package com.example.onlineshopping;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class OrderData extends AppCompatActivity {
//    ArrayList<String> myList;
    int bba;
    String [] stringArray;
    String [] myLis;
    int it;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdata);
        final Intent intent = getIntent();
        stringArray  = intent.getStringArrayExtra("bbprod");
        final ProjectDB database = new ProjectDB(this);
        bba =intent.getIntExtra("bbord",0);
        String cas=String.valueOf(bba);
        myLis = (String[]) getIntent().getSerializableExtra("bbquant");
        it=intent.getIntExtra("bba",0);
        ListView lis=(ListView)findViewById(R.id.b7b7list);
        final ArrayAdapter<String> arr2 = new ArrayAdapter(this,android.R.layout.simple_list_item_1);
        arr2.add(("Order Number"+"       Product ID        "+"           Quantity  "));
        for(int i=0;i<it;i++){
            arr2.add((cas+"   "+"       Product         "+stringArray[i] +"                 quantity:  "+myLis[i]));
            database.insertorderdetails(stringArray[i],myLis[0], bba);

        }
        lis.setAdapter(arr2);



    }
}
