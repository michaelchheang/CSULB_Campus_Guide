package chheang_michael.com.csulb_campus_guide;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class CourseDataAdapter extends RecyclerView.Adapter<CourseDataAdapter.CourseViewHolder> {

    @Override
    public String toString() {
        return "CourseDataAdapter{" +
                "courses=" + courses +
                '}';
    }

    public List<Course> courses;

    public class CourseViewHolder extends RecyclerView.ViewHolder{
        private TextView courseSubject, courseNumber, building,
                room, startTime, endTime, daysOfCourse;
        public CourseViewHolder(View view){
            super(view);
            courseSubject = (TextView) view.findViewById(R.id.courseSubject);
            courseNumber = (TextView) view.findViewById(R.id.courseNumber);
            building = (TextView) view.findViewById(R.id.building);
            room = (TextView) view.findViewById(R.id.room);
            startTime = (TextView) view.findViewById(R.id.startTime);
            endTime = (TextView) view.findViewById(R.id.endTime);
            daysOfCourse = (TextView) view.findViewById(R.id.daysOfCourse);
        }
    }

    public CourseDataAdapter(List<Course> courses){
        this.courses = courses;
    }

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_row,parent, false);
        return new CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CourseViewHolder holder, int position) {
        Course course = courses.get(position);
        holder.courseSubject.setText(course.getCourseSubject());
        holder.courseNumber.setText(course.getCourseNumber());
        holder.building.setText(course.getBuilding());
        holder.room.setText(course.getRoom());
        holder.startTime.setText(course.getStartTime());
        holder.endTime.setText(course.getEndTime());
        holder.daysOfCourse.setText(course.getDaysOfClass());
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }
}
