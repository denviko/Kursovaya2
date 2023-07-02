package ru.hogwarts.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.model.Student;
import ru.hogwarts.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    public Student add(@RequestBody Student student) {
        return service.add(student);
    }

    @GetMapping
    public Student find(@RequestParam Long id) {
        return service.get(id);
    }
    @DeleteMapping

    public boolean delete(@RequestParam Long id) {
        return service.remove(id);
    }

    @PutMapping
    public Student update(@RequestParam Long id, @RequestBody Student student) {
        return service.update(id, student);
    }

    @GetMapping("/all")
    public Collection<Student> all() {
        return service.getAll();
    }


}
