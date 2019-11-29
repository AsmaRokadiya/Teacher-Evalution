package com.example.asmar.teacherassesment;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DashBoard extends AppCompatActivity {
    Button yourProfile;
    Button viewRating;
    Button addRating;
    String username;
    Button lgout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);




        username = getIntent().getExtras().getString("username");
        lgout=(Button)findViewById(R.id.btnlogout);
        yourProfile = (Button) findViewById(R.id.btnYourProfile);
        viewRating = (Button) findViewById(R.id.btnViewRating);
        addRating = (Button) findViewById(R.id.btnAddRating);
        yourProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Profile.class);
                i.putExtra("username", username);

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
                Intent i = new Intent(getApplicationContext(), Main2Activity.class);
                i.putExtra("username", username);
                startActivity(i);
            }
        });

        lgout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
    @Override
    public void onBackPressed() {
        //Don nothing
    }
}
