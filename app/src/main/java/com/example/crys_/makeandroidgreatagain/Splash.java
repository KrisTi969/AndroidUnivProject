package com.example.crys_.makeandroidgreatagain;

/**
 * Created by crys_ on 01.12.2017.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;
import java.util.Random;

public class Splash extends Activity {
    String[] myStringArray = {"\"They may take our lives, but they'll never take our freedom!\"\n" +
            "Braveheart, 1995",
            "Frankly, my dear, I don't give a damn.\n" +
            "Gone With the Wind, 1939",
            "You're gonna need a bigger boat.\n" +
            "Jaws, 1975",
            "\"May the Force be with you.\"\n" +
                    "Star Wars, 1977",
            "\"I'm going to make him an offer he can't refuse.\"\n" +
                    "The Godfather, 1972",
            };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        // Let RNGEESUS take the wheel
        Random RNG = new Random();
        int Low = 0;
        int High = 4;
        int Result = RNG.nextInt(High-Low) + Low;

        TextView quote = (TextView) findViewById(R.id.quote);
        quote.setText(myStringArray[Result]);
        Log.v(String.valueOf(1),"dadaadada"+quote);
        int secondsDelayed = 3;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(Splash.this, LoginPage.class));
                finish();
            }
        }, secondsDelayed * 1000);
    }
}
