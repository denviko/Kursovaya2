package ru.hogwarts.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.model.Faculty;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository <Faculty,Long> {
    Collection<Faculty> findAllByColorOrNameIgnoreCase(String color, String name);

    @Query("select f from Faculty f join Student s on s.faculty.id = f.id where s.id = :studentId")
    Faculty findFacultiesByStudentId(long studentId);
}
