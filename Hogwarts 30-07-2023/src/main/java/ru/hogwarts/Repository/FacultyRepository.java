package ru.hogwarts.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.model.Faculty;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository <Faculty,Long> {
    Collection<Faculty> findAllByColorOrNameIgnoreCase(String color, String name);
}
