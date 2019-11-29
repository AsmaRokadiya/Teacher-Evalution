package com.example.asmar.teacherassesment;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class Main2Activity extends AppCompatActivity {

    String username;
    TextView sub1,sub2,sub3;
    TextView name1,name2,name3;
    String resultString;
    String[] subid = new String[3];
    String[] sub = new String[3];
    String[] teacherid = new String[3];
    String[] teacherfname = new String[3];
    String[] teacherlname = new String[3];

    String strUrl = "http://192.168.2.15:8080/WebApplication1/teachereval/studentsubject/getSubject&";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        username = getIntent().getExtras().getString("username");

        sub1=(TextView)findViewById(R.id.subname1);
        sub2=(TextView)findViewById(R.id.subname2);
        sub3=(TextView)findViewById(R.id.subname3);



        name1=(TextView)findViewById(R.id.teacher1);
        name2=(TextView)findViewById(R.id.teacher2);
        name3=(TextView)findViewById(R.id.teacher3);


        CardView card_view1 = (CardView) findViewById(R.id.cardView1); // creating a CardView and assigning a value.
        CardView card_view2 = (CardView) findViewById(R.id.cardView2); // creating a CardView and assigning a value.
        CardView card_view3 = (CardView) findViewById(R.id.cardView3); // creating a CardView and assigning a value.

        new MyTask().execute();
        card_view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Main3Activity.class);
                i.putExtra("username", username);
                i.putExtra("subjectid", subid[0]);
                i.putExtra("teacherid", teacherid[0]);
                startActivity(i);
            }
        });

        card_view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Main3Activity.class);
                i.putExtra("username", username);
                i.putExtra("subjectid", subid[1]);
                i.putExtra("teacherid", teacherid[1]);
                startActivity(i);
            }
        });

        card_view3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Main3Activity.class);
                i.putExtra("username", username);
                i.putExtra("subjectid", subid[2]);
                i.putExtra("teacherid", teacherid[2]);
                startActivity(i);
            }
        });


    }



    private class MyTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params){

            URL url = null;

            try {
                url = new URL(strUrl+username);

                HttpURLConnection client = (HttpURLConnection) url.openConnection();
                client.setRequestMethod("GET");
                client.connect();



                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

                String response = in.readLine();
                System.out.println("Result is" + response);


                JSONObject obj = new JSONObject(response);
                resultString = "" + obj.getString("Status");
                JSONArray subs = obj.getJSONArray("Details of subject and teacher");
                if(resultString.equals("OK")){
                    for(int i=0;i<3;i++){
                        JSONObject details = subs.getJSONObject(i);
                        subid[i]=details.getString("subject_id");
                        sub[i]=details.getString("subject_name");
                        teacherid[i]=details.getString("teacher_id");
                        teacherfname[i]=details.getString("teacher_fname");
                        teacherlname[i]=details.getString("teacher_lname");
                    }
                }
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


            //id.setText(persons.getId());
            sub1.setText(sub[0]);
            sub2.setText(sub[1]);
            sub3.setText(sub[2]);

            name1.setText(teacherfname[0]+" "+teacherlname[0]);
            name2.setText(teacherfname[1]+" "+teacherlname[1]);
            name3.setText(teacherfname[2]+" "+teacherlname[2]);
            super.onPostExecute(result);

        }
    }



}
