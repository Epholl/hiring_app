package sk.epholl.hiringapp.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomáš Isteník on 28.08.2016.
 */
public class EmployeesDao {

    public static final String TABLE_NAME = "employees";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_LAST_NAME = "last_name";
    public static final String COLUMN_ICON_URL = "icon_url";
    public static final String COLUMN_DEPARTMENT = "department";

    public static final String[] ALL_COLUMNS = {
            COLUMN_ID,
            COLUMN_FIRST_NAME,
            COLUMN_LAST_NAME,
            COLUMN_ICON_URL,
            COLUMN_DEPARTMENT
    };

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "( "
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_FIRST_NAME + " text not null, "
            + COLUMN_LAST_NAME + " text not null, "
            + COLUMN_ICON_URL + " text not null, "
            + COLUMN_DEPARTMENT + " text not null "
            +");";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    private SQLiteDatabase mDatabase;
    private HiringAppSQLiteHelper mDbHelper;

    public EmployeesDao(Context context) {
        mDbHelper = HiringAppSQLiteHelper.getInstance();
    }

    public void open() throws SQLException {
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void close() {
        mDbHelper.close();
    }

    public long insertEmployee(@NonNull final Employee employee) {
        final ContentValues values = new ContentValues();
        values.put(COLUMN_FIRST_NAME, employee.getFirstName());
        values.put(COLUMN_LAST_NAME, employee.getLastName());
        values.put(COLUMN_ICON_URL, employee.getIconUrl());
        values.put(COLUMN_DEPARTMENT, employee.getDepartment());
        return mDatabase.insert(TABLE_NAME, null, values);
    }

    public void replaceEmployeeData(@NonNull final List<Employee> newData) {
        mDatabase.beginTransaction();
        mDatabase.delete(TABLE_NAME, null, null);
        for (Employee employee: newData) {
            insertEmployee(employee);
        }
        mDatabase.setTransactionSuccessful();
        mDatabase.endTransaction();
    }

    public List<Employee> getAllEmployees() {
        final List<Employee> employees = new ArrayList<>();
        final Cursor cursor = mDatabase.query(TABLE_NAME, ALL_COLUMNS, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            final Employee employee = fromCursor(cursor);
            employees.add(employee);
            cursor.moveToNext();
        }
        cursor.close();
        return employees;
    }

    public List<Employee> getDepartmentEmployees(@NonNull final String department) {
        final List<Employee> employees = new ArrayList<>();
        String[] selectionArgs = { department };
        final Cursor cursor = mDatabase.query(TABLE_NAME, ALL_COLUMNS,
                COLUMN_DEPARTMENT + " = ?", selectionArgs, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            final Employee employee = fromCursor(cursor);
            employees.add(employee);
            cursor.moveToNext();
        }
        cursor.close();
        return employees;
    }

    public List<String> getDepartments() {
        final List<String> departments = new ArrayList<>();
        String[] selectionColumns = { COLUMN_DEPARTMENT };
        final Cursor cursor = mDatabase.query(true, TABLE_NAME, selectionColumns, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            final String department = cursor.getString(0);
            departments.add(department);
            cursor.moveToNext();
        }
        cursor.close();
        return departments;
    }

    private Employee fromCursor(Cursor cursor) {
        return new Employee(
                cursor.getLong(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4)
        );
    }
}
