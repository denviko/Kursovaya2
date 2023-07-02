package ru.hogwarts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.model.Faculty;
import ru.hogwarts.model.Student;
import ru.hogwarts.service.FacultyService;

import java.util.Collection;

@Controller
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService service;

    public FacultyController(FacultyService service) {
        this.service = service;
    }
    @PostMapping
    public Faculty add(@RequestBody Faculty faculty) {
        return service.add(faculty);
    }

    @GetMapping
    public Faculty find(@RequestParam Long id) {
        return service.get(id);
    }
    @DeleteMapping

    public boolean delete(@RequestParam Long id) {
        return service.remove(id);
    }

    @PutMapping
    public Faculty update(@RequestParam Long id, @RequestBody Faculty faculty) {
        return service.update(id, faculty);
    }
    @GetMapping("/all")
    public Collection<Faculty> all() {
        return service.getAll();
    }




}
