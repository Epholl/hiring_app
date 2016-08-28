package sk.epholl.hiringapp.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import sk.epholl.hiringapp.R;
import sk.epholl.hiringapp.data.db.Employee;

/**
 * Created by Tomáš Isteník on 28.08.2016.
 */
public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {

    private final List<Employee> mEmployees;

    public EmployeeAdapter(@NonNull final List<Employee> employees) {
        mEmployees = employees;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_employee, null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Employee employee = mEmployees.get(position);
        holder.mFirstNameView.setText(employee.getFirstName());
        holder.mSurnameView.setText(employee.getLastName());
        holder.mDepartmentView.setText(employee.getDepartment());

        Glide.with(holder.mIconView.getContext())
                .load(employee.getIconUrl())
                .into(holder.mIconView);

        // Deprecated method call to avoid api 19 problems with Context.getColor()
        int color = (position % 2 == 0) ?
                holder.mRootView.getContext().getResources().getColor(R.color.silver)
                : holder.mRootView.getContext().getResources().getColor(R.color.white);
        holder.mRootView.setBackgroundColor(color);
    }

    @Override
    public int getItemCount() {
        return mEmployees.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        View mRootView;
        ImageView mIconView;
        TextView mSurnameView;
        TextView mFirstNameView;
        TextView mDepartmentView;

        public ViewHolder(View itemView) {
            super(itemView);

            mRootView = itemView;
            mIconView = (ImageView) itemView.findViewById(R.id.icon_image);
            mSurnameView = (TextView) itemView.findViewById(R.id.surname_text_view);
            mFirstNameView = (TextView) itemView.findViewById(R.id.firstname_text_view);
            mDepartmentView = (TextView) itemView.findViewById(R.id.department_text_view);
        }
    }
}
