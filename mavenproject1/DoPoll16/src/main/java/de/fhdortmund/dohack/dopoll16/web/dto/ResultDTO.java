/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhdortmund.dohack.dopoll16.web.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author Sebastian
 */
public class ResultDTO {
    
    private Map<String,Integer> resultMap = new HashMap<String, Integer>();
   
    public static ResultDTO createResultDTO(PollDTO pollDTO, List<AnswerDTO> answers){
        ResultDTO resultDTO = new ResultDTO();
        for(String i : pollDTO.getAnswer()){
            resultDTO.getMap().put(i, 0);
        }
        for(AnswerDTO i : answers){
            if(i.getPoll() == pollDTO.getId()){
                resultDTO.getMap().put(i.getAnswer(), resultDTO.getMap().get(i.getAnswer())+1);
            }
        }
        return resultDTO;
    }
    public static ResultDTO createResultDTOProcent(PollDTO pollDTO, List<AnswerDTO> answers){
        ResultDTO resultDTO = new ResultDTO();
        for(String i : pollDTO.getAnswer()){
            resultDTO.getMap().put(i,0);
        }
        int zahl = 0;
        for(AnswerDTO i : answers){
            if(i.getPoll() == pollDTO.getId()){
                resultDTO.getMap().put(i.getAnswer(), resultDTO.getMap().get(i.getAnswer())+1);
                zahl++;
            }
        }
        double tmp;
        for(Entry<String, Integer> eintrag : resultDTO.getMap().entrySet()){
            tmp = eintrag.getValue()/zahl * 100;
            eintrag.setValue((int)tmp);
        }
        return resultDTO;
    }
    
    public Map<String, Integer> getMap(){
        return resultMap;
    }
    
}
