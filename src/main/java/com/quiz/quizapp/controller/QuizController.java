package com.quiz.quizapp.controller;

import com.quiz.quizapp.model.Question;
import com.quiz.quizapp.model.WrapperQuestion;
import com.quiz.quizapp.service.Quizservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Quiz")
public class QuizController {
    @Autowired
    private Quizservice q;
    @GetMapping("create")
    @CrossOrigin
public ResponseEntity<String> create(@RequestParam String title,@RequestParam int num,@RequestParam String category){

return q.createQuiz(title,num,category);
}
@GetMapping("get/{id}")
@CrossOrigin
public ResponseEntity<List<WrapperQuestion>> getQuestions(@PathVariable int id){
        return q.getQuestionsbyId(id);
}
@PostMapping("submit/{id}")
@CrossOrigin
public ResponseEntity<Integer> CalculateResult(@PathVariable int id,@RequestBody List<String> Answers){
        System.out.println("submitted");
       return q.CalculateResult(id,Answers);
}

}
