package com.example.asmar.teacherassesment;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Profile extends AppCompatActivity {

    TextView id, fname, lname;
    TextView addr, pho, pos, ema;
    Button btn;
    Button refresh;
    Button changePassword;
    String o1,o2,o3,o4,o5,o6,o7;
    String strUrl = "http://192.168.2.15:8080/WebApplication1/teachereval/student/singleStudent&";
String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        username = getIntent().getExtras().getString("username");
        changePassword=(Button)findViewById(R.id.profileChangepwd);
        refresh=(Button) findViewById(R.id.profileRefresh);
        btn = (Button) findViewById(R.id.profilebtnEdit);
        id = (TextView) findViewById(R.id.profileUserID);
        fname = (TextView) findViewById(R.id.profileFname);
        lname = (TextView) findViewById(R.id.profileLname);
        addr = (TextView) findViewById(R.id.profileAddress);
        pho = (TextView) findViewById(R.id.profilePhone);
        pos = (TextView) findViewById(R.id.profilePostal);
        ema = (TextView) findViewById(R.id.profileEmail);
        new MyTask().execute();
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MyTask().execute();
            }
        });

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),ChangePwd.class);
                i.putExtra("username",username);
                startActivity(i);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  DetailsDatabaseAsync.execute();
                Intent i = new Intent(getApplicationContext(), EditProfile.class);
                i.putExtra("id",o1);
                i.putExtra("fname",o2);
                i.putExtra("lname",o3);
                i.putExtra("email",o4);
                i.putExtra("address",o6);
                i.putExtra("postal",o7);
                i.putExtra("phone",o5);
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
                 o1 = "" + obj.getString("id");
                 o2 = "" + obj.getString("fname");
                 o3 = "" + obj.getString("lname");
                 o4 = "" + obj.getString("email");
                 o5 = "" + obj.getString("phone");
                 o6 = "" + obj.getString("address");
                 o7 = "" + obj.getString("postal");

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
            id.setText(o1);
            fname.setText(o2);
            lname.setText(o3);
            addr.setText(o6);
            pho.setText(o5);
            pos.setText(o7);
            ema.setText(o4);
            super.onPostExecute(result);

        }
    }



}
