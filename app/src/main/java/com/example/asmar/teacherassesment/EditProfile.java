package com.example.asmar.teacherassesment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditProfile extends AppCompatActivity {

Persons persons;
TextView id,fname,lname;
EditText addr,pho,pos,ema;
Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        btn=(Button)findViewById(R.id.editprofilebtnConfirm);
        id=(TextView)findViewById(R.id.EditprofileUserID);
        fname=(TextView)findViewById(R.id.EditprofileFname);
        lname=(TextView)findViewById(R.id.EditprofileLname);

        id.setText(persons.getId());
        fname.setText(persons.getFname());
        lname.setText(persons.getLname());

        addr=(EditText)findViewById(R.id.EditprofileAddress);
        pho=(EditText)findViewById(R.id.EditprofilePhone);
        pos=(EditText)findViewById(R.id.EditprofilePostal);
        ema=(EditText)findViewById(R.id.EditprofileEmail);

        addr.setHint(persons.getAddress());
        pho.setHint(persons.getPhone());
        ema.setHint(persons.getEmail());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(addr.getText().toString()) &&
                        TextUtils.isEmpty(pho.getText().toString())&&
                        TextUtils.isEmpty(ema.getText().toString()))
                        {
                    //Toast
                    return;
                }
                else {
                    //code for webservice call
                }
            }
        });
    }
}
