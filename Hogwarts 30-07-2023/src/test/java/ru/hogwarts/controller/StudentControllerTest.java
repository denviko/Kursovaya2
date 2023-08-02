package ru.hogwarts.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.Repository.AvatarRepository;
import ru.hogwarts.Repository.FacultyRepository;
import ru.hogwarts.Repository.StudentRepository;
import ru.hogwarts.model.Student;
import ru.hogwarts.service.AvatarService;
import ru.hogwarts.service.FacultyService;
import ru.hogwarts.service.StudentService;

import java.util.Optional;

import static jdk.internal.vm.compiler.word.LocationIdentity.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.ResponseEntity.status;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;

class StudentControllerTest {
    @WebMvcTest
    MockMvc mockMvc;
    @MockBean
    StudentRepository studentRepository;
    @MockBean
     AvatarRepository avatarRepository;
    @MockBean
     FacultyRepository facultyRepository;
    @SpyBean
     StudentService studentService;
    @SpyBean
     AvatarService avatarService;
    @SpyBean
     FacultyService facultyService;
    @InjectMocks
     StudentController studentController;

    StudentControllerTest() {
    }

    @Test
    public void saveStudentTest() throws Exception {
        final Long id = 1L;
        final String name = "Harry";

        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        Student student = new Student();
        student.setId(id);
        student.setName(name);

        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));


        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name));

        mockMvc.perform((MockMvcRequestBuilders
                        .get("/student?id=4")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name)));

    }
    @Test
    public void findStudentTest() throws Exception {
        final Long id = 2L;
        final String name = "Ron";

        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        Student student = new Student();
        student.setId(id);
        student.setName(name);

        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));


        MockMvc.perform(MockMvcRequestBuilders
                .delete("/student/del2")
                .accept(MediaType.APPLICATION_JSON));

    }




}