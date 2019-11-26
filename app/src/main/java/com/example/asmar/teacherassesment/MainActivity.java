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
    private Button btn;
    public String usernameString,passwordString;
    String dbusername,dbpassword;
    EditText user_id,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user_id = (EditText)findViewById(R.id.userid);
        usernameString=user_id.getText().toString();
        password = (EditText) findViewById(R.id.password);
        btn= (Button) findViewById(R.id.btnlogin);
       // passwordString= password.getText().toString();
        new MyTask().execute();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(password.getText().toString().equals(dbpassword)&&user_id.getText().toString().equals(dbusername)){
                    Toast.makeText(getApplicationContext(),"Login Success",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(getApplicationContext(),DashBoard.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Login Failed. Please try again",Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    private class MyTask extends AsyncTask<Void, Void, Void> {
       // String o1,o2,o3,o4;
        @Override
        protected Void doInBackground(Void... params){

            URL url = null;

            try {

                url = new URL("http://192.168.2.15:8080/WebApplication1/teachereval/credentials/validate&"+usernameString);

                HttpURLConnection client = null;

                client = (HttpURLConnection) url.openConnection();

                client.setRequestMethod("GET");

                int responseCode = client.getResponseCode();

                System.out.println("\n Sending 'GET' request to URL : " + url);

                System.out.println("Response Code : " + responseCode);

                InputStreamReader myInput= new InputStreamReader(client.getInputStream());

                BufferedReader in = new BufferedReader(myInput);
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                //print result
                System.out.println(response.toString());

                JSONObject obj =new JSONObject(response.toString());
                dbusername=""+obj.getString("username");
                dbpassword=""+obj.getString("password");



            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(Void result){
            super.onPostExecute(result);
        }
    }


}
