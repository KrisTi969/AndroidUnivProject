package com.example.crys_.makeandroidgreatagain;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;



public class MainActivity extends AppCompatActivity {

    Button view_all_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

/////
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view_all_button = (Button) findViewById(R.id.button5);
        view_all_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ViewUsers.class));
            }
        });

    }
   /* public void addUser(View view)
    {
        String t1 = Name.getText().toString();
        String t2 = Pass.getText().toString();
        if(t1.isEmpty() || t2.isEmpty()) {
            ToastMessage.message(getApplicationContext(),"Introduceti atat filmul, cat si data !");

        }
        else {
            long id = adapter.insertData(t1,t2);
            if(id<=0) {
                ToastMessage.message(getApplicationContext(), "Inserarea nu s-a putut face");
                Name.setText("");
                Pass.setText("");
            }
            else
            {
                ToastMessage.message(getApplicationContext(),"Inserare cu succes");
                Name.setText("");
                Pass.setText("");
            }
        }
    }*/


/*    public void update(View view)
    {
        String u1 = updateold.getText().toString();
        String u2 = updatenew.getText().toString();
        if(u1.isEmpty() || u2.isEmpty())
        {
            ToastMessage.message(getApplicationContext(),"Enter Data");
        }
        else
        {
            int a= adapter.updateName( u1, u2);
            if(a<=0)
            {
                ToastMessage.message(getApplicationContext(),"Unsuccessful");
                updateold.setText("");
                updatenew.setText("");
            } else {
                ToastMessage.message(getApplicationContext(),"Updated");
                updateold.setText("");
                updatenew.setText("");
            }
        }
    }*/

   /* public void delete( View view)
    {
        String uname = delete.getText().toString();
        if(uname.isEmpty())
        {
            ToastMessage.message(getApplicationContext(),"Enter Data");
        }
        else{
            int a= adapter.delete(uname);
            if(a<=0)
            {
                ToastMessage.message(getApplicationContext(),"Unsuccessful");
                delete.setText("");
            }
            else
            {
                ToastMessage.message(this, "DELETED");
                delete.setText("");
            }
        }
    }*/


}
