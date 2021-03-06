package sk.epholl.hiringapp.activity;

import android.content.Context;

import java.util.List;

import eu.inloop.viewmodel.IView;
import sk.epholl.hiringapp.data.db.Employee;

/**
 * Created by Tomáš Isteník on 28.08.2016.
 */
public interface IMainActivityView extends IView {

    void setEmployeeData(List<Employee> employees);
    void setDepartmentData(List<String> departments);
    void setSelectedDepartment(String selectedDepartment);
    Context getContext();
}
