package com.dom.mipt4;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            fillList(new DataLoader().getData());
        } catch (ExecutionException | InterruptedException e) {
            Log.e("DataLoader", "Failed to get data. " + e.getMessage());
        }
    }

    private void fillList(List<RoadInfo> roadInfoList) {
        if (roadInfoList == null) return;
        Log.w("INFO", "Filling view with road info data.");
        ListView list = findViewById(R.id.itemsList);
        RoadInfoAdapter customAdapter = new RoadInfoAdapter(getApplicationContext(), roadInfoList);
        list.setAdapter(customAdapter);
    }


}