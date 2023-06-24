package pro.sky.Repository;

import Service.QuestionService;
import org.springframework.stereotype.Repository;
import pro.sky.Model.Question;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Repository("javaRepository")

public class JavaRepository implements QuestionRepository {
    private final Set<Question> questions = new HashSet<>();

    @Override
    public Question add(String question, String answer) {
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (questions.remove(question)) {
            return question;
        }
        return null;
    }

    private String remove(String question) {
        if (questions.removeIf(q -> q.getQuestion().equals(question))) {
            return question;
        }
        return null;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableSet(questions);
    }

    public int getQuestionAmount() {
        return questions.size();
    }
}


