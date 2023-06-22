package Service;

import org.springframework.stereotype.Service;
import pro.sky.Model.Question;

import java.util.*;



@Service

public class JavaQuestionService implements QuestionService {
    private final Set<Question> questions = new HashSet<>();
    private final Random random = new Random();


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

    @Override
    public Question getRandomQuestion() {
        var index = random.nextInt(questions.size());
        var it = questions.iterator();
        for (int i = 0; it.hasNext(); i++) {
            var rq2 = it.next();
            if (i == index) {
                return rq2;
            }

        }

        throw new IllegalStateException("");
    }
}
