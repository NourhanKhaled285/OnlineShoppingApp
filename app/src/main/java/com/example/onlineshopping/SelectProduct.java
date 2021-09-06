package com.example.onlineshopping;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onlineshopping.R;

public class SelectProduct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectproduct);
        Button bu1=(Button)findViewById(R.id.button3);
        Button bu2=(Button)findViewById(R.id.button8);
//        Button but3=(Button) findViewById(R.id.but3_sh3);
        bu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(SelectProduct.this,ShopingView.class);
                startActivity(i);
            }
        });
        bu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(SelectProduct.this,Shopingview2.class);
                startActivity(i);
            }
        });

//        but3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i =new Intent(SelectProduct.this,Shopingview3.class);
//                startActivity(i);
//            }
//        });
    }
}
