/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhdortmund.dohack.dopoll16.web.dto;

import java.util.Date;

/**
 *
 * @author Sebastian
 */
public class PollDTO {
    private int id;
    private String thema;
    private String question;
    private String[] answer;
    private Date endsAt;

    public PollDTO(int id, String thema, String question, String[] answer, Date endsAt) {
        this.id = id;
        this.thema = thema;
        this.question = question;
        this.answer = answer;
        this.endsAt = endsAt;
    }

    public PollDTO() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getThema() {
        return thema;
    }

    public void setThema(String thema) {
        this.thema = thema;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getAnswer() {
        return answer;
    }

    public void setAnswer(String[] answer) {
        this.answer = answer;
    }

    public Date getEndsAt() {
        return endsAt;
    }

    public void setEndsAt(Date endsAt) {
        this.endsAt = endsAt;
    }

}
