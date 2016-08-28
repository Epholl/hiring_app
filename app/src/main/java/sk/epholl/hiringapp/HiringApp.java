package sk.epholl.hiringapp;

import android.app.Application;

import sk.epholl.hiringapp.data.ApiDataProvider;
import sk.epholl.hiringapp.data.bus.EventBus;
import sk.epholl.hiringapp.data.request.RefreshDbDataRequest;

/**
 * Created by Tomáš Isteník on 28.08.2016.
 */
public class HiringApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ApiDataProvider.init(this);
        EventBus.init();

        // Call api request to refresh DB data. Only happens on app startup
        new RefreshDbDataRequest(this).refreshDbData();
    }
}
