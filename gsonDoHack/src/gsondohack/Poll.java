/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gsondohack;

/**
 *
 * @author Jakob
 */
public class Poll {
    int id;
    String thema;
    String question;
    String[] answer;
    int endAt;
    
    public Poll(int id,String thema, String question, String[] answer)
    {
        this.id = id;
        endAt = (int) (System.currentTimeMillis() / 1000L);
        this.thema = thema;
        this.question = question;
        this.answer = answer;
    }
}
