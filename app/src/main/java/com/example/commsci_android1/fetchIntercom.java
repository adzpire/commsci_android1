package com.example.commsci_android1;

import android.os.AsyncTask;

import com.example.commsci_android1.Intercom;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class fetchIntercom extends AsyncTask<Void, Void, Void> {
    String data = "";
    String dataparsed = "";
    String singleparsed = "";

    @Override
    protected Void doInBackground(Void... voids) {
        try {
//            URL url = new URL("https://jsonplaceholder.typicode.com/todos");
            URL url = new URL("https://api.commsci.psu.ac.th/rest/www/album?page=1");
//            URL url = new URL("http://comm-sci.pn.psu.ac.th/office/inventory/default/indexjson");
//            URL url = new URL("https://api.commsci.psu.ac.th/rest/covid-borrow/default/initial-data");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }
//            {
//                itemms:[{},{},{},...],
//                _links: {
//
//                }
//            }
            try {
                JSONArray ja = new JSONArray(data);
                for(int i = 0; i < ja.length(); i++){
                    JSONObject jo = (JSONObject) ja.get(i);
                    singleparsed =  "id:" + jo.get("id") + "\n" +
                                    "loc_name:" + jo.get("loc_name") + "\n" +
                                    "loc_name_eng:" + jo.get("loc_name_eng") + "\n" +
                                    "loc_floor:" + jo.get("loc_floor") + "\n";
                    dataparsed += singleparsed + "\n";
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

//        Intercom.data.setText(this.dataparsed);
        Intercom.data.setText(this.data);
    }
}
