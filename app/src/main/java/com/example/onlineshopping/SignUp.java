package com.example.onlineshopping;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class SignUp extends AppCompatActivity {
    private TextView mdisplay;
    private DatePickerDialog.OnDateSetListener mdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        final EditText name=(EditText)findViewById(R.id.editText3);
        final EditText email=(EditText)findViewById(R.id.editText4);
        final EditText phone=(EditText)findViewById(R.id.editText5);
        final EditText gender=(EditText)findViewById(R.id.editText6);
        final EditText password=(EditText)findViewById(R.id.editText7);
        final EditText seq_qn=(EditText)findViewById(R.id.sec_qu);
        final EditText jop=(EditText)findViewById(R.id.editText8);
        final Button sz=(Button)findViewById(R.id.button9);
        final ProjectDB reg = new ProjectDB(getApplicationContext());

        mdisplay=(TextView)findViewById(R.id.textView3);
        mdisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog=new DatePickerDialog(SignUp.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,mdate,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });


        mdate=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;

                // Log.d(Tag,"onDataset:mm/dd/yyy:"+month+"/"+dayOfMonth+"/"+year);
                String date=month+"/"+dayOfMonth+"/"+year;
                mdisplay.setText(date);
            }
        };
         sz.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (reg.checkthelogin(phone.getText().toString(),password.getText().toString() ) == true) {
                     Toast.makeText(getApplicationContext(), "This EMail already exist", Toast.LENGTH_LONG).show();
                 }
                 else {
                     reg.insertcustmer(name.getText().toString(), email.getText().toString(),
                             phone.getText().toString(), gender.getText().toString(), password.getText().toString(),seq_qn.getText().toString(), mdisplay.getText().toString(), jop.getText().toString());
                     Toast.makeText(getApplicationContext(), "Customer Added", Toast.LENGTH_LONG).show();
                     Intent i = new Intent(SignUp.this, MainActivity.class);
                     startActivity(i);
                 }

             }
         });



    }

}
