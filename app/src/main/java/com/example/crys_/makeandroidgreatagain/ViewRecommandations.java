package com.example.crys_.makeandroidgreatagain;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

import model.Recomandations;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static java.net.Proxy.Type.HTTP;

/**
 * Created by crys_ on 01.12.2017.
 */

public class ViewRecommandations extends AppCompatActivity {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public final String postUrl = "http://10.0.2.2:8080/getRecFromServer";
    public List<Recomandations> list = new ArrayList<>();
    public Integer id = 0;
    Recomandations r1 = new Recomandations(1,"TheFlash111","20-12-2032");
    Recomandations r2 = new Recomandations(2,"Westworld111","20-12-2011");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        list.add(r1);
        list.add(r2);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_recommandations);
        final ArrayAdapter arrayAdapter = new ArrayAdapter<>(this,R.layout.activity_list_view_rec,list);
        final ListView listView = (ListView) findViewById(R.id.list_view_rec);
        listView.setAdapter(arrayAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ViewRecommandations.this,  (list.get(position)).toString(), Toast.LENGTH_SHORT).show();
                //
                Log.i("Send email", "");
                String[] TO = {""};
                String[] CC = {""};
                Intent emailIntent = new Intent(Intent.ACTION_SEND);

                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_CC, CC);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Remember to watch" + (list.get(position)).toString() + "!");

                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    finish();
                    Log.i("Finished sending email", "");
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(ViewRecommandations.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }

                ///
            }
        });


        Button submitButton = (Button) findViewById(R.id.button_get_from_serv);

        submitButton.setOnClickListener(new View.OnClickListener() {


            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            public void onClick(View v) {

                String result;
                JSONObject jsonObject = new JSONObject();
                ///String s1 = user.getText().toString();
                // String s2 = pass.getText().toString();
                //Log.v(String.valueOf(1),s1);
                //Log.v(String.valueOf(2),s2);
                /*AsyncT asyncT = new AsyncT();
                asyncT.execute(s1,s2);*/
                HttpGetRequest getRequest = new HttpGetRequest();

                try {
                    list.clear(); /// stergem lista
                    result = getRequest.execute(postUrl).get();
                    Log.v("1", result);

                    InputStream stream = new ByteArrayInputStream(result.getBytes(StandardCharsets.UTF_8.name()));
                    JsonReader jsonreader = new JsonReader(new InputStreamReader(stream, "UTF-8"));
                    String text = null;
                    String date = null;
                    jsonreader.beginArray();
                    while (jsonreader.hasNext()) {
                        jsonreader.beginObject();
                        for(int i = 0; i< 2;i++) {
                            String name = jsonreader.nextName();
                            switch (name) {
                                case "name":
                                    text = jsonreader.nextString();
                                    break;
                                case "first_aired":
                                    date = jsonreader.nextString();
                                    break;
                            }
                        }
                        list.add(new Recomandations(id, text, date));
                        id += 1;
                        jsonreader.endObject();
                    }
                    jsonreader.endArray();

                    listView.setAdapter(arrayAdapter); // reimporspamtam lista
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

    }

}