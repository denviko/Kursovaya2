package ru.hogwarts.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.hogwarts.model.Student;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {
    StudentService service = new StudentService();

    @Test
    void testAdd() {
        var actual = service.add(new Student(15L, "Harry", 20));
        var actual2 = service.add(new Student(20L, "Ron", 25));


        assertThat(actual.getId()).isEqualTo(0);
        assertThat(actual2.getId()).isEqualTo(1);
        assertThat(service.getAll()).containsExactly(
                new Student(0L, "Harry", 20),
                new Student(1L, "Ron", 25));
    }

    @Test
    void testDelete() {
        service.add(new Student(15L, "Harry", 20));
        service.add(new Student(20L, "Ron", 25));

        assertThat(service.getAll().size()).isEqualTo(2);

        assertThat(service.remove(1L)).isTrue();
        assertThat(service.remove(156L)).isFalse();
        assertThat(service.getAll().size()).isEqualTo(1);
        assertThat(service.getAll()).containsExactly(new Student(0L, "Harry", 20));

    }
    @Test
    void testGetAll() {
        service.add(new Student(15L, "Harry", 20));
        service.add(new Student(20L, "Ron", 25));
        service.add(new Student(25L, "Germiona", 21));

        var expected = Map.of(
                0L, new Student(0L, "Harry", 20),
                1L, new Student(1L, "Ron", 25),
                2L, new Student(2L, "Germiona", 21));

        assertThat(service.getAll()).isEqualTo(expected);

    }

}