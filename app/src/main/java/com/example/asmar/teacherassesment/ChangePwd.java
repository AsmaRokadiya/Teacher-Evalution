package com.example.asmar.teacherassesment;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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
import java.nio.file.attribute.BasicFileAttributeView;

public class ChangePwd extends AppCompatActivity {
String username;
    String strUrl = "http://172.26.221.79:8080/WebApplication1/teachereval/credentials/updatePassword&";
EditText newPassword;
String newPwd;
JSONObject object;
String resultString;
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd);

        username = getIntent().getExtras().getString("username");
        newPassword=(EditText)findViewById(R.id.editText2);
        button=(Button)findViewById(R.id.changePWDBUTTON);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(newPassword.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(),"Please fill all the field",Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                    newPwd=newPassword.getText().toString();
                    new MyTask().execute();
                }
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
                url = new URL(strUrl+username+"&"+newPwd);

                HttpURLConnection client = (HttpURLConnection) url.openConnection();
                client.setRequestMethod("GET");
                client.connect();



                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

                String response = in.readLine();
                System.out.println("Change Password result is " + response);


                object=new JSONObject(response);
                resultString=""+object.getString("Status");

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
                Toast.makeText(getApplicationContext(),"Please try again",Toast.LENGTH_LONG).show();
            }
            else
                Toast.makeText(getApplicationContext(),"Password Updated",Toast.LENGTH_LONG).show();
            super.onPostExecute(result);

        }
    }

}
