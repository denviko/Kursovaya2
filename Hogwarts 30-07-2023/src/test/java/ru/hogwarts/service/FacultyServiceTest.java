package ru.hogwarts.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import ru.hogwarts.Repository.FacultyRepository;
import ru.hogwarts.model.Faculty;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FacultyServiceTest {
    @Mock
    FacultyRepository repository;
    FacultyService facultyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        facultyService = new FacultyService(repository);
    }

    @Test
    void testDelete() {
        facultyService.remove(1L);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void testUpdate() {
        when(repository.findById(1L)).thenReturn(Optional.of(new Faculty(1L, "Math", "green")));
        when(repository.save(any())).then(invocation -> invocation.getArguments()[0]);

        var updated = facultyService.update(new Faculty(1L, "Math", "ORANGE"));
        assertThat(updated.getColor()).isEqualTo("ORANGE");

        var empty = facultyService.update(new Faculty(10L, "BlaBla", "BlaBla"));
        assertThat(empty).isNull();

    }


}