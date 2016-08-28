package sk.epholl.hiringapp.data.api;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Tomáš Isteník on 28.08.2016.
 */
public class Company {

    public static List<sk.epholl.hiringapp.data.db.Employee> asDbEmployeeList(@NonNull final Company company) {
        List<sk.epholl.hiringapp.data.db.Employee> dbEmployees = new LinkedList<>();
        for (Department department: company.departments) {
            for (Employee employee: department.employees) {
                dbEmployees.add(new sk.epholl.hiringapp.data.db.Employee(
                        employee.firstName, employee.lastName, employee.iconUrl, department.name
                ));
            }
        }
        return dbEmployees;
    }

    @SerializedName("Departments")
    public List<Department> departments;

    public static class Department {

        @SerializedName("Name")
        public String name;

        @SerializedName("employees")
        public List<Employee> employees;
    }

    public static class Employee {

        @SerializedName("firstName")
        public String firstName;

        @SerializedName("lastName")
        public String lastName;

        @SerializedName("avatar")
        public String iconUrl;
    }
}
