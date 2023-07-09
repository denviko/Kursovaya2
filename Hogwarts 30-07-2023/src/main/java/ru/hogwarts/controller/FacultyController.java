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

    // @GetMapping("/color")
    //  public Collection<Faculty> findByColor(@RequestParam String color) {
    //   return service.findByColor(color);
    // }
    @GetMapping("/findByColorOrName")
    public Collection<Faculty> findByColorOrName(@RequestParam (required = false) String color,
                                                 @RequestParam (required = false) String name) {
        return service.findByColorOrName(color,name);
    }
    @DeleteMapping

    public void delete(@RequestParam Long id) {
        service.remove(id);

    }

    @PutMapping
    public Faculty update(@RequestParam Long id, @RequestBody Faculty faculty) {
        return service.update(faculty);
    }
    @GetMapping("/all")
    public Collection<Faculty> all() {
        return service.getAll();
    }




}
