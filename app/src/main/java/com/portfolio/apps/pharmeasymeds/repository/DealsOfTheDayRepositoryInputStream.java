package com.portfolio.apps.pharmeasymeds.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.portfolio.apps.pharmeasymeds.response.DealsOfTheDayResponse;
import com.portfolio.apps.pharmeasymeds.retrofit.ApiRequest;
import com.portfolio.apps.pharmeasymeds.retrofit.RetrofitRequest;

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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DealsOfTheDayRepositoryInputStream {

    private static final String TAG = DealsOfTheDayRepositoryInputStream.class.getSimpleName();
    private ApiRequest apiRequest;
    private static DealsOfTheDayRepositoryInputStream ourInstance;


    public DealsOfTheDayRepositoryInputStream(Application application) {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }


    public static DealsOfTheDayRepositoryInputStream getInstance(Application application) {
        if(ourInstance == null){
            ourInstance = new DealsOfTheDayRepositoryInputStream(application);
        }
        return ourInstance;
    }
    public LiveData<DealsOfTheDayResponse> getDealsList(final String query_url) {
        final MutableLiveData<DealsOfTheDayResponse> data = new MutableLiveData<>();
        // perform the network request on separate thread
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    DealsOfTheDayResponse response = fetchData(query_url);
                    data.postValue(response);
                    Log.i("Snath, Query_URL ","URL "+query_url);
                    Log.i("Snath, RESPONSE ","Response "+response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        executorService.shutdown();
        return data;
    }
    public static DealsOfTheDayResponse fetchData(String urlString) throws IOException {

        DealsOfTheDayResponse jsonResponse = null;
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        // if url null, return
        if(url == null) {
            return null;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection= (HttpURLConnection)url.openConnection();
            urlConnection.setReadTimeout(10000/*milliseconds*/);
            urlConnection.setConnectTimeout(15000/*milliseconds*/);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            // this is being logged again even after screen rotation
            Log.v("Snath, ","Network request made");

            // if the request was successful(response code 200)
            //then read the input stream and parse the output
            if(urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
            else{
                Log.e("Snath ","Error Response code: " + urlConnection.getResponseCode());
            }
        }
        catch (IOException exception) {
            Log.e("Snath ","Problem retrieving the earthquake JSON results",exception);
        }
        finally {
            if(urlConnection != null) {
                urlConnection.disconnect();
            }
            if(inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static DealsOfTheDayResponse readFromStream(InputStream inputStream) throws IOException {
        BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();

        String inputStr; DealsOfTheDayResponse parsedData = null;  JSONArray data=null;
        while ((inputStr = streamReader.readLine()) != null) {
            responseStrBuilder.append(inputStr);
        }

        try {
            Gson gson = new Gson();
            JSONObject result = new JSONObject(responseStrBuilder.toString());
            data = result.getJSONObject("response").getJSONArray("data");
            Log.i("Snath, Result ","Result "+result);
            //For getting single attribute
            parsedData = gson.fromJson(String.valueOf(result), DealsOfTheDayResponse.class);
            System.out.println(parsedData.getDealsOfTheDayList()); //John
            Log.i("Snath, parsedList ","parsedList "+parsedData.getDealsOfTheDayList());
               /* System.out.println(gson.toJson(loginResponse)); // {"name":"John"}
                JsonObject jsonObject = new LoginResponseParser(ApplicationClass.getContext()).parse(inputStream).getAsJsonObject();*/
            Log.i("Snath, parsedData ","parsedData "+gson.toJson(result));
            // System.out.println(jsonObject.get("name").getAsString()); //John

        } catch (JSONException e) {
            e.printStackTrace();
        }
        finally {
            inputStream.close();
        }
        return parsedData;
    }
}
