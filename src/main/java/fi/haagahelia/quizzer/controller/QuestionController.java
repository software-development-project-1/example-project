package fi.haagahelia.quizzer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
// import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fi.haagahelia.quizzer.model.Question;
import fi.haagahelia.quizzer.model.Quizz;
import fi.haagahelia.quizzer.repository.DifficultyRepository;
import fi.haagahelia.quizzer.repository.QuestionRepository;
import fi.haagahelia.quizzer.repository.QuizzRepository;
import jakarta.persistence.EntityNotFoundException;

@Controller
public class QuestionController {

    @Autowired
    private QuizzRepository quizzRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private DifficultyRepository difficultyRepository;

    // show question list

    @GetMapping("/questionlist/{quizzId}")
    public String questionList(@PathVariable("quizzId") Long quizzId,
            @RequestParam(name = "difficulty", required = false) Long difficultyId,
            Model model) {
        Quizz quizz = quizzRepository.findById(quizzId)
                .orElseThrow(() -> new EntityNotFoundException("Quiz not found"));

        List<Question> questions = quizz.getQuestion();

        if (difficultyId != null) {
            if (difficultyId != 0) {
                List<Question> filteredQuestions = new ArrayList<>();
                for (Question question : questions) {
                    if (question.getDifficulty().getDifficultyId().equals(difficultyId)) {
                        filteredQuestions.add(question);
                    }
                }
                questions = filteredQuestions;
            }
        }

        model.addAttribute("quizzName", quizz.getName().toUpperCase());
        model.addAttribute("questions", questions);
        model.addAttribute("difficulties", difficultyRepository.findAll());
        model.addAttribute("selectedDifficultyId", difficultyId); // Pass the selected difficulty id to the template
        return "questionlist";
    }

    // edit question
    @GetMapping(value = "/editquestion/{questionId}")
    public String editQuizForm(@PathVariable("questionId") Long questionId, Model model) {
        model.addAttribute("question", questionRepository.findById(questionId));
        model.addAttribute("difficulties", difficultyRepository.findAll());
        model.addAttribute("quizzes", quizzRepository.findAll());
        return "editquestion.html";
    }

    // save question
    @PostMapping(value = "/savequestion")
    public String save(Question question) {
        questionRepository.save(question);
        return "redirect:/questionlist/" + question.getQuizz().getQuizzId();
    }

}