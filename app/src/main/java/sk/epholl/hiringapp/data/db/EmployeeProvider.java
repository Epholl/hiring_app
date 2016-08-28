package sk.epholl.hiringapp.data.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by Tomáš Isteník on 28.08.2016.
 */
public class EmployeeProvider extends ContentProvider {

    public static final String BASE_URL = "sk.epholl.hiringapp.provider";
    public static final String EMPLOYEE_TABLE = "employees";

    public static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI(BASE_URL, EMPLOYEE_TABLE, 1);
    }



    @Override
    public synchronized boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public synchronized Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public synchronized String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public synchronized Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public synchronized int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public synchronized int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
