package sk.epholl.hiringapp.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.squareup.otto.Subscribe;

import java.util.List;

import eu.inloop.viewmodel.AbstractViewModel;
import sk.epholl.hiringapp.data.bus.DataUpdatedEvent;
import sk.epholl.hiringapp.data.bus.EventBus;
import sk.epholl.hiringapp.data.db.Employee;

/**
 * Created by Tomáš Isteník on 28.08.2016.
 */
public class MainActivityModel extends AbstractViewModel<IMainActivityView> {

    private List<Employee> mEmployees;

    @Override
    public void onBindView(@NonNull IMainActivityView view) {
        super.onBindView(view);

        if (mEmployees == null) {
            loadData();
        } else {
            view.setEmployeeData(mEmployees);
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

    private void loadData() {

    }
}
