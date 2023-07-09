package ru.hogwarts.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.Repository.StudentRepository;
import ru.hogwarts.model.Student;

import java.util.Collection;
import java.util.stream.Collectors;

@Service

public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }


    public Student add(Student student) {
        return repository.save(student);
    }

    public Student update(Student student) {
        return repository.findById(student.getId())
                .map(entity -> {
                    entity.setAge(student.getAge());
                    entity.setName(student.getName());
                    return repository.save(entity);
                })
                .orElse(null);


    }

    public Student get(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void remove(Long id) {
        repository.deleteById(id);
    }

    public Collection<Student> getAll() {
        return repository.findAll();
    }

    public Collection<Student> findByAgeBetween(int minAge, int maxAge) {
        return repository.findAllByAgeBetween(minAge, maxAge);
    }


    //public Collection<Student> findByAge(int age) {
    //   return storage.values().stream()
    //            .filter(it -> it.getAge() == age)
    //           .collect(Collectors.toList());
    }





