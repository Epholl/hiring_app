package sk.epholl.hiringapp.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import eu.inloop.viewmodel.base.ViewModelBaseActivity;
import sk.epholl.hiringapp.Adapter.EmployeeAdapter;
import sk.epholl.hiringapp.HiringApp;
import sk.epholl.hiringapp.R;
import sk.epholl.hiringapp.data.db.Employee;

public class MainActivity extends ViewModelBaseActivity<IMainActivityView, MainActivityModel> implements IMainActivityView {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setModelView(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.employee_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        findViewById(R.id.department_all_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getViewModel().setSelectedDepartment(null);
            }
        });

        findViewById(R.id.department_rd_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getViewModel().setSelectedDepartment("RD");
            }
        });

        findViewById(R.id.retry_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HiringApp)getApplication()).checkForUpdate();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public Class<MainActivityModel> getViewModelClass() {
        return MainActivityModel.class;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setEmployeeData(List<Employee> employees) {
        if (employees.size() == 0) {
            findViewById(R.id.no_data_view).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.no_data_view).setVisibility(View.GONE);

        }

        if (mRecyclerView == null) {
            // The activity was "losing" the Recycler View reference upon rotations, quick hacky fix
            mRecyclerView = (RecyclerView) findViewById(R.id.employee_recycler_view);
        }
        mRecyclerView.setAdapter(new EmployeeAdapter(employees));
    }

    @Override
    public void setDepartmentData(List<String> departments) {

    }

    @Override
    public void setSelectedDepartment(String selectedDepartment) {

    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }
}
