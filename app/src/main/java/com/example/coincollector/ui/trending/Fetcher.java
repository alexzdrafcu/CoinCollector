package com.example.coincollector.ui.trending;

import com.example.coincollector.TopCoin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Fetcher {

    String url = "https://my-json-server.typicode.com/alexzdrafcu/CoinCollectorDatabase/coins";
    private static final String JSON_ID = "id";
    private static final String JSON_NUME = "nume";
    private static final String JSON_AN = "an";
    private static final String JSON_VALOARE = "valoareNominala";
    private static final String JSON_MATERIAL = "material";
    private static final String JSON_GREUTATE = "greutate";
    private static final String JSON_DIAMETRU = "diametru";
    private static final String JSON_MARGINE = "margine";
    private static final String JSON_PRET = "pret";
    private static final String JSON_IMG = "imgurl";



    public byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK)
                return null;

            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();

        } finally {
            connection.disconnect();
        }
    }


    public String retriveJsonData() {
        StringBuilder ret = new StringBuilder();
        try {
            String jsonString = (new String(getUrlBytes(url)));
            JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
            for (int i = 0; i < array.length(); i++) {
                JSONObject json = array.getJSONObject(i);
                ret.append(json.getString(JSON_NUME));
                ret.append(json.getString(JSON_AN));
                ret.append(json.getString(JSON_MATERIAL));
                ret.append(json.getString(JSON_PRET));
                ret.append(System.getProperty("line.separator"));
            }

            System.out.println();
        } catch (Exception e) {
        }
        return ret.toString();
    }

    public ArrayList<TopCoin> extractTopCoin() {
        ArrayList<TopCoin> topCoins = new ArrayList<>();
        try {
            String jsonString = (new String(getUrlBytes(url)));
            JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
            for (int i = 0; i < array.length(); i++) {
                JSONObject json = array.getJSONObject(i);
                String id = json.getString(JSON_ID);
                String nume = json.getString(JSON_NUME);
                String an = json.getString(JSON_AN);
                String valoare = json.getString(JSON_VALOARE);
                String material = json.getString(JSON_MATERIAL);
                String greutate = json.getString(JSON_GREUTATE);
                String diametru = json.getString(JSON_DIAMETRU);
                String margine = json.getString(JSON_MARGINE);
                String pret = json.getString(JSON_PRET);
                String imgurl = json.getString(JSON_IMG);
                JSONObject detalii = json.getJSONObject("detalii");
                String detalii1 = detalii.getString("detalii1");
                String detalii2 = detalii.getString("detalii2");
                String detalii3 = detalii.getString("detalii3");
                TopCoin topCoin = new TopCoin(id,nume,an,valoare,material,greutate,diametru,margine,detalii1,detalii2,detalii3,pret,imgurl);
                topCoins.add(topCoin);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return topCoins;
    }
}
