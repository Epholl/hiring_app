package sk.epholl.hiringapp.activity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.squareup.otto.Subscribe;

import java.util.List;

import eu.inloop.viewmodel.AbstractViewModel;
import sk.epholl.hiringapp.data.bus.DataUpdatedEvent;
import sk.epholl.hiringapp.data.bus.EventBus;
import sk.epholl.hiringapp.data.db.Employee;
import sk.epholl.hiringapp.data.db.EmployeesDao;

/**
 * Created by Tomáš Isteník on 28.08.2016.
 */
public class MainActivityModel extends AbstractViewModel<IMainActivityView> {

    private List<Employee> mEmployees;
    private List<String> mDepartments;
    private Context mContext;
    private String mSelectedDepartment = null;

    @Override
    public void onBindView(@NonNull IMainActivityView view) {
        super.onBindView(view);

        if (mContext == null) {
            mContext = view.getContext();
        }

        if (mEmployees == null) {
            loadData();
        } else {
            showData();
        }
    }

    @Nullable
    @Override
    public IMainActivityView getView() {
        return super.getView();
    }

    @Override
    public void onDestroy() {
        EventBus.getInstance().unregister(this);
        super.onDestroy();
    }

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        EventBus.getInstance().register(this);
    }

    @Subscribe
    public void onEmployeeDataChanged(@NonNull final DataUpdatedEvent event) {
        loadData();
    }

    // Potentially incorrect solution. The Model can hold a list of all employees and filter them programmatically
    // instead of repeating the expensive DB operation every time
    public void setSelectedDepartment(@NonNull final String department) {
        mSelectedDepartment = department;
        loadData();
    }

    private void showData() {
        if (getView() != null) {
            getView().setSelectedDepartment(mSelectedDepartment);
            getView().setDepartmentData(mDepartments);
            getView().setEmployeeData(mEmployees);
        }
    }

    private void loadData() {
        new AsyncTask<Void, Void, List<Employee>>() {

            private List<Employee> mEmployees;
            private List<String> mDepartments;
            @Override
            protected List<Employee> doInBackground(Void... params) {

                EmployeesDao dao = new EmployeesDao(mContext);
                dao.open();
                if (mSelectedDepartment == null) {
                    mEmployees = dao.getAllEmployees();
                } else {
                    mEmployees = dao.getDepartmentEmployees(mSelectedDepartment);
                }
                mDepartments = dao.getDepartments();
                return null;
            }

            @Override
            protected void onPostExecute(List<Employee> employees) {
                MainActivityModel.this.mEmployees = mEmployees;
                MainActivityModel.this.mDepartments = mDepartments;
                showData();
            }
        }.execute();
    }
}
