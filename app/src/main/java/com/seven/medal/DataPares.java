package com.seven.medal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.JsonToken;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by seven on 16-8-14.
 */

public class DataPares {
    private List<Integer> gold ;
    private List<Integer> sli ;
    private List<Integer> cu ;
    private List<String> name;
    private List<Integer> total;
    private List<Bitmap> flag;
    private List<Integer> top;

    public DataPares() {
        init();
        String json = getJson();
        getNumber(json);
    }

    private void init() {
        gold = new ArrayList<>();
        sli = new ArrayList<>() ;
        cu = new ArrayList<>() ;
        name = new ArrayList<>();
        total = new ArrayList<>();
        flag = new ArrayList<>();
        top = new ArrayList<>();
    }

    private void getNumber(String json) {
        try {
            JSONTokener parse = new JSONTokener(json);
            JSONObject obj = (JSONObject) parse.nextValue();
            JSONArray array = obj.getJSONArray("msList");
            for(int i=0;i<array.length();i++) {
                JSONObject obj1 = array.getJSONObject(i);
                JSONObject obj2 = obj1.getJSONObject("medal");
                name.add(obj2.getString("organisation"));
                top.add(obj2.getInt("rank"));
                gold.add(obj2.getInt("goldTOT"));
                sli.add(obj2.getInt("silverTOT"));
                cu.add(obj2.getInt("bronzeTOT"));
                total.add(obj2.getInt("totalTOT"));
                String url = obj2.getString("organisationImgUrl");
                flag.add(getBitMap(url));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private Bitmap getBitMap(String u) {
        URL url;
        Bitmap bit = null;
        HttpURLConnection http = null;
        try {
            url = new URL(u);
            http = (HttpURLConnection) url.openConnection();
            bit = BitmapFactory.decodeStream(http.getInputStream());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            http.disconnect();
        }

        return bit;
    }

    private String getJson() {
        StringBuffer str = new StringBuffer();
        InputStreamReader reader = null;
        BufferedReader buf = null;
        HttpURLConnection http = null;
        URL url = null;
        try {
            url = new URL("http://data.2016.163.com/medal/index.json?callback=mea&source=app");
            http = (HttpURLConnection) url.openConnection();
            reader = new InputStreamReader(http.getInputStream());
            buf = new BufferedReader(reader);

            String tmp ;
            int len;
            while((tmp = buf.readLine())!=null) {
                str.append(tmp);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(buf!=null) {
                    buf.close();
                    reader.close();
                }
                if(http!=null)
                    http.disconnect();

                } catch (IOException e) {
                    e.printStackTrace();
                }

        }

        return str.substring(4,str.length()-2);
    }

    public int getGold(int pos) {
        return gold.get(pos);
    }

    public int getSli(int pos) {
        return sli.get(pos);
    }

    public int getCu(int pos) {
        return cu.get(pos);
    }

    public int getTotal(int pos) {
        return total.get(pos);
    }

    public String getName(int pos) {
        return name.get(pos);
    }

    public int getTop(int pos) {
        return top.get(pos);
    }

    public Bitmap getBitMap(int pos) {
        return flag.get(pos);
    }

    public int size() {
        return name.size();
    }
}
