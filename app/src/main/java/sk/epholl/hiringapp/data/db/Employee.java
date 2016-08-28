package sk.epholl.hiringapp.data.db;

/**
 * Created by Tomáš Isteník on 28.08.2016.
 */
public class Employee {

    private long _id;
    private String firstName;
    private String lastName;
    private String iconUrl;
    private String department;

    public Employee(String firstName, String lastName, String iconUrl, String department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.iconUrl = iconUrl;
        this.department = department;
    }

    public Employee(long id, String firstName, String lastName, String iconUrl, String department) {
        this._id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.iconUrl = iconUrl;
        this.department = department;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee: " + firstName + " " + lastName + ", " + department + ", " + iconUrl;
    }
}
