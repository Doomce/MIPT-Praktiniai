package com.dom.mipt4;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RoadInfo {

    @Expose
    @SerializedName("irenginys")
    public String name;

    @Expose
    @SerializedName("numeris")
    public String roadNumber;

    @Expose
    @SerializedName("pavadinimas")
    public String roadName;

    @Expose
    @SerializedName("kilometras")
    public String positionAt;

    @Expose
    @SerializedName("surinkimo_data")
    public String capturedAt;

    @Expose
    @SerializedName("oro_temperatura")
    public double temperature;

    @Expose
    @SerializedName("kelio_danga")
    public String roadCondition;

}
