package com.dom.mipt4;

import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    public static List<RoadInfo> Parse(BufferedReader json) {
        try {
            Log.w("GSON", "Converting JSON to Objects....");
            Type typeOfList = new TypeToken<ArrayList<RoadInfo>>() {}.getType();
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            return gson.fromJson(json, typeOfList);
        } catch (JsonIOException | JsonSyntaxException e) {
            Log.e("GSON", "Failed to convert JSON to Object. " + e.getMessage());
            return null;
        }
    }

}
