/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhdortmund.dohack.dopoll16.web.dto;

import de.fhdortmund.dohack.dopoll16.db.entity.Poll;
import java.util.Date;

/**
 *
 * @author Sebastian
 */
public class AnswerDTO {
    private String id;
    private Poll poll;
    private String answer;
    private Date answeredAt;

    public AnswerDTO(){
        
    }

    public AnswerDTO(String id, Poll poll, String answer, Date answeredAt) {
        this.id = id;
        this.poll = poll;
        this.answer = answer;
        this.answeredAt = answeredAt;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getAnsweredAt() {
        return answeredAt;
    }

    public void setAnsweredAt(Date answeredAt) {
        this.answeredAt = answeredAt;
    }
}
