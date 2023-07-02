package ru.hogwarts.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import ru.hogwarts.model.Student;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service

public class StudentService {
    private final Map<Long, Student> storage = new HashMap<>();
    private long id = 0;

    public Student add(Student student) {
        student.setId(id++);
        storage.put(student.getId(), student);
        return student;
    }

    public Student update(long id, Student student) {
        if (storage.containsKey(id)) {
            storage.put(id, student);
            return student;
        }
        return null;
    }

    public Student get(Long id) {
        return storage.get(id);
    }

    public boolean remove(Long id) {
        return storage.remove(id) != null;
    }

    public Collection<Student> getAll() {
        return storage.values();
    }

    public Collection<Student> findByAge(int age) {
        return storage.values().stream()
                .filter(it -> it.getAge() == age)
                .collect(Collectors.toList());
    }

}




