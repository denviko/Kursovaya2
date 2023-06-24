package Service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pro.sky.Model.Question;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service

public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService mathQuestionService;
    private final QuestionService javaQuestionService;
    private final Random random = new Random();

    public ExaminerServiceImpl(@Qualifier("math") QuestionService mathQuestionService,
                               @Qualifier("java") QuestionService javaQuestionService) {
        this.mathQuestionService = mathQuestionService;
        this.javaQuestionService = javaQuestionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {


        var allJavaQuestions = javaQuestionService.getAll();
        var allMathQuestions = mathQuestionService.getAll();
        if (allMathQuestions.size() + allJavaQuestions.size() < amount) {
            throw new QuestionNotEnoughException();
        }
        if (allMathQuestions.size() + allJavaQuestions.size() == amount) {
            var all = new HashSet<Question>();
            all.addAll(allJavaQuestions);
            all.addAll(allMathQuestions);
            return all;
        }
        Set<Question> questions = new HashSet<>(amount);
        while (questions.size() < amount) {
            questions.add(random.nextBoolean()
                    ? javaQuestionService.getRandomQuestion()
                    : mathQuestionService.getRandomQuestion());
        }

        return questions;
    }
}
