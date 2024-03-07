package com.quiz.quizapp.model;

import lombok.Data;

@Data

public class WrapperQuestion {
    private Integer Qid;

    private String Qname;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    public WrapperQuestion(Integer qid, String qname, String option1, String option2, String option3, String option4) {
        Qid = qid;
        Qname = qname;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
    }
}
