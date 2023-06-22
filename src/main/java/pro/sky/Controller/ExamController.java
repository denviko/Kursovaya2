package pro.sky.Controller;

import Service.ExaminerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.Model.Question;

import java.util.Collection;

@RequestMapping("/exam")
@RestController


public class ExamController {
    private final ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("/get/{amount}")
    public Collection<Question> getAmount(@PathVariable int amount) {
        return examinerService.getQuestions(amount);

    }

}
