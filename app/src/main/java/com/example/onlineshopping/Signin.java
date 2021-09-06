package com.example.onlineshopping;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Signin extends AppCompatActivity {
    Cursor cursor;
    MyBringBack ourview;
    EditText editTextUsername;
    EditText editTextPassword;
    private String username,password;
    CheckBox saveLoginCheckBox;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;
    //private SharedPreferences mprefs;
    //private static final  String prefme="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        ourview=new MyBringBack(this);
        setContentView(ourview);
        editTextUsername =(EditText)findViewById(R.id.editText);
        editTextPassword =(EditText)findViewById(R.id.editText2);
        Button login=(Button)findViewById(R.id.button2);
        //Button signup=(Button)findViewById(R.id.button3);
        Button forget=(Button)findViewById(R.id.button);
        saveLoginCheckBox=(CheckBox)findViewById(R.id.checkBox);
        final ProjectDB reg = new ProjectDB(getApplicationContext());
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            editTextUsername.setText(loginPreferences.getString("username", ""));
            editTextPassword.setText(loginPreferences.getString("password", ""));
            saveLoginCheckBox.setChecked(true);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editTextUsername.getWindowToken(), 0);

                String pass = editTextPassword.getText().toString();
                String phon = editTextUsername.getText().toString();
                if (!(pass.equals("") && phon.equals(""))) {


                    if (reg.checkthelogin(phon, pass) == true) {
                        if (saveLoginCheckBox.isChecked()) {

                            loginPrefsEditor.putBoolean("saveLogin", true);
                            loginPrefsEditor.putString("username", phon);
                            loginPrefsEditor.putString("password", pass);
                            loginPrefsEditor.commit();
                        } else {
                            loginPrefsEditor.clear();
                            loginPrefsEditor.commit();
                        }
                        Intent i = new Intent(Signin.this, SelectProduct.class);
                        editTextUsername.getText().clear();
                        editTextPassword.getText().clear();
                        startActivity(i);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Wrong phone OR Password", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "phone or password is empty", Toast.LENGTH_LONG).show();
                }


            }
        });
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Signin.this, Forget.class);
                startActivity(i);
            }
        });


    }
}
