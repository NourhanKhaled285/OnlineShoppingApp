package com.example.onlineshopping;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ConfirmBuying extends AppCompatActivity {
    int total=0;
    Cursor c;
    ArrayList<String> myList;
    MyBringBack ourview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmbuy);
        ourview=new MyBringBack(this);
        setContentView(ourview);
        final Intent intent = getIntent();
        final String [] stringArray = intent.getStringArrayExtra("products");
         final int p =intent.getIntExtra("count",0);
        final String [] stringArray2 = intent.getStringArrayExtra("prices");
        myList = (ArrayList<String>) getIntent().getSerializableExtra("quantity");
        final ArrayList<String> arr_list = new ArrayList<String>();
        final ArrayList<Integer> total_list = new ArrayList<Integer>();
        ListView lis=(ListView)findViewById(R.id.list3);
        final TextView tex2=(TextView)findViewById(R.id.textView5);
        final ArrayAdapter<String> arr2 = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arr_list);
        final int total=intent.getIntExtra("total",0);
        final ProjectDB database = new ProjectDB(this);
        final int update_total=0;
        final String []prodid=new String[p];
        final String [] quantity_updates=new String[p];
        for(int i=0;i<p;i++){
            String z=database.prodid(stringArray[i]);
            prodid[i]=z;
            arr2.add((stringArray[i] +"      Price :" +stringArray2[i]+"           quantity :"+myList.get(i)));
            quantity_updates[i]=myList.get(i);
        }
        total_list.add(total);

        tex2.setText(String.valueOf(total));
        lis.setAdapter(arr2);
        lis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                final int selectedPosition = position;


                AlertDialog.Builder alert=new AlertDialog.Builder(ConfirmBuying.this);
                alert.setTitle("Update Quantity !!");
                final EditText input = new EditText(getApplicationContext());
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                input.setRawInputType(Configuration.KEYBOARD_12KEY);

                alert.setView(input);
                alert.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        int tex=Integer.parseInt(input.getText().toString());

                        if(tex<0){
                            Toast.makeText(getApplicationContext(),"quantity not suidable ",Toast.LENGTH_LONG).show();
                        }
                        else if(tex<=100){


                            final int total2 = total_list.get(total_list.size()-1)-(Integer.parseInt(myList.get(position))*Integer.parseInt(stringArray2[position]))+tex*Integer.parseInt(stringArray2[position]);
                            quantity_updates[position]=String.valueOf(tex);
                            arr2.insert(stringArray[position] +"      Price :" +stringArray2[position]+"           quantity :"+String.valueOf(tex),position);
                            arr_list.remove(position+1);
                            arr2.notifyDataSetChanged();
                            tex2.setText(String.valueOf(total2));
                            total_list.add(total2);

                            //count = selectedPosition;

                        }
                        else{
                            Toast.makeText(getApplicationContext(),"quantity doesn't fit ",Toast.LENGTH_LONG).show();
                        }
                        //Put actions for OK button here
                    }
                });
                alert.setNegativeButton("Cancel", null);
                alert.show();

            }
        });




        lis.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final int pos=position;
                new AlertDialog.Builder(ConfirmBuying.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Are you sure ?")
                        .setMessage("Do you want delete this product ?")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                final int total2 = total_list.get(total_list.size()-1)-(Integer.parseInt(quantity_updates[pos])*Integer.parseInt(stringArray2[pos]));
                                quantity_updates[pos]="0";
                                tex2.setText(String.valueOf(total2));
                                arr_list.remove(pos);

                                arr2.notifyDataSetChanged();

                            }
                        })
                        .setNegativeButton("No",null)
                        .show();

                return true;

            }
        });


        Button check=(Button)findViewById(R.id.button10);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ConfirmBuying.this, OrderDetails.class);
                i.putExtra("prodid",prodid);
                i.putExtra("quant",quantity_updates);
                i.putExtra("key",p);
                startActivity(i);
            }
        });

    }


}
