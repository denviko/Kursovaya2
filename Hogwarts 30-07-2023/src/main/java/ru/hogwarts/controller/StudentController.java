package ru.hogwarts.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.DTO.FacultyDTO;
import ru.hogwarts.model.Faculty;
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

    @GetMapping("/findByAgeBetween")
    public Collection<Student> findByAgeBetween(@RequestParam int minAge, @RequestParam int maxAge) {
        return service.findByAgeBetween(minAge, maxAge);

    }

    @DeleteMapping

    public void delete(@RequestParam Long id) {
        service.remove(id);

    }

    @PutMapping
    public Student update(@RequestBody Student student) {
        return service.update(student);
    }

    @GetMapping("/{id}/faculty/")
    public FacultyDTO findFaculty(@PathVariable Long id) {
        return service.findFaculty(id);
    }


    // @GetMapping("/age")
    // public Collection<Student> findByAge(@RequestParam int age) {
    //   return service.findByAge(age);
    // }

    @GetMapping("/all")
    public Collection<Student> all() {
        return service.getAll();
    }

    @GetMapping("/by-name")
    public Collection<String> findByName() {
        return service.findByName();
    }


    @GetMapping("/avg-age")
    public double avgAge() {
        return service.getAvgAge();
    }


    @GetMapping("/quantity")
    public long quantity() {
        return service.getStudentQuantity();
    }

    @GetMapping("/last")
    public Collection<Student> last() {
        return service.getLastStudents();
    }

}
