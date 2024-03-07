package com.quiz.quizapp.service;

import com.quiz.quizapp.Dao.QuizRepo;
import com.quiz.quizapp.Dao.questionrepo;
import com.quiz.quizapp.model.Question;
import com.quiz.quizapp.model.Quiz;
import com.quiz.quizapp.model.WrapperQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Quizservice {
    @Autowired
    private questionrepo s;
    @Autowired
    private QuizRepo q;
    public ResponseEntity<String> createQuiz(String title,int num,String category){
        Quiz Q=new Quiz();
        Q.setTitle(title);
        Q.setCategory(category);

        List<Question> questions=s.findRandomQuestions(category,num);
        Q.setQuestions(questions);
        q.save(Q);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<WrapperQuestion>> getQuestionsbyId(int id) {
        List<WrapperQuestion> QuestionsforUser=new ArrayList<>();
        Optional<Quiz> questionsfromDb=q.findById(id);
        for(Question q1:questionsfromDb.get().getQuestions()){
            WrapperQuestion wq=new WrapperQuestion(q1.getQid(),q1.getQname(), q1.getOption1(), q1.getOption2(),q1.getOption3(),q1.getOption4());
            QuestionsforUser.add(wq);
        }
        return new ResponseEntity<>(QuestionsforUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> CalculateResult(int id, List<String> answers) {
        int r=0;
        int i=0;
        Quiz Q=q.findById(id).get();
        List<Question> questions=Q.getQuestions();
        for(Question q1:questions){
            if(q1.getCurrectOption().equals(answers.get(i))){
                r++;
                i++;
            }
        }
        return new ResponseEntity<>(r,HttpStatus.OK);
    }
}
