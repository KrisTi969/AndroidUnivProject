package com.example.crys_.makeandroidgreatagain;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
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

import static java.net.Proxy.Type.HTTP;

/**
 * Created by crys_ on 01.12.2017.
 */

public class LoginPage extends Activity {
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
                String s1 = user.getText().toString();
                String s2 = pass.getText().toString();
                Log.v(String.valueOf(1),s1);
                Log.v(String.valueOf(2),s2);
                AsyncT asyncT = new AsyncT();
                asyncT.execute(s1,s2);
                try {
                    asyncT.getPostDataString(jsonObject);
                } catch (Exception e) {
                    e.printStackTrace();
                }
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

        public String getPostDataString(JSONObject params) throws Exception {
            System.out.print("primim");
            StringBuilder result = new StringBuilder();
            boolean first = true;

            Iterator<String> itr = params.keys();

            while(itr.hasNext()){

                String key= itr.next();
                Object value = params.get(key);
                if (first)
                    first = false;
                else
                    result.append("&");
                result.append(URLEncoder.encode(key, "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(value.toString(), "UTF-8"));
            }
            return result.toString();
        }

    }
}