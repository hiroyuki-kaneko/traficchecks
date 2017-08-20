package com.nekoscape.android.trafficchecks.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.nekoscape.android.trafficchecks.database.models.NetworkInfo;
import com.nekoscape.android.trafficchecks.database.tables.NetworkInfoTable;

import java.util.ArrayList;
import java.util.List;

public class DBManagementHelper extends SQLiteOpenHelper {

    static final String NAME = "traffic_db";
    private static final int VERSION = 1;

    DBManagementHelper(Context context){
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(NetworkInfoTable.CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // No upgrade SQL yet.
    }

    public void addNetworkInfo(NetworkInfo networkInfo){
        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NetworkInfoTable.TYPE, networkInfo.getType());
        values.put(NetworkInfoTable.SUBTYPE, networkInfo.getSubtype());

        database.insert(NetworkInfoTable.TABLE_NAME, null, values);
    }

    public List<NetworkInfo> getAllNetworkInfo(){
        SQLiteDatabase database = getReadableDatabase();

        Cursor cursor = database.rawQuery(NetworkInfoTable.SELECT_ALL, null);

        return buildNetworkInfo(cursor);
    }

    private List<NetworkInfo> buildNetworkInfo(Cursor cursor){
        List<NetworkInfo> networkInfos = new ArrayList<>();
        if(isCursorPopulated(cursor)){
            do {
                int type = cursor.getInt(cursor.getColumnIndex(NetworkInfoTable.TYPE));
                int subtype = cursor.getInt(cursor.getColumnIndex(NetworkInfoTable.SUBTYPE));

                networkInfos.add(new NetworkInfo(type, subtype));
            }while(cursor.moveToNext());
        }

        return networkInfos;
    }

    private boolean isCursorPopulated(Cursor cursor) {
        return cursor != null && cursor.moveToFirst();
    }
}
