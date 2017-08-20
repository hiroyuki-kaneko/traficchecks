package com.nekoscape.android.trafficchecks.database;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.nekoscape.android.trafficchecks.database.models.NetworkInfo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class NetworkInfoTableTest {
    private Context context = InstrumentationRegistry.getTargetContext();
    private DBManagementHelper dbHelper = null;

    @Before
    public void setup() {
        context.deleteDatabase(dbHelper.NAME);
        dbHelper = new DBManagementHelper(context);
    }

    @After
    public void teardown(){

        dbHelper.close();
    }

    @Test
    public void addValidNetworkInfoData() {
        dbHelper.addNetworkInfo(new NetworkInfo(1, 1));

        List<NetworkInfo> networkInfoList = dbHelper.getAllNetworkInfo();

        assertThat(networkInfoList.size(), is(1));
        NetworkInfo networkInfo = networkInfoList.get(1);
        assertThat(networkInfo.getType(), is(1));
        assertThat(networkInfo.getSubtype(), is(1));
    }
}
