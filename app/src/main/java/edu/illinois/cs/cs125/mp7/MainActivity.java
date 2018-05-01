package edu.illinois.cs.cs125.mp7;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


import org.json.JSONObject;

import static edu.illinois.cs.cs125.mp7.Parser.getChina;
import static edu.illinois.cs.cs125.mp7.Parser.getEuro;
import static edu.illinois.cs.cs125.mp7.Parser.getUK;
import static edu.illinois.cs.cs125.mp7.Parser.getUS;

/**
 * Main screen for our API testing app.
 */
public final class MainActivity extends AppCompatActivity {
    private static String json;
    /** Default logging tag for messages from the main activity. */
    private static final String TAG = "MP7";

    /** Request queue for our network requests. */
    private static RequestQueue requestQueue;

    /**
     * Run when our activity comes into view.
     *
     * @param savedInstanceState state that was saved by the activity last time it was paused
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Load the main layout for our activity
        setContentView(R.layout.activity_main);
        // Set up a queue for our Volley requests
        requestQueue = Volley.newRequestQueue(this);

        // Attach the handler to our UI button
        final Button startAPICall = findViewById(R.id.reset);
        startAPICall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d(TAG, "Start API button clicked");
                startAPICall();


                TextView myUSD1 = (TextView) findViewById(R.id.dollar);
                myUSD1.setText(getUS(json));

                TextView myUK1 = (TextView) findViewById(R.id.uktext);
                myUK1.setText(getUK(json));

                TextView myChina1 = (TextView) findViewById(R.id.yuan);
                myChina1.setText(getChina(json));

                TextView myEuro1 = (TextView) findViewById(R.id.eurotext);
                myEuro1.setText(getEuro(json));

            }
        });


    }

    /**
     * Make an API call.
     */
    void startAPICall() {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "http://data.fixer.io/api/latest?access_key=2f25229884c3ae72eabefaadd8c19e48",
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            json = response.toString();
                            Log.d(TAG, response.toString());
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    Log.w(TAG, error.toString());
                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String getUS(final String json) {
        if (json == null) {
            return null;
        }
        JsonParser parser = new JsonParser();
        JsonObject result = parser.parse(json).getAsJsonObject();
        if (result.has("rates")) {
            JsonObject rates = result.getAsJsonObject("rates");
            if (rates.has("USD")) {
                return rates.get("USD").getAsString();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
    public static String getUK(final String json) {
        if (json == null) {
            return null;
        }
        JsonParser parser = new JsonParser();
        JsonObject result = parser.parse(json).getAsJsonObject();
        if (result.has("rates")) {
            JsonObject rates = result.getAsJsonObject("rates");
            if (rates.has("GBP")) {
                return rates.get("GBP").getAsString();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public static String getChina(final String json) {
        if (json == null) {
            return null;
        }
        JsonParser parser = new JsonParser();
        JsonObject result = parser.parse(json).getAsJsonObject();
        if (result.has("rates")) {
            JsonObject rates = result.getAsJsonObject("rates");
            if (rates.has("CNY")) {
                return rates.get("CNY").getAsString();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public static String getEuro(final String json) {
        if (json == null) {
            return null;
        }
        JsonParser parser = new JsonParser();
        JsonObject result = parser.parse(json).getAsJsonObject();
        if (result.has("rates")) {
            JsonObject rates = result.getAsJsonObject("rates");
            if (rates.has("EUR")) {
                return rates.get("EUR").getAsString();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }






}
