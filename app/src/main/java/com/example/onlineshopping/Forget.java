package com.example.onlineshopping;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Forget extends AppCompatActivity {
Cursor z;
MyBringBack ourview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_forget);
        ourview=new MyBringBack(this);
        setContentView(ourview);
        final ProjectDB reg = new ProjectDB(getApplicationContext());
        //final TextInputEditText email=(TextInputEditText)findViewById(R.id.email_inptxt);
       final EditText seq_ans=(EditText)findViewById(R.id.ans_seq);
        final EditText email=(EditText)findViewById(R.id.email_txt);
       // final TextInputEditText seq_ans=(TextInputEditText)findViewById(R.id.ans_seq);
        final TextView pass=(TextView)findViewById(R.id.textView) ;
        Button login=(Button)findViewById(R.id.button5);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 z=reg.Forgotfethdata(email.getText().toString(),seq_ans.getText().toString());
                if(z.getCount()>0){
                    pass.setText(z.getString(5).toString());
                    pass.setText(z.getString(5));

                    Thread sender = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try
                            {


                                SendByGMail sender = new SendByGMail("nourhank285@gmail.com","123");
                                sender.sendMail("Subject",z.getString(5)
                                        ,
                                        "nourhank285@gmail.com",
                                        email.getText().toString());
                                        pass.setText(z.getString(5).toString());
                            }
                            catch (Exception e)
                            {
                                Log.e("mylog", "Error: " + e.getMessage());
                            }
                        }
                    });
                    sender.start();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Wrong email",Toast.LENGTH_LONG).show();

                }
            }
        });

    }
}
