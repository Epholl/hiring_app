package sk.epholl.hiringapp.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Tomáš Isteník on 28.08.2016.
 */
public class HiringAppSQLiteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "hiring_app.db";
    public static final int DATABASE_VERSION = 1;



    public HiringAppSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(EmployeesDao.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(EmployeesDao.DROP_TABLE);
        onCreate(db);
    }
}
