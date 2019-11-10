package com.example.asmar.teacherassesment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
        private String uname="student1";
    private String pwd="abcd";
    private Button btn;
    EditText user_id,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user_id = (EditText)findViewById(R.id.userid);
        password = (EditText) findViewById(R.id.password);
        btn= (Button) findViewById(R.id.btnlogin);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(password.getText().toString().equals(pwd)&&user_id.getText().toString().equals(uname)){
                    Toast.makeText(getApplicationContext(),"Login Success",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(getApplicationContext(),DashBoard.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
