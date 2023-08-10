package ru.hogwarts.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.DTO.FacultyDTO;
import ru.hogwarts.Repository.StudentRepository;
import ru.hogwarts.model.Student;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class StudentService {
    private final static Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }


    public Student add(Student student) {
        logger.info("Invoked add student method with argument: {}", student);
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
        logger.info("Invoked get student by id method with argument {}", id);
        return repository.findById(id).orElse(null);
    }

    public void remove(Long id) {
        logger.info("Invoked add student method");
        repository.deleteById(id);
    }

    public Collection<Student> getAll() {
        logger.info("Invoked getAll student method");
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

    public Collection<String> findByName() {
        return repository.findAll()
                .stream()
                .map(Student::getName)
                .map(String::toUpperCase)
                .filter(name -> name.startsWith("A"))
                .sorted()
                .collect(Collectors.toList());
    }
    public double getAverageAgeWithStream() {
        return repository.findAll()
                .stream()
                .mapToInt(Student::getAge)
                .average()
                .orElse(0d);

    }
    public void printNotSynchronized() {
        var students = repository.findAll().stream().limit(6).collect(Collectors.toList());
        System.out.println("=================");

        students.forEach(System.out::println);
        System.out.println("=================");


        System.out.println(students.get(0));
        System.out.println(students.get(1));

        new Thread(() -> {
            System.out.println(students.get(2));
            System.out.println(students.get(3));

        }).start();

        new Thread(() -> {
            System.out.println(students.get(4));
            System.out.println(students.get(5));

        }).start();
        System.out.println("=================");


    }

    public void printSynchronized() {
        var students = repository.findAll().stream().limit(6).collect(Collectors.toList());
        System.out.println("=================");

        students.forEach(System.out::println);
        System.out.println("=================");

        print(students.get(0));
        print(students.get(1));

        new Thread(() -> {
            print(students.get(2));
            print(students.get(3));

        }).start();

        new Thread(() -> {
            print(students.get(4));
            print(students.get(5));

        }).start();
        System.out.println("=================");
    }

    private synchronized void print(Object obj) {
        System.out.println(obj.toString());
    }
}






