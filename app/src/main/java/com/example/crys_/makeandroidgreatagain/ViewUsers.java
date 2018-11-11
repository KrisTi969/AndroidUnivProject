package com.example.crys_.makeandroidgreatagain;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import database.myDbAdapter;


/**
 * Created by crys_ on 08.11.2017.
 */

public class ViewUsers extends Activity{
    myDbAdapter adapter;
    String[] list;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_users);
        print();
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(this,R.layout.activity_list_view,list);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(arrayAdapter);
    }


    public void print() {
        String dataALL = "";
        myDbAdapter adapter = new myDbAdapter(this);
        String data = adapter.getData();
        ArrayList arrayList = new ArrayList();
        list = data.split("\n");
        Collections.addAll(arrayList, list);
    }
}
