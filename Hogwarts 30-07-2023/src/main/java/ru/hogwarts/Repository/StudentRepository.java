package ru.hogwarts.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository <Student,Long> {
    Collection<Student> findAllByAgeBetween(int min, int max);

    Collection<Student> findAllByFaculty_Id(Long id);
@Query(value = "select count (*) from student", nativeQuery = true )
    long getStudentQuantity();

    @Query(value = "select AVG(age) from student", nativeQuery = true)
    double getAverageAge();

    @Query(value = "select * from student order by id desc limit 5", nativeQuery = true)
    List<Student> getLastStudents();

}

