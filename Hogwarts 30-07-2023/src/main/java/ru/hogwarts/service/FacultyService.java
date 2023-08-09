package ru.hogwarts.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.Repository.FacultyRepository;
import ru.hogwarts.model.Faculty;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final FacultyRepository repository;

    public FacultyService(FacultyRepository repository) {
        this.repository = repository;
    }

    public Faculty add(Faculty faculty) {
        return repository.save(faculty);

    }

    public Faculty update(Faculty faculty) {
        return repository.findById(faculty.getId())
                .map(entity -> {
                    entity.setColor(faculty.getColor());
                    entity.setName(faculty.getName());
                    return repository.save(entity);
                })
                .orElse(null);
    }


    public Faculty get(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void remove(Long id) {
        repository.findById(id).ifPresent(repository::delete);
    }

    public Collection<Faculty> getAll() {
        return repository.findAll();
    }

    public Collection<Faculty> findByColorOrName(String color, String name) {
        return repository.findAllByColorOrNameIgnoreCase(color, name);

    }

    public String getLongestFacultyName() {
        return repository.findAll()
                .stream()
                .map(Faculty::getName)
                .max(Comparator.comparingInt(String::length))
                .orElseThrow(() -> new IllegalStateException("Not found any faculty"));

    }
}



