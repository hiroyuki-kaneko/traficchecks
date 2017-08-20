package com.nekoscape.android.trafficchecks.database.tables;

import android.provider.BaseColumns;

public class NetworkInfoTable implements BaseColumns {
    public static final String TABLE_NAME = "network_info";
    public static final String TYPE = "type";
    public static final String SUBTYPE = "subtype";

    public static final String CREATE_TABLE_QUERY = "create table " + TABLE_NAME + " ("+
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            TYPE +" INTEGER, "+
            SUBTYPE + "INTEGER)";


    public static final String SELECT_ALL = "select " +
            _ID + ", " +
            TYPE + ", " +
            SUBTYPE + " from " + TABLE_NAME;
}
