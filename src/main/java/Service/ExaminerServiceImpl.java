package Service;

import org.springframework.stereotype.Service;
import pro.sky.Model.Question;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service

public class ExaminerServiceImpl implements ExaminerService{
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        var allquastions = questionService.getAll();
        if (allquastions.size() < amount) {
            throw new QuestionNotEnoughException();
        }
        if (allquastions.size() == amount) {
            return allquastions;
        }
        Set<Question> questions = new HashSet<>(amount);
        while (questions.size() < amount) {
            questions.add(questionService.getRandomQuestion());
        }

        return questions;
    }
}
