package com.example.asmar.teacherassesment;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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

public class EditProfile extends AppCompatActivity {
    JSONObject obj;
    TextView id,fname,lname;
    EditText addr,pho,pos,ema;
    String idd,fnam,lnam,address,postal,email,phone;
    String stringUrl="http://172.26.221.79:8080/WebApplication1/teachereval/persons/updateStudent&";
    String resultInteger;

    String valueemail,valuephone,valueaddress,valuepostal;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        idd = getIntent().getExtras().getString("id");
        fnam = getIntent().getExtras().getString("fname");
        lnam = getIntent().getExtras().getString("lname");
        address = getIntent().getExtras().getString("address");
        postal = getIntent().getExtras().getString("postal");
        email = getIntent().getExtras().getString("email");
        phone = getIntent().getExtras().getString("phone");

        btn=(Button)findViewById(R.id.editprofilebtnConfirm);
        id=(TextView)findViewById(R.id.EditprofileUserID);
        fname=(TextView)findViewById(R.id.EditprofileFname);
        lname=(TextView)findViewById(R.id.EditprofileLname);

        id.setText(idd);
        fname.setText(fnam);
        lname.setText(lnam);

        addr=(EditText)findViewById(R.id.EditprofileAddress);
        pho=(EditText)findViewById(R.id.EditprofilePhone);
        pos=(EditText)findViewById(R.id.EditprofilePostal);
        ema=(EditText)findViewById(R.id.EditprofileEmail);

        addr.setHint(address);
        pho.setHint(phone);
        pos.setHint(postal);
        ema.setHint(email);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(addr.getText().toString()) ||
                        TextUtils.isEmpty(pho.getText().toString())||
                        TextUtils.isEmpty(ema.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(),"Please fill all the fields",Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                    valueaddress=addr.getText().toString();
                    valueemail=ema.getText().toString();
                    valuephone=pho.getText().toString();
                    valuepostal=pos.getText().toString();

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
                url = new URL(stringUrl+valueemail+"&"+valuephone+"&"+valueaddress+"&"+valuepostal+"&"+idd);

                HttpURLConnection client = (HttpURLConnection) url.openConnection();
                client.setRequestMethod("GET");
                client.connect();



                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

                String response = in.readLine();
                //    System.out.println("Result is" + response);


                obj=new JSONObject(response);
                resultInteger=""+obj.getString("Status");
                System.out.println("Result is...." + url.toString());
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

            if (resultInteger.equals("ERROR")){
                Toast.makeText(getApplicationContext(),"Please try again",Toast.LENGTH_LONG).show();
            }
            else
                Toast.makeText(getApplicationContext(),"Data Updated",Toast.LENGTH_LONG).show();

            super.onPostExecute(result);

        }
    }
}
