package com.example.asmar.teacherassesment;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DisplayRating extends AppCompatActivity {
    String resultString1;
    String resultString2;
    String username;
    TextView sub1,sub2,sub3;
    TextView name1,name2,name3;
    TextView rating1,rating2,rating3;
    public  String[] subid = new String[3];
    public String[] sub = new String[3];
    public String[] teacherfname = new String[3];
    public   String[] teacherlname = new String[3];
    public   String[] rating= new String[3];

    String strUrl1 = "http://172.26.221.79:8080/WebApplication1/teachereval/studentsubject/getSubject&";
    String strUrl2 = "http://172.26.221.79:8080/WebApplication1/teachereval/ratingTable/viewData&";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_rating);

        username = getIntent().getExtras().getString("username");

        sub1=(TextView)findViewById(R.id.viewSubjectName1);
        sub2=(TextView)findViewById(R.id.viewSubjectName2);
        sub3=(TextView)findViewById(R.id.viewSubjectName3);



        name1=(TextView)findViewById(R.id.viewTeacherName1);
        name2=(TextView)findViewById(R.id.viewTeacherName2);
        name3=(TextView)findViewById(R.id.viewTeacherName3);


        rating1=(TextView)findViewById(R.id.viewRating1);
        rating2=(TextView)findViewById(R.id.viewRating2);
        rating3=(TextView)findViewById(R.id.viewRating3);

        CardView card_view4 = (CardView) findViewById(R.id.cardView4); // creating a CardView and assigning a value.
        CardView card_view5 = (CardView) findViewById(R.id.cardView5); // creating a CardView and assigning a value.
        CardView card_view6 = (CardView) findViewById(R.id.cardView6); // creating a CardView and assigning a value.
        new MyTask().execute();
        new MySecondTask().execute();

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
                url = new URL(strUrl1+username);

                HttpURLConnection client = (HttpURLConnection) url.openConnection();
                client.setRequestMethod("GET");
                client.connect();



                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

                String response = in.readLine();
                System.out.println("Result is" + response);


                JSONObject obj = new JSONObject(response);
                resultString1 = "" + obj.getString("Status");
                JSONArray subs = obj.getJSONArray("Details of subject and teacher");
                if(resultString1.equals("OK")){
                    for(int i=0;i<3;i++){
                        JSONObject details = subs.getJSONObject(i);
                        subid[i]=details.getString("subject_id");
                        sub[i]=details.getString("subject_name");
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

    private class MySecondTask extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {

            URL url = null;

            try {
                url = new URL(strUrl2+subid[0]+"&"+subid[2]);

                HttpURLConnection client = (HttpURLConnection) url.openConnection();
                client.setRequestMethod("GET");
                client.connect();



                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

                String response = in.readLine();
                System.out.println("Result is..." + response);


                JSONObject obj2 = new JSONObject(response);
                resultString2 = "" + obj2.getString("Status");
                JSONArray subs2 = obj2.getJSONArray("Details");
                if(resultString2.equals("OK")){
                    for(int i=0;i<3;i++){
                        JSONObject details = subs2.getJSONObject(i);
                        rating[i]=details.getString("rating");
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



            rating1.setText(rating[0]);
            rating2.setText(rating[1]);
            rating3.setText(rating[2]);
            super.onPostExecute(result);

        }
    }
}
