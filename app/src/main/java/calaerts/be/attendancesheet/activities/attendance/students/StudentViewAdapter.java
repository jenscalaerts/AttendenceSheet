package calaerts.be.attendancesheet.activities.attendance.students;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import calaerts.be.attendancesheet.R;
import calaerts.be.attendancesheet.activities.attendance.students.StudentFragment.OnListFragmentInteractionListener;
import calaerts.be.attendancesheet.model.Hour;
import calaerts.be.attendancesheet.model.Student;

public class StudentViewAdapter extends RecyclerView.Adapter<StudentViewAdapter.ViewHolder> {

    private final OnListFragmentInteractionListener mListener;
    private List<Student> values = new ArrayList<>();
    private LocalDate currentDate = new LocalDate();
    private Hour currentHour;

    public StudentViewAdapter(OnListFragmentInteractionListener listener) {
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_student_selecteable, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = values.get(position);
        holder.checkBox.setText(values.get(position).getName());
        holder.checkBox.setChecked(values.get(position).hasMissedAttendanceAtDate(currentDate, currentHour));

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) mListener.onListFragmentInteraction(holder.mItem);
            }
        });
    }

    public void setStudents(List<Student> students) {
        Collections.sort(students);
        values = students;
        this.notifyDataSetChanged();
    }

    public void setDate(LocalDate localDate) {
        currentDate = localDate == null ? new LocalDate() : localDate;
        notifyDataSetChanged();
    }

    public void setCurrentHour(Hour hour) {
        currentHour = hour;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final CheckBox checkBox;
        public Student mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            checkBox = view.findViewById(R.id.studentCheckBox);
        }
    }
}
