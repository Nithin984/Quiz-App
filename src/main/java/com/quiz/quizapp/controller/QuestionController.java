package com.quiz.quizapp.controller;

import com.quiz.quizapp.model.Question;
import com.quiz.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Question")
public class QuestionController {
    @Autowired
    private QuestionService s;
    @PostMapping("save")
public ResponseEntity<String> InsertQuestion(@RequestBody Question q){
    return s.saveQuestion(q);
}
@GetMapping("GetAllQuestions")
public ResponseEntity<List<Question>> GetAll(){
        return s.GetAllQuestions();
}
@DeleteMapping("Delete/{id}")
public ResponseEntity<String> deletebyId(@PathVariable Integer id){
        return s.DeletebyId(id);
}
@GetMapping("Category/{c}")
public ResponseEntity<List<Question>> findByCategory(@PathVariable String c){
        return s.findByCategory(c);
}
}
