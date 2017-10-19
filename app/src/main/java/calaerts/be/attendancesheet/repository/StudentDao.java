package calaerts.be.attendancesheet.repository;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;

import java.util.List;

import calaerts.be.attendancesheet.Converters;
import calaerts.be.attendancesheet.model.DayOfWeek;
import calaerts.be.attendancesheet.model.Hour;
import calaerts.be.attendancesheet.model.Student;
@Dao
@TypeConverters(Converters.class)
public interface StudentDao {

    @Query("select student.* from Student student " +
            "inner join Klas klas on klas.id =student.klasId " +
            "inner join Moment moment on moment.klasId=student.klasId " +
            "where moment.dayOfWeek=:dayOfWeek and moment.hour =:hour")
    LiveData<List<Student>> getStudentsAtDayOfWeekAndHour(DayOfWeek dayOfWeek, Hour hour);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStudent(Student student);

}
