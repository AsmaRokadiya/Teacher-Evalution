package com.example.asmar.teacherassesment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashBoard extends AppCompatActivity {
Button yourProfile;
Button viewRating;
Button addRating;
String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        username = getIntent().getExtras().getString("username");

        yourProfile=(Button)findViewById(R.id.btnYourProfile);
        viewRating=(Button)findViewById(R.id.btnViewRating);
        addRating=(Button)findViewById(R.id.btnAddRating);
        yourProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Profile.class);
                i.putExtra("username",username);

                startActivity(i);
            }
        });

        viewRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        addRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Main2Activity.class);
                startActivity(i);
            }
        });
    }
}
