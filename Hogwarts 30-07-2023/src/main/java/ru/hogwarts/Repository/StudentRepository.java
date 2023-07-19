package ru.hogwarts.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository <Student,Long> {
    Collection<Student> findAllByAgeBetween(int min, int max);

    Collection<Student> findAllByFaculty_Id(Long id);

}

