package ru.hogwarts.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.hogwarts.model.Student;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class StudentMebMvcTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    Student testStudent;

    @Test
    public void contextLoads() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    public void testDefaultMessage() throws Exception {
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port, String.class)).isNotNull();
    }
    @Test
    public void testGetStudents() throws Exception {
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port, String.class)).isNotNull();
    }

    @Test
    public void testPostStudent() throws Exception {
        Student student = new Student();
        HttpEntity<Student> httpStudent = new HttpEntity<>(student);
        student.setAge(20);
        student.setName("Ron");
        ResponseEntity<Student> studentEntity = restTemplate.exchange(
                "Http://localChost:" + port + "/student",
                HttpMethod.PUT,
                httpStudent,
                Student.class);
        Assertions.assertThat(studentEntity.getStatusCode()).isEqualTo(HttpStatus.OK
        );

    }

    @Test
    void testDeleteStudent() {

    }




}
