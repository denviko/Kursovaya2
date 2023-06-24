package Service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pro.sky.Model.Question;
import pro.sky.Repository.QuestionRepository;

import java.util.*;

@Service("math")

public class MathQuestionService implements QuestionService {
    private final QuestionRepository repository;


    private final Random random = new Random();

    public MathQuestionService(@Qualifier("mathRepository") QuestionRepository repository) {
        this.repository = repository;
    }


    @Override

    public Question add(String question, String answer) {
        return repository.add(question, answer);
    }

    @Override
    public Question add(Question question) {
        return repository.add(question);
    }

    @Override
    public Question remove(Question question) {
        return repository.remove(question);
    }


    @Override
    public Collection<Question> getAll() {
        return repository.getAll();
    }


    @Override
    public Question getRandomQuestion() {
        var questions = repository.getAll();
        var index = random.nextInt(questions.size());
        var it = questions.iterator();
        for (int i = 0; it.hasNext(); i++) {
            var rq2 = it.next();
            if (i == index) {
                return rq2;
            }

        }

        throw new IllegalStateException("Not found any question");
    }
}