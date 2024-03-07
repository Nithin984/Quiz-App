package com.quiz.quizapp.service;

import com.quiz.quizapp.model.Question;
import com.quiz.quizapp.Dao.questionrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private questionrepo r;
    public ResponseEntity<String> saveQuestion(Question q){
        r.save(q);
        try {
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<List<Question>> GetAllQuestions(){
      try {
          return new ResponseEntity<>(r.findAll(),HttpStatus.OK);
      }
      catch (Exception e){
          e.printStackTrace();
      }
       return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<String> DeletebyId(Integer id) {
        r.deleteById(id);
        try {
            return new ResponseEntity<>("Question Deleted by id="+id,HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong",HttpStatus.BAD_REQUEST);

    }
    public ResponseEntity<List<Question>> findByCategory(String c){
        try {
            return new ResponseEntity<>(r.findBycategory(c),HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
         return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
}
