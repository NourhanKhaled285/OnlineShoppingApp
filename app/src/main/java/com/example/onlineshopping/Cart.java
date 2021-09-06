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
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {
    Cursor curso;
    MyBringBack ourview;
    int total=0;
    int s;
    int q;
    int count=0;
    int count2=0;
    int p;
    ArrayList<String> quan=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ourview=new MyBringBack(this);
        setContentView(ourview);
        final Intent intent = getIntent();
        final  String [] stringArray = intent.getStringArrayExtra("string-array");
        p=intent.getIntExtra("i",0);
        final  ListView list = (ListView) findViewById(R.id.list3);
        final ArrayAdapter<String> arr = new ArrayAdapter(this,android.R.layout.simple_list_item_1);
        final ProjectDB database = new ProjectDB(this);
        final String []price=new String[p];
        for(int i=0;i<p;i++){
            String z=database.getproductprice(stringArray[i]);

            price[i]=z;
            arr.add((stringArray[i] +"      Price :" + price[i]));
        }

        list.setAdapter(arr);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                curso=database.get(stringArray[position]);
                curso.moveToPosition(position);

                 s= Integer.parseInt(curso.getString(2));
                 q= Integer.parseInt(curso.getString(3));
                //i.putExtra("empss",enumm);
                //startActivity(i);
                final int selectedPosition = position;
                AlertDialog.Builder alert=new AlertDialog.Builder(Cart.this);
                alert.setTitle("Add Quantity");
                final EditText input = new EditText(getApplicationContext());
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                input.setRawInputType(Configuration.KEYBOARD_12KEY);
                alert.setView(input);
                alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        int tex=Integer.parseInt(input.getText().toString());
                        if(tex<=0){
                            Toast.makeText(getApplicationContext(),"quantity should be at least one!! ",Toast.LENGTH_LONG).show();
                        }
                        else if(tex<=q){
                                total += s * tex;
                                quan.add(String.valueOf(tex));
                                count++;
                                //count = selectedPosition;

                        }
                        else{
                            Toast.makeText(getApplicationContext(),"We only have "+String.valueOf(q)+" from this product ",Toast.LENGTH_LONG).show();
                        }
                        //Put actions for OK button here
                    }
                });
                alert.setNegativeButton("Cancel", null);
                alert.show();



            }
        });
        Button bu=(Button)findViewById(R.id.button10);
        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count>=p) {


                    Intent i = new Intent(Cart.this, ConfirmBuying.class);
                    i.putExtra("total", total);
                    i.putExtra("products", stringArray);
                    i.putExtra("count", p);
                    i.putExtra("prices", price);
                    i.putExtra("quantity", quan);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getApplicationContext(),"you don't add the quantity ",Toast.LENGTH_LONG).show();
                }


            }
        });

    }
}
