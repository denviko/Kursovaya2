package Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pro.sky.Model.Question;
import pro.sky.Repository.JavaRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;


class JavaQuestionServiceTest {
    @Mock
    JavaRepository repository;
    JavaQuestionService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new JavaQuestionService(repository);

        when(repository.add(anyString(), anyString())).thenReturn(new Question("q1","q2"));
        when(repository.getAll()).thenReturn(List.of(new Question("q1","q2")));
    }

    @Test
    void testAdd() {
        var actual = service.add("q1","q2");
        assertEquals("q1",actual.getQuestion() );
        assertEquals("q2", actual.getAnswer());
        var all = service.getAll();
        assertEquals(1, all.size());
        Question expected = new Question("q1", "q2");
        assertEquals(expected, all.iterator().next());
    }
}

