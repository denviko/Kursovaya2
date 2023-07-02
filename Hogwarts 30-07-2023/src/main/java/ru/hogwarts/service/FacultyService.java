package ru.hogwarts.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.model.Faculty;
import ru.hogwarts.model.Faculty;

import java.util.HashMap;
import java.util.Map;

@Service
public class FacultyService {
    private final Map<Long, Faculty> storage = new HashMap<>();
    private long id = 0;

    public Faculty add(Faculty faculty) {
        storage.put(id++, faculty);
        return faculty;
    }

    public Faculty update(long id, Faculty faculty) {
        if (storage.containsKey(id)) {
            storage.put(id, faculty);
            return faculty;
        }
        return null;
    }

    public Faculty get(Long id) {
        return storage.get(id);
    }

    public boolean remove(Long id) {
        return storage.remove(id) != null;
    }
}


