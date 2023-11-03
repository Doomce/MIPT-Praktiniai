package com.dom.mipt4;


import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.*;

public class DataLoader {

    private Future<List<RoadInfo>> future;

    public DataLoader() {
        Log.w("INFO", "Init DataLoader");
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        future = executorService.submit(execute());
    }

    public List<RoadInfo> getData() throws ExecutionException, InterruptedException {
        return future.get();
    }

    private Callable<List<RoadInfo>> execute() {
        return () -> {
            try {
                Log.w("INFO", "Getting data from: https://eismoinfo.lt/weather-conditions-service");
                URL url = new URL("https://eismoinfo.lt/weather-conditions-service");

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                InputStream responseStream = connection.getInputStream();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(responseStream));
                List<RoadInfo> info = Parser.Parse(bufferedReader);
                bufferedReader.close();

                return info;
            } catch (IOException e) {
                Log.e("DataLoader", "Failed to get data from website. " + e.getMessage());
                return null;
            }
        };


    }

}
