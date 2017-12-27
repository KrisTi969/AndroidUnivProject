package com.example.crys_.makeandroidgreatagain;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Random;

import javax.net.ssl.HttpsURLConnection;

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

public class LoginPage extends AppCompatActivity {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public String postUrl= "http://10.0.2.2:8080/login";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage);

        Button submitButton = (Button) findViewById(R.id.loginButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            final EditText user = (EditText) findViewById(R.id.user);
            final EditText pass = (EditText) findViewById(R.id.pass);

            public void onClick(View v) {
                JSONObject jsonObject = new JSONObject();
                ///String s1 = user.getText().toString();
               // String s2 = pass.getText().toString();
                //Log.v(String.valueOf(1),s1);
                //Log.v(String.valueOf(2),s2);
                /*AsyncT asyncT = new AsyncT();
                asyncT.execute(s1,s2);*/
                try {
                    jsonObject.put("username",user.getText().toString());
                    jsonObject.put("password",pass.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    postRequest(postUrl,jsonObject.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            void postRequest(String postUrl,String postBody) throws IOException {

                OkHttpClient client = new OkHttpClient();
                RequestBody body= RequestBody.create(JSON ,postBody);
                final Request request = new Request.Builder()
                        .url(postUrl)
                        .post(body)
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        call.cancel();
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        final String myResponse = response.body().string();
                        LoginPage.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String name = "";
                                JSONObject object = new JSONObject();
                                try {
                                    object = new JSONObject(myResponse);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                String n = "";
                                try {
                                    n = object.getString("message");
                                    Log.v(String.valueOf(1),n);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                if(n.equals("true")) {
                                    startActivity(new Intent(LoginPage.this, MainActivity.class));
                                }

                                //txtString.setText(myResponse);
                            }
                        });

                    }
                });
            }

        });

    }



    class AsyncT extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            try {
                URL url = new URL("http://10.0.2.2:8080/login"); //Enter URL here
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setReadTimeout(15000);
                httpURLConnection.setConnectTimeout(15000);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestMethod("POST"); // here you are telling that it is a POST request, which can be changed into "PUT", "GET", "DELETE" etc.
                httpURLConnection.setRequestProperty("Content-Type", "application/json"); // here you are setting the `Content-Type` for the data you are sending which is `application/json`
                httpURLConnection.setRequestProperty("Accept", "application/json");
               // httpURLConnection.connect();
                httpURLConnection.setChunkedStreamingMode(0);

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("username", params[0]);
                jsonObject.put("password", params[1]);

                    DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
                wr.writeBytes(String.valueOf(jsonObject));
                Log.v(String.valueOf(1),String.valueOf(jsonObject));
                wr.flush();
                wr.close();
                Log.v(String.valueOf(1),"Se trimite");
            /// Partea de primire de la server

                int responseCode=httpURLConnection.getResponseCode();
                Log.v(String.valueOf(1),"response code" + responseCode);
                if (responseCode == HttpsURLConnection.HTTP_OK) {
                   // Log.v(String.valueOf(1),"trece de if ");
                    BufferedReader in=new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    StringBuffer sb = new StringBuffer("");
                    String line="";
                    while((line = in.readLine()) != null) {
                        sb.append(line);
                        break;
                    }
                    in.close();
                    System.out.print(sb.toString());
                    Log.v(String.valueOf(1),sb.toString());

                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

    }
}