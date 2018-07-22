package com.scholarships.college.quorradota;

import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;

public class Loading extends AppCompatActivity {

    ProgressDialog pd;
    DatabaseHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        final PreferenceHelper mPref;
        mPref = new PreferenceHelper(getApplicationContext());
        mPref.setFirstTime(true);
        mPref.setCurrentLoad(0);
        DB = new DatabaseHelper(this);

        //Toast.makeText(this, mPref.getHello(), Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                new JsonTask().execute("http://ip-api.com/json/");
            }
        },(3000));
    }


    public String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        String ip = Formatter.formatIpAddress(inetAddress.hashCode());
                        Log.i("wew", "***** IP="+ ip);
                        return ip;
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e("wew", ex.toString());
        }
        return null;
    }

    private class JsonTask extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();

            pd = new ProgressDialog(Loading.this);
            pd.setMessage("Please wait");
            pd.setCancelable(false);
            pd.show();
        }

        protected String doInBackground(String... params) {


            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();


                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line+"\n");
                    Log.d("Response: ", "> " + line+ "\n");   //here u ll get whole response...... :-)

                }

                return buffer.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (pd.isShowing()){
                pd.dismiss();
            }

            String ip = "";
            String city = "";
            String country = "";
            JSONObject obj = null;
            try {
                obj = new JSONObject(result);
                System.out.println(obj.getString("query"));
                ip = obj.getString("query");
                city = obj.getString("city");
                country = obj.getString("country");

                Toast.makeText(Loading.this, ip+ " " + city + " "+ country, Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            boolean statIP = true;
            int i=0;
            while( i < DB.getAllNotes().size() && statIP){
                if(ip.equals(DB.getAllNotes().get(i).getNote())){
                    Toast.makeText(Loading.this, "You Already Use IP", Toast.LENGTH_LONG).show();
                    statIP=false;
                }
                i++;
            }

            if(statIP){
                DB.insertIP(ip,country);
            }

            Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
            startActivity(intent);
            finish();

        }
    }
}
