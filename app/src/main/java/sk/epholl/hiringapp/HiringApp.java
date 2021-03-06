package sk.epholl.hiringapp;

import android.app.Application;

import sk.epholl.hiringapp.data.ApiDataProvider;
import sk.epholl.hiringapp.data.bus.EventBus;
import sk.epholl.hiringapp.data.db.HiringAppSQLiteHelper;
import sk.epholl.hiringapp.data.request.RefreshDbDataRequest;

/**
 * Created by Tomáš Isteník on 28.08.2016.
 */
public class HiringApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        HiringAppSQLiteHelper.init(this);
        ApiDataProvider.init(this);
        EventBus.init();

        checkForUpdate();
    }

    public void checkForUpdate() {
        new RefreshDbDataRequest(this).refreshDbData();
    }
}
