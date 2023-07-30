package ru.hogwarts.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.DTO.FacultyDTO;
import ru.hogwarts.Repository.StudentRepository;
import ru.hogwarts.model.Faculty;
import ru.hogwarts.model.Student;

import java.util.Collection;
import java.util.List;
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
                    entity.setFaculty(student.getFaculty());
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

    public FacultyDTO findFaculty(Long id) {
        return repository.findById(id).map(student -> {
            FacultyDTO dto = new FacultyDTO();
            dto.setId(student.getFaculty().getId());
            dto.setName(student.getFaculty().getName());
            dto.setColor(student.getFaculty().getColor());
            return dto;
        }).orElse(null);

    }

    public long getStudentQuantity() {
        return repository.getStudentQuantity();

    }

    public double getAvgAge() {
        return repository.getAverageAge();
    }

    public List<Student> getLastStudents() {
        return repository.getLastStudents();
    }
}






