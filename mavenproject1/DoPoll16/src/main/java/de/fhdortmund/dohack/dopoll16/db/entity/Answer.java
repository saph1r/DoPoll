/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhdortmund.dohack.dopoll16.db.entity;

import java.util.Date;

/**
 *
 * @author Sebastian
 */
public class Answer {

    private String id;
    private int pollId;
    private String answer;
    private Date answeredAt;

    public Answer(){
        
    }

    public Answer(String id, int pollId, String answer, Date answeredAt) {
        this.id = id;
        this.pollId = pollId;
        this.answer = answer;
        this.answeredAt = answeredAt;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPoll() {
        return pollId;
    }

    public void setPoll(int pollId) {
        this.pollId = pollId;
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
