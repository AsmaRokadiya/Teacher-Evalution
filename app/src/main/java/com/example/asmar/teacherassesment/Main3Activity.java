package com.example.asmar.teacherassesment;

import android.content.Intent;
import android.media.Rating;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main3Activity extends AppCompatActivity {
RatingBar ratingBar;
EditText comment;
Button btnSubmit;
String username;
String teacher_id;
String subject_id;
String resultString;
    String ratingx;
    String strUrl = "http://192.168.2.15:8080/WebApplication1/teachereval/ratingTable/insertRating&";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        username = getIntent().getExtras().getString("username");
        teacher_id = getIntent().getExtras().getString("teacherid");
        subject_id = getIntent().getExtras().getString("subjectid");

        System.out.println(teacher_id);
        ratingBar=(RatingBar)findViewById(R.id.rating);
        comment=(EditText)findViewById(R.id.editText);
        btnSubmit=(Button)findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ratingx = ""+ratingBar.getRating();
                new MyTask().execute();

            }
        });
    }

    private class MyTask extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {

            URL url = null;

            try {
                System.out.println("the float is..." + ratingx);
                url = new URL(strUrl+username+"&"+subject_id+"&"+teacher_id+"&"+comment.getText().toString()+"&"+ratingx);

                HttpURLConnection client = (HttpURLConnection) url.openConnection();
                client.setRequestMethod("GET");
                client.connect();



                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

                String response = in.readLine();
                System.out.println("Result is........" + response);


                JSONObject obj = new JSONObject(response);
                resultString = "" + obj.getString("Status");
                System.out.println(resultString);
                // new Persons(o2, o3, o6, o7, o4, o5);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }
        @Override
        protected void onPostExecute(String result){
            if (resultString.equals("ERROR")){
                Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(getApplicationContext(), "You have successfully evaluated your teacher", Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(), DashBoard.class);
                i.putExtra("username", username);
                startActivity(i);
            }
            super.onPostExecute(result);

        }
    }
}
