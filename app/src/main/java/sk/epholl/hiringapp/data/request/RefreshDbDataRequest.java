package sk.epholl.hiringapp.data.request;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import sk.epholl.hiringapp.Const;
import sk.epholl.hiringapp.data.ApiDataProvider;
import sk.epholl.hiringapp.data.api.Company;
import sk.epholl.hiringapp.data.db.Employee;
import sk.epholl.hiringapp.data.db.EmployeesDao;

/**
 * Created by Tomáš Isteník on 28.08.2016.
 */
public class RefreshDbDataRequest {

    private final Context mContext;

    public RefreshDbDataRequest(Context context) {
        this.mContext = context;
    }

    public void refreshDbData() {
        final ApiDataProvider apiDataProvider = ApiDataProvider.getInstance();
        apiDataProvider.requestObject(Const.DEFAULT_DATA_URL, Company.class, new ApiDataProvider.ResponseListener<Company>() {

            @Override
            public void onSuccess(Company response) {
                // Async load of .json data complete, fill DB;

                new AsyncTask<Company, Void, Void>() {
                    @Override
                    protected Void doInBackground(Company... company) {
                        final EmployeesDao dao = new EmployeesDao(mContext);
                        List<Employee> employeesAsList = Company.asDbEmployeeList(company[0]);
                        dao.open();
                        dao.replaceEmployeeData(employeesAsList);
                        dao.close();
                        return null;
                    }
                }.execute(response);
            }

            @Override
            public void onError(String message) {
                // Silent fail - add analytics call, notify user (SnackBar via event bus?)
                Log.e(RefreshDbDataRequest.class.getName(), "Error fetching server data: " + message);
            }
        });
    }
}
