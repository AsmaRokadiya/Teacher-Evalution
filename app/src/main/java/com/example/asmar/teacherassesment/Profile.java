package com.example.asmar.teacherassesment;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Profile extends AppCompatActivity {
    Persons persons;
    TextView id, fname, lname;
    TextView addr, pho, pos, ema;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btn = (Button) findViewById(R.id.profilebtnEdit);
        id = (TextView) findViewById(R.id.profileUserID);
        fname = (TextView) findViewById(R.id.profileFname);
        lname = (TextView) findViewById(R.id.profileLname);
        addr = (TextView) findViewById(R.id.profileAddress);
        pho = (TextView) findViewById(R.id.profilePhone);
        pos = (TextView) findViewById(R.id.profilePostal);
        ema = (TextView) findViewById(R.id.profileEmail);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  DetailsDatabaseAsync.execute();
                Intent i = new Intent(getApplicationContext(), EditProfile.class);

                startActivity(i);
            }
        });


    }

    private class DetailsDatabaseAsync extends AsyncTask<Void, Void, Void> {
        Persons p;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            URL url = null;

            String o1, o2, o3, o4, o5, o6, o7;
            try {

                url = new URL("http://192.168.2.15:8080/WebApplication1/teachereval/student/singleStudent&" + p.getId());

                HttpURLConnection client = null;

                client = (HttpURLConnection) url.openConnection();

                client.setRequestMethod("GET");

                int responseCode = client.getResponseCode();

                System.out.println("\n Sending 'GET' request to URL : " + url);

                System.out.println("Response Code : " + responseCode);

                InputStreamReader myInput = new InputStreamReader(client.getInputStream());

                BufferedReader in = new BufferedReader(myInput);
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                //print result
                System.out.println(response.toString());


                JSONObject obj = new JSONObject(response.toString());
                o1 = "" + obj.getInt("pid");
                o2 = "" + obj.getInt("fname");
                o3 = "" + obj.getString("lname");
                o4 = "" + obj.getString("email");
                o5 = "" + obj.getString("phone");
                o6 = "" + obj.getString("address");
                o7 = "" + obj.getString("postal");

                new Persons(o1, o2, o3, o6, o7, o4, o5);
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
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            id.setText(persons.getId());
            fname.setText(persons.getFname());
            lname.setText(persons.getLname());
            addr.setText(persons.getAddress());
            pho.setText(persons.getPhone());
            pos.setText(persons.getPostal());
            ema.setText(persons.getEmail());
        }

    }
}
