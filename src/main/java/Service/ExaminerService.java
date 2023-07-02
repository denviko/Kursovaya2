package Service;

import pro.sky.Model.Question;

import java.util.Collection;
import java.util.Collections;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
