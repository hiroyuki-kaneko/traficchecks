package com.nekoscape.android.trafficchecks.database.models;

public class NetworkInfo {
    private int type;
    private int subtype;

    public NetworkInfo(int type, int subtype){
        this.type = type;
        this.subtype = subtype;
    }

    public int getType() {
        return type;
    }

    public int getSubtype() {
        return subtype;
    }
}
