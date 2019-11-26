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

public class MainActivity extends AppCompatActivity {
    String LOGGED_PERSON="";
    private Button btn;
    public String usernameString,passwordString;
    String dbusername,dbpassword;
    EditText user_id,password;
    String strUrl = "http://192.168.2.18:8080/WebApplication1/teachereval/credentials/validate&";

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

                usernameString=user_id.getText().toString();
                passwordString= password.getText().toString();

                new MyTask().execute();


            }
        });

    }
    public void setID(String id){
        this.LOGGED_PERSON=id;
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
                url = new URL(strUrl+usernameString);

                HttpURLConnection client = (HttpURLConnection) url.openConnection();
                client.setRequestMethod("GET");
                client.connect();



                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

                String response = in.readLine();
                System.out.println("Result is" + response);


                JSONObject obj =new JSONObject(response);
                dbusername=""+obj.getString("username");
                dbpassword=""+obj.getString("password");

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

            if(passwordString.equals(dbpassword)&&usernameString.equals(dbusername)){
                Toast.makeText(getApplicationContext(),"Login Success",Toast.LENGTH_LONG).show();
                setID(dbusername);
                Intent i=new Intent(getApplicationContext(),DashBoard.class);
                startActivity(i);
            }
            else {
                Toast.makeText(getApplicationContext(),"Login Failed. Please try again",Toast.LENGTH_LONG).show();
            }            super.onPostExecute(result);
        }
    }
}
