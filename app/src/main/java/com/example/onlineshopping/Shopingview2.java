package com.example.onlineshopping;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;

public class Shopingview2 extends AppCompatActivity {
    ArrayList<String> selectedItems=new ArrayList<>();
    ArrayList<String>cloth=new ArrayList<>();
    MyBringBack ourview;
    String []item;
    String []price;
    //String []cloth;
    Cursor kos;
    int zp =0;
    private  static  final  int REQUEST_CODE_SPEECH_INPUT = 1;
    EditText et2 ;
    private TextWatcher text = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopingview2);
        ourview=new MyBringBack(this);
        setContentView(ourview);
        final Button bt2 = (Button) findViewById(R.id.button4);
        final Button bt3 = (Button) findViewById(R.id.button5);
        final Button bt4 = (Button) findViewById(R.id.button7);
        final Button bt6 = (Button) findViewById(R.id.button6);
        et2 = (EditText) findViewById(R.id.editText2);
        final ListView list = (ListView) findViewById(R.id.ListProducts);
        final ArrayAdapter<String> arr = new ArrayAdapter(this,android.R.layout.simple_list_item_multiple_choice);
        final ProjectDB database = new ProjectDB(this);

        database.insert1();

        Integer ok=0;

        text = new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                if (et2.getText().toString().isEmpty()){
                    arr.clear();
                    Cursor res = database.getspesificcategory(2);
                    //  arr.clear();

                    while (!res.isAfterLast()){
                        arr.add((res.getString(1) +"      Price :" + res.getString(2)));



                        res.moveToNext();

                    }

                    list.setAdapter(arr);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        };
        et2.addTextChangedListener(text);



        if (ok==0) {
            Cursor res = database.getspesificcategory(2);
            arr.clear();
            while (!res.isAfterLast()) {
                arr.add((res.getString(1) +"      Price :" + res.getString(2)));
                res.moveToNext();
            }

            list.setAdapter(arr);
            ok = 1;

        }
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Shopingview2.this, ScanQR.class);
                startActivity(i);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = database.getprouduct(et2.getText().toString());
                if (res.getCount() == 0){
                    Toast.makeText(getApplicationContext(),
                            "Not Found ",
                            Toast.LENGTH_LONG).show();

                    return;
                }
                arr.clear();
                while (!res.isAfterLast()){
                    arr.add((res.getString(1) + "     Price :" + res.getString(2)));
                    res.moveToNext();
                }

                list.setAdapter(arr);
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Selecteditem=((TextView)view).getText().toString();

                if (selectedItems.contains(Selecteditem)){
                    zp--;
                    selectedItems.remove(Selecteditem);
                }
                else {
                    zp++;
                    selectedItems.add(Selecteditem);

                }
            }

        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String items="";
                String price="";
                String[] arr = new String[10000];
                for(int i =0 ; i<10000;++i){
                    arr[i]="";
                }
                int j=0;
                for(String item:selectedItems){

                    items+=item +"\n";
                    for (int i =0 ; i < items.length();++i){
                        if (items.charAt(i) != ' '){
                            arr[j]+= items.charAt(i);
                        }
                        else{
                            break;
                        }
                    }
                    ++j;
                    items = "";
                }
                Intent intent = new Intent(Shopingview2.this, Cart.class);
                intent.putExtra("string-array", arr);
                intent.putExtra("i",zp);
                startActivity(intent);

            }
        });
    }

    private  void speak(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say");
        try{
            startActivityForResult(intent,REQUEST_CODE_SPEECH_INPUT);
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),
                    "Wrong",
                    Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_CODE_SPEECH_INPUT:{
                if (resultCode == RESULT_OK && null != data){
                    ArrayList<String> res = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    et2.setText(res.get(0));
                }
                break;
            }
        }
    }
    }

